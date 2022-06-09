package com.wy.demo.luanma;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestCharacter
{

    public static void main(String[] args) throws IOException {
        String s1="我嫩爹";
        System.out.println("操作系统默认的编码"+ Charset.defaultCharset());
        byte[] bytes1 = s1.getBytes(StandardCharsets.UTF_8);
        byte[] gbks = s1.getBytes("GBK");
        //输出utf-8编码后的字节数组
       // [-26, -108, -128, -27, -115, -102, -24, -81, -66, -27, -96, -126]
        System.out.println(Arrays.toString(bytes1));
        //输出gbk编码后的字节数组
        //[-59, -54, -78, -87, -65, -50, -52, -61]
        System.out.println(Arrays.toString(gbks));
       //解码和编码一致
        String s11 = new String(bytes1, "UTF-8");
        String s22 =new String(gbks,"GBK");
        System.out.println(s11);
        System.out.println(s22);

        //读取文件案例  文件的字符集与读取的字符集一致才不会乱码
        test01("C:\\Users\\king\\Desktop\\任务2.txt","GB2312");
      //utf8编码  iso8859-1解码   iso8859-1编码 usf8解码 就可以还原真面目了
       String a="我恁爹";
       //utf8编码
        byte[] bytes = a.getBytes(StandardCharsets.UTF_8);
        System.out.println("前。。。。。。。。。。。。。"+Arrays.toString(bytes));
        //iso 解码
        String s = new String(bytes, "ISO-8859-1");
        //编码得到原来的字节数组
        byte[] bytes2 = s.getBytes(StandardCharsets.ISO_8859_1);
        System.out.println("后。。。。。。。。。。。。。"+Arrays.toString(bytes2));
       //utf8解码
        System.out.println(new String(bytes2,"GBK"));
//延时  只适用于iso8859-1  不适用与GBK


    }

   public static void test01(String path,String encoding) throws IOException {
       encoding=encoding==null?"UTF-8":encoding;
       FileInputStream fis = new FileInputStream(path);
       byte[] bytes = new byte[1024];
       fis.read(bytes);
       System.out.println(new String(bytes,encoding));
   }

}
