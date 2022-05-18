package com.wy.demo.mybatis.generator.controller;

import com.wy.demo.mybatis.generator.dto.QueryUserDTO;
import com.wy.demo.mybatis.generator.dto.UserDTO;
import com.wy.demo.mybatis.generator.mapper.generator.UserDTOMapper;
import com.wy.demo.lightspot.UnitedReturn.Result2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "用户表")
@RestController
@RequestMapping("/users")
public class MokeController {
    @Autowired
    UserDTOMapper    userDTOMapper;

    @PostMapping("/getUser")
    @ApiOperation("用户表-获取用户信息")
    public Result2<UserDTO> getUser(@RequestBody QueryUserDTO queryUserDTO) {
        UserDTO userDTO = userDTOMapper.selectByPrimaryKey(queryUserDTO.getId());
        return  Result2.sucess(userDTO);
    }

}