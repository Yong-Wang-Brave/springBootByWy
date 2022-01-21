package com.wy.demo.mybatiesInterceptor.MD5;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class TestDes {
    public static void main(String[] args) {
        // 待加密内容
        String str = "testpasswordChiper";
        // 密码，长度要是8的倍数 密钥随意定
        String password = "12345678";
        byte[] encrypt = encrypt(str.getBytes(), password);
        System.out.println(Charset.defaultCharset().name()); // 输出：GBK

        String str1 = new String(encrypt, Charset.forName("ISO-8859-1"));
        byte[] after = str1.getBytes(Charset.forName("ISO-8859-1"));
        System.out.println("before: "  + "--" + Arrays.toString(encrypt));
        System.out.println("after : "  + "--" + Arrays.toString(after));


    }


    /**
     * 加密
     *
     * @param datasource
     *            byte[]
     * @param password
     *            String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象,ENCRYPT_MODE用于将 Cipher 初始化为加密模式的常量
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(datasource); // 按单部分操作加密或解密数据，或者结束一个多部分操作
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

}
