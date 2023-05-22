package com.wy.demo;

import com.wy.demo.Redis.redisLock.RedisLockAspect;
import com.wy.demo.controller.Service.Impl.SortCourseServiceImpl;
import com.wy.demo.controller.dto.PageDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;


@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DistributedLockTest {

    @Autowired
    private SortCourseServiceImpl sortCourseServiceImpl;
    @Autowired
    ApplicationContext applicationContext;

    @Test
 public   void testDistributedLock() throws InterruptedException {
        PageDto pageDto = new PageDto();
        pageDto.setCatId(11);

        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            sortCourseServiceImpl.findSortCourseByDTO(pageDto);
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            sortCourseServiceImpl.findSortCourseByDTO(pageDto);
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();
    }




    @Test
    public  void  testRegister(){
        RedisLockAspect bean = applicationContext.getBean(RedisLockAspect.class);
        System.out.println("哈哈"+bean);
    }
}