package com.wy.demo.自定义注解;

import com.wy.demo.controller.dto.Student;
import com.wy.demo.lightspot.UnitedReturn.Result;
import com.wy.demo.lightspot.UnitedReturn.Result2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
public class AccountController {
    @PostMapping("/cardNo")
    public Result getAccount(@RequestBody @IdCard Student  Student) {
        return Result.sucess();
    }
}
