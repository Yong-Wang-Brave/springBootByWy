package com.wy.demo.自定义注解.demo3;

import com.wy.demo.Exception.Exception2.HealthManageException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CryptUtil {

   public void insertFill(Object object){
       MetaObject metaObject = MetaObject.forObject(object, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
       List<Field> fields = Arrays.asList(metaObject.getOriginalObject().getClass().getDeclaredFields());
       fields.forEach(field -> {
           Encrypt annotation = field.getAnnotation(Encrypt.class);
           if (annotation!=null) {
               try {
                   setFieldValue(field,annotation,metaObject);
               } catch (Exception e) {
                  throw new HealthManageException("数据脱敏失败",e);
               }
           }
       });
   }

    public  void setFieldValue(Field field, Encrypt annotation, MetaObject metaObject)  {
       String tableName = annotation.tableName();
       String columnName =annotation.columnName();
        String originalFieldName = annotation.original();
        String plain = String.valueOf(metaObject.getValue(originalFieldName));
        String targetFieldName = field.getName();
        String targetValue = "";
       switch(annotation.fillType()) {
           case ENCRYPT:
               //加密逻辑策略写在这里
               targetValue= plain+"aa";
              break;
           case HASH:
               //哈希逻辑
               targetValue=null;
               break;
           default:
       }
        setFieldValueByName(targetFieldName,targetValue.toString(),metaObject);

    }

    public void setFieldValueByName(String targetFieldName, String targetValue, MetaObject metaObject) {
       metaObject.setValue(targetFieldName,targetValue);
    }


    public String decrypt(String plain) throws Exception {
       String originValue=plain.substring(0,plain.length()-2);
        return originValue;


   /*     String str = "123456";
        // 密码，长度要是8的倍数 密钥随意定
        String password = "12345678";
        byte[] encrypt = encrypt(str.getBytes(), password);
        System.out.println("加密前:" +str);
        System.out.println("加密后:" + new String(encrypt));
        // 解密
        byte[] decrypt = decrypt((byte[]) encrypt, password);
        System.out.println("解密后:" + new String(decrypt));*/
    }
}
