package com.wy.demo.mybatiesInterceptor;

import com.wy.demo.mybatiesInterceptor.MD5.DES;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Intercepts({
        @Signature(type= Executor.class,method = "update",args = {MappedStatement.class,Object.class})
,@Signature(type = Executor.class,method = "query",args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class
}),@Signature(type = Executor.class,method = "query",args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class
, CacheKey.class, BoundSql.class})}
        )


@Component
//这个注解很关键
public class DataInterceptor implements Interceptor {

    Object parameter ;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //执行增删改查的对应的方法  id: com.wy.demo.mapper.UserMapper.updateUser
        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
       //是否是需要处理的方法
        if (isSensitive(statement.getId())) {
            Object[] args = invocation.getArgs();
            //方法对应的参数，也就是入参实体类
            parameter  = args[1];

            encry(parameter);
        }
        Object proceed = invocation.proceed();
        if (proceed==null) {
              return null;
        }
//解密  根据sql语句找出所有需要的字段，然后解密成功即可
        decry(parameter);

        return proceed;
    }

    private void decry(Object parameter) throws Exception {
       /* if ((proceed instanceof List)) {
            List list=(List)proceed;
            if (CollectionUtils.isEmpty(list)) {
                return;
            }
            Object o = list.get(0);
            Class<?> aClass = o.getClass();
            aClass.getAnnotation(SensitiveTable.class);
        }*/


        Class<?> returnClass = parameter.getClass();
        //找出加了注解的字段
        Field[] fields   = returnClass.getDeclaredFields();
        for (Field field : fields) {
            //要加密的字段上定义的注解信息
            SensitiveField annotation = field.getAnnotation(SensitiveField.class);
            if (annotation!=null) {
                //处理加注解的字段 也就是field  annotation  该字段对应的注解信息，parameter 实体类
                setFieldValueDecry(field,annotation,parameter);
            }
        }

    }

    private void encry(Object parameter)  throws Exception{
        //  class com.wy.demo.entity.UserReq
        Class<?> aClass = parameter.getClass();

        //判断实体类是否加了表明的注解
        SensitiveTable sensitiveTable = aClass.getAnnotation(SensitiveTable.class);
        if ((sensitiveTable==null)) {
            return;
        }
        //找出加了注解的字段
        Field[] fields   = aClass.getDeclaredFields();
        for (Field field : fields) {
            //要加密的字段上定义的注解信息
            SensitiveField annotation = field.getAnnotation(SensitiveField.class);
            if (annotation!=null) {
               //处理加注解的字段 field原字段  annotation  该字段对应的注解信息，parameter 实体类
               setFieldValue(field,annotation,parameter);
            }
        }


    }
    private void setFieldValueDecry(Field field, SensitiveField annotation, Object parameter) throws Exception {
        Class<?> aClass = parameter.getClass();
        //从注解中获取 要解密的字段
        String originalFieldName = annotation.original();
        //parameter实体类  从实体类中获取要修改的字段的字段值
        Object oriValueByName = getFieldValueByName(originalFieldName, parameter);
        if (oriValueByName==null) {
            return;
        }
        //要加密字段找到原始值对应的值
        String plain = String.valueOf(oriValueByName);
        if (StringUtils.isEmpty(plain)) {
            return;
        }
        //加注解值对应的value
       Object  fieldValueByName = getFieldValueByName(field.getName(), parameter);


//获取表明
        String tableName = aClass.getAnnotation(SensitiveTable.class).value();
        //获取标记字段上对应表的字段
        String columnName = annotation.columnName();
        byte[] targetValue;
        switch (annotation.fillType()) {
            case HASH:
                targetValue = DES.decrypt((byte[]) fieldValueByName,(tableName + columnName));
                break;
            case ENCRYPT:
                //没有加break，所以依旧会执行break
            default:
                targetValue = DES.decrypt((byte[])fieldValueByName,(tableName + columnName));
                break;
        }

    }
    private void setFieldValue(Field field, SensitiveField annotation, Object parameter) throws Exception {
        Class<?> aClass = parameter.getClass();
        //从注解中获取 要加密的字段
        String originalFieldName = annotation.original();
        //parameter实体类  从实体类中获取要修改的字段的字段值
        Object fieldValueByName = getFieldValueByName(originalFieldName, parameter);
        if (fieldValueByName==null) {
            return;
        }
        //要加密字段找到原始值对应的值
        String plain = String.valueOf(fieldValueByName);
        if (StringUtils.isEmpty(plain)) {
            return;
        }
//获取表明
        String tableName = aClass.getAnnotation(SensitiveTable.class).value();
        //获取标记字段上对应表的字段
        String columnName = annotation.columnName();
        byte[] targetValue;
        switch (annotation.fillType()) {
            case HASH:
                targetValue = DES.encrypt(plain.getBytes(),(tableName + columnName));
                break;
            case ENCRYPT:
                //没有加break，所以依旧会执行break
            default:
                targetValue = DES.encrypt(plain.getBytes(),(tableName + columnName));
                break;
        }
        //加注解的字段
        String name = field.getName();
// 重新给实体类赋值
        setFieldValueByName(name,targetValue,parameter);

    }
    //给加注解的字段赋值 加密后的内容
    private void setFieldValueByName(String name, byte[] targetValue, Object parameter) throws Exception {
        Class<?> aClass = parameter.getClass();
        //获取整个实体类
        BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
  // 找到实体类中的原字段
        PropertyDescriptor propertyDescriptor = Arrays.stream(beanInfo.getPropertyDescriptors()).filter(a ->
                name.equals(a.getName())).findFirst().orElse(null);
        if (propertyDescriptor==null) {
            return ;
        }
        //将加密后的写入实体类中的加注解的字段中
        Method readMethod = propertyDescriptor.getWriteMethod();
          readMethod.invoke(parameter,targetValue);



    }


    /**
     * 从实体类中  根据字段名 找对应的value
     * @param name
     * @param o
     * @return
     * @throws Exception
     */
    private Object getFieldValueByName(String name, Object o) throws Exception {
        Class<?> aClass = o.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
        PropertyDescriptor propertyDescriptor = Arrays.stream(beanInfo.getPropertyDescriptors()).filter(a ->
                name.equals(a.getName())).findFirst().orElse(null);
        if (propertyDescriptor==null) {
            return null;
        }
        Method readMethod = propertyDescriptor.getReadMethod();
        return  readMethod.invoke(o);

    }


    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    //定义需要筛选的方法
    private Boolean isSensitive(String statementId) {
        List<String> strings = Arrays.asList(SensitiveConstants.statementIds);
        if (strings.contains(statementId)) {
            return true;
        }
        return false;
    }

}
