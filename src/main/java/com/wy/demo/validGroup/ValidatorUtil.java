package com.wy.demo.validGroup;

import com.wy.demo.Exception.ServiceException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtil {
    private static final Validator VALIDATOR ;

    static{
        VALIDATOR= Validation.buildDefaultValidatorFactory().getValidator();
    }


    /**
     * 校验对象
     * @param object 待校验的对象
     * @param groups 待校验的组
     * @throws ServiceException
     */
    public static void validateEntity(Object object ,Class<?>... groups  ) throws ServiceException{
             //校验的 结果
        Set<ConstraintViolation<Object>> validate = VALIDATOR.validate(object, groups);
        if ((!validate.isEmpty())) {
            StringBuilder msg=new StringBuilder();
            for (ConstraintViolation<Object> objectConstraintViolation : validate) {
                msg.append(objectConstraintViolation.getMessage()).append("!");
            }
            throw  new ServiceException(msg.toString());
        }


    }

}
