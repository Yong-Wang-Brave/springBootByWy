package com.wy.demo.自定义注解;

import com.wy.demo.controller.dto.Student;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdCardValidator implements ConstraintValidator<IdCard, Student> {
    @Override
    public void initialize(IdCard constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Student value, ConstraintValidatorContext context) {
        // 这里的 value 就是输入参数（证件号），例如：230102197701013467

        // TODO 根据自己的实际业务情况，编写校验逻辑，返回校验结果
        return validated(value);
    }

    Boolean  validated(Student cradNo){
        String pattern = "^[1-9]\\d{5}(?:18|19|20)\\d{2}(?:0[1-9]|10|11|12)(?:0[1-9]|[1-2]\\d|30|31)\\d{3}[\\dXx]$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(cradNo.getCardNo());
        return m.matches();
    }


}
