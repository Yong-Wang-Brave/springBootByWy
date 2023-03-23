package com.wy.demo.controller;

import cn.hutool.core.date.StopWatch;
import com.wy.demo.entity.UserBatch;
import com.wy.demo.jwt.UserService;
import com.wy.demo.mybatis.mappers.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class UserBathController {
    private static final int BATCH_SIZE = 100;

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    UserService userService;

    @PostMapping("/inserBatch")
    public String welcome() {
        List<UserBatch> userList = buildDataToInsert();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int size = userList.size();
        int batchCount = (size + BATCH_SIZE - 1) / BATCH_SIZE;
        for (int i = 0; i < batchCount; i++) {
            int fromIndex = i * BATCH_SIZE;
            int toIndex = Math.min(fromIndex + BATCH_SIZE, size);
            List<UserBatch> subList = userList.subList(fromIndex, toIndex);

            userService.batchInsert(subList);
        }
        stopWatch.stop();
        log.info(stopWatch.getTotalTimeMillis());
        return "welcome token authentication";
    }

    @PostMapping("/inserBatch2")
    public String welcome2() {
        List<UserBatch> userList = buildDataToInsert();
// add some objects to the list
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserMapper someMapper = sqlSession.getMapper(UserMapper.class);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            int size = userList.size();
            int batchCount = (size + BATCH_SIZE - 1) / BATCH_SIZE;
            for (int i = 0; i < batchCount; i++) {
                int fromIndex = i * BATCH_SIZE;
                int toIndex = Math.min(fromIndex + BATCH_SIZE, size);
                List<UserBatch> subList = userList.subList(fromIndex, toIndex);
                someMapper.batchInsert(subList);
                sqlSession.commit();
            }
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        stopWatch.stop();
        log.info(stopWatch.getTotalTimeMillis());
        return "welcome token authentication";
    }

    private static List<UserBatch> buildDataToInsert() {
        // 构建待插入的数据
        List<UserBatch> dataToInsert = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            UserBatch userBatch = new UserBatch();
            userBatch.setUsername(String.valueOf(i));
            userBatch.setPassword(String.valueOf(i));
            dataToInsert.add(userBatch);

        }
        return dataToInsert;
    }


    /**
     * 参考地址
     * https://mybatis.org/mybatis-dynamic-sql/docs/insert.html
     */
    @PostMapping("/inserBatch22")
    public String welcome22() {
/*
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            //自定义你的方法来获取需要插入的数据
            List<UserBatch> records = getRecordsToInsert();
            //BatchInsert
            BatchInsert<UserBatch> batchInsert = insert(records)
                    .into(table)
                    .map(id).toProperty("id")
                    .map(field1).toProperty("field1")
                    .map(field2).toProperty("field2")
                    .build()
                    .render(RenderingStrategy.MYBATIS3);
            batchInsert.insertStatements().stream().forEach(mapper::insert);
            session.commit();
        } finally {
            session.close();
        }

        return "welcome token authentication";
    }*/
        return null;

    }


}