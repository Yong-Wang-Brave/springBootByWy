package com.wy.demo.丁雪峰.InitialzingBean;


import com.wy.demo.controller.dto.Student;
import org.springframework.beans.factory.InitializingBean;
/*InitialzingBean用法
        当一个类实现这个接口之后，Spring启动后，初始化Bean时，
        若该Bean实现InitialzingBean接口，会自动调用afterPropertiesSet()方法，
        完成一些用户自定义的初始化操作。*/
/*重要提醒
        Spring是通过反射来调用init-method指定方法，
        而实现InitializingBean接口是直接调用afterPropertiesSet方法，所以后者效率高，
        但使用init-method方式减少了对Spring的依赖
        如果调用afterPropertiesSet方法时报错，则不会再调用init-method指定的方法
        ————————————————
        版权声明：本文为CSDN博主「凌兮～」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/qq_40093255/article/details/117317943*/
public class SpringBeanInit implements InitializingBean {
 
    private Integer id;
 
    private String name;
 
    private Integer age;
 
    private boolean sex;
 
    private Student student;
 
 /** 这里进行优先调用初始化一些参数
 */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("this is bean init set student data");
        Student student = new Student();
        this.student = student;
    }
 
    public void testInit(){
        System.out.println("this is bean web.xml init-method invock");
    }
 
    public Student getStudent() {
        return student;
    }
 
    public void setStudent(Student student) {
        this.student = student;
    }
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Integer getAge() {
        return age;
    }
 
    public void setAge(Integer age) {
        this.age = age;
    }
 
    public boolean isSex() {
        return sex;
    }
 
    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
