package com.wy.demo.validGroup;


import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}