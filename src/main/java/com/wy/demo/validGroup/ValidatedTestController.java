package com.wy.demo.validGroup;


import com.wy.demo.lightspot.UnitedReturn.Result;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/validated/test")
public class ValidatedTestController {
 
    @PostMapping("/add")
    @ResponseBody
    public String add(
            @Validated(Add.class) @RequestBody ParamsVo paramsVo){
        System.out.println(String.format("add obj = {%s}",paramsVo.toString()));
        return "success";
    }
 
    @RequestMapping("/edit")
    @ResponseBody
    public String editAll(
            @Validated({Edit.class,ParamsVo.ModifyAge.class})ParamsVo paramsVo){
        System.out.println(String.format("edit obj = {%s}",paramsVo.toString()));
        return "success";
 
    }
    //校验实体类与 对应的分组
    @RequestMapping("/addAll")
    @ResponseBody
    public Result exam(@RequestBody ParamsVo  paramsVo ){
        ValidatorUtil.validateEntity(paramsVo,Add.class);
        return Result.sucess();

    };

}