package com.wy.demo.自定义注解;

import com.wy.demo.lightspot.UnitedReturn.Result;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdCardValidator.class)
@Documented
public @interface IdCard {
    String message() default "cardNo invalid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
} 
