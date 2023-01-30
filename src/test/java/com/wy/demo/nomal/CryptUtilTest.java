package com.wy.demo.nomal;


import com.wy.demo.DemoApplication;
import com.wy.demo.自定义注解.demo3.CryptUtil;
import com.wy.demo.自定义注解.demo3.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class CryptUtilTest {
    @Autowired
    private CryptUtil cryptUtil;

    @Test
    public void encrypt(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMobileNo("111");
        cryptUtil.insertFill(customerDTO);
    }

    @Test
    public void dencrypt() throws Exception {

String mobileNo="111aa";
        String decrypt = cryptUtil.decrypt(mobileNo);
        System.out.println(decrypt);
    }
}
