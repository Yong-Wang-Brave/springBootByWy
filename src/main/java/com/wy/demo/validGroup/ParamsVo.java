package com.wy.demo.validGroup;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
 @Data
public class ParamsVo {
 
    //特殊用于修改年龄 标记使用 灵活放置位置
    public interface ModifyAge {
    }
 
    //年龄是1-120之间有效
    public static final String AGE_REG = "^[Y|N]$";
 
    @NotBlank(
            groups = {Edit.class, ParamsVo.ModifyAge.class},
            message = "失败，id不能为空"
    )
    private String id;
 
    @NotBlank(groups = {Add.class,Edit.class}, message = "失败，名字不能为空")
    private String name;
 
    //自定义一个正则
    @NotBlank(groups = {Add.class, ParamsVo.ModifyAge.class},
            message = "失败，请填写age"
    )
    @Pattern(regexp = AGE_REG,groups = {Add.class, ParamsVo.ModifyAge.class},
            message = "失败，请填写正确age"
    )
    private String sex;
 

}