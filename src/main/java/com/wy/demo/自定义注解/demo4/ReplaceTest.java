package com.wy.demo.自定义注解.demo4;

import org.junit.Test;

public class ReplaceTest {


    @Test
   public  void parse(){
        StudentAndTeacher studentAndTeacher = new StudentAndTeacher(" ddd ");
        Object parse = new ReplaceParser().Parse(studentAndTeacher);

        StudentAndTeacher aa=  (StudentAndTeacher)parse;
        System.out.println(aa.getName());
    }
}
