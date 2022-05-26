package com.wy.demo.自定义注解;

import com.wy.demo.controller.dto.Student;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//常规写法
//这种实现方式的弊端是：参数校验逻辑与业务处理逻辑掺杂在一起，看起来并不是那么简洁。
public class AccountController0 {
    public Student getAccount(@RequestParam("cardNo") String cardNo) {
        if (validated(cardNo)) {
            // 校验证件号是否有效，若无效则提示无效证件号
            System.out.println("校验通过");
        }

        // TODO 验证通过后，进行业务处理


        return null;
    }
   Boolean  validated(String cradNo){
         String pattern = "^[1-9]\\d{5}(?:18|19|20)\\d{2}(?:0[1-9]|10|11|12)(?:0[1-9]|[1-2]\\d|30|31)\\d{3}[\\dXx]$";
         Pattern r = Pattern.compile(pattern);
         Matcher m = r.matcher(cradNo);
     return m.matches();
     }

}
