package com.wy.demo.mybatiesInterceptor2;
 

import com.wy.demo.mybatiesInterceptor.SensitiveConstants;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;
 
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
 
/**
 * @author lu'xing
 * @title: AutoAddQueryConditionInterceptor
 * @projectName commerce
 * @description: TODO
 * @date 2021/8/1720:51
 */
//@Component
@Intercepts({
        @Signature(
                type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class
        }),
})
public class AutoAddQueryConditionInterceptor implements Interceptor {
 
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

            // 方法一
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
            //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
            MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
            //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
            String mappedStatementId = mappedStatement.getId();

            //sql语句类型 select、delete、insert、update
            String sqlCommandType = mappedStatement.getSqlCommandType().toString();
            BoundSql boundSql = statementHandler.getBoundSql();

            //获取到原始sql语句
            String sql = boundSql.getSql();
            String mSql = "";

            //TODO 修改位置
    /*    if(mappedStatementId.startsWith("com.scm.platform.trading.mapper.SaleOrderMapper.selectByExample")
            || mappedStatementId.startsWith("com.scm.platform.trading.mapper.SaleSettlementMapper.selectByExample")){
            String addStr = "WHERE 1=1 and create_user_code = '" + userCode  + "' and ";
            mSql = sql.replace("WHERE", addStr);
        }else {
            mSql = sql;
        }*/
        if(mappedStatementId.startsWith("com.wy.demo.mybatis.mappers.SortCourseMapper.findSortCourse")){
            String addStr = "where 1=1 and  ";
            mSql = sql.replace("where", addStr);
        }

            //注解逻辑判断  添加注解了才拦截
//        Class<?> classType = Class.forName(mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf(".")));
//        String mName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1, mappedStatement.getId().length());
//        for (Method method : classType.getDeclaredMethods()) {
//            if (method.isAnnotationPresent(InterceptAnnotation.class) && mName.equals(method.getName())) {
//                InterceptAnnotation interceptorAnnotation = method.getAnnotation(InterceptAnnotation.class);
//                if (interceptorAnnotation.flag()) {
//                    mSql = sql + " and create_user_code = '" + userCode  + "'";
//                }
//            }
//        }
            //通过反射修改sql语句
            Field field = boundSql.getClass().getDeclaredField("sql");
            field.setAccessible(true);
            field.set(boundSql, mSql);


        return invocation.proceed();
    }

    //定义需要筛选的方法
    private Boolean isSensitive(String statementId) {
        List<String> strings = Arrays.asList(SensitiveConstants.statementIds);
        if (strings.contains(statementId)) {
            return true;
        }
        return false;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }
 
    @Override
    public void setProperties(Properties properties) {
    }
 
}