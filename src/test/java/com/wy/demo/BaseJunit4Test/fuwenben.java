package com.wy.demo.BaseJunit4Test;

import org.apache.commons.lang.StringUtils;

public class fuwenben {

    public static void main(String[] args) {
        String aa="   我听见\n大大大\t，    大大大。     大   ";
        String content = Html2Text.getContent(aa);
       // System.out.println(content);

        String abbreviate = StringUtils.abbreviate(aa, 8);
        String s = StringUtils.deleteWhitespace(aa);
        System.out.println(s);
    }
}
