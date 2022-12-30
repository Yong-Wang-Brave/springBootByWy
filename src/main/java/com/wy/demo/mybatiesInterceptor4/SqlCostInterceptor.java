package com.wy.demo.mybatiesInterceptor4;

import com.github.pagehelper.util.StringUtil;
import com.wy.demo.丁雪峰.ApplicationContextAware.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

@Intercepts({//Statement声明
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})
        , @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})}
)
@Slf4j
public class SqlCostInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        boolean showCompleteSql = false;
        String showSql = ApplicationContextUtil.getApplicationContext().getEnvironment().getProperty("mybatis.showCompleteSql");
        if (StringUtil.isNotEmpty(showSql)) {
            showCompleteSql = Boolean.parseBoolean(showSql);
        }
        if (!showCompleteSql) {
            return invocation.proceed();
        }
        long startTime = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        long endTime = System.currentTimeMillis();
        long sqlCost = endTime - startTime;
        Object target = invocation.getTarget();
        StatementHandler statementHandler = (StatementHandler) target;
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());

        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        //BouldSql就是封装mybatis最终产生的sql类
        BoundSql boundSql = statementHandler.getBoundSql();
        Configuration configuration = mappedStatement.getConfiguration();
        //获取到最终的sql语句
        String sql = showSql(configuration, boundSql);
        log.info("execute sql method:{}", mappedStatement.getId());
        log.info("execute sql:{} ", sql);
        if (sqlCost > 2000) {
            log.warn("execute sql cost:{}ms", sqlCost);
        } else {
            log.info("execute sql cost:{}ms", sqlCost);
        }


        return proceed;
    }

    private String showSql(Configuration configuration, BoundSql boundSql) throws NoSuchFieldException, IllegalAccessException {
        //获取参数
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //sql语句中多个空格都用一个空格代替
        String sql = getRealSql().replaceAll("[\\s]+", " ");
        if (parameterObject != null) {
            //获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            //如果根据parameterObject.getClass()可以找到对应的类型，则替换
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
            } else {
                //MetaObject 主要是封装了originalObbject对象，提供了get和set方法用于获取和设置originalObject属性值，主要支持对javaBean,Collection
                //map 三种类型对象的操作
                MetaObject metaObject = configuration.newMetaObject(parameterObject);

                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));

                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        //该分支是动态sql
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));

                    } else {
                        //打印出缺失，提醒该参数缺失并防止错位
                        sql = sql.replaceFirst("\\?", "缺失");
                    }
                }
            }
        }
        return sql;
    }

    /**
     * 如果参数是String ，则添加单引号
     * 如果是日期，则转换为时间格式器并加单引号
     * 对参数是null和不是null的情况做处理
     *
     * @param
     * @return
     */
    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

    /**
     * 获取真实的sql,mybatis将其存在ThredLocal中
     *
     * @return
     */
    private String getRealSql() throws NoSuchFieldException, IllegalAccessException {
        Field field = ErrorContext.class.getDeclaredField("sql");
        field.setAccessible(true);
        Object sql = field.get(ErrorContext.instance());
        if (sql != null) {
            return sql.toString();
        }
        return "";

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
