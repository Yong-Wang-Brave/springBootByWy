package com.wy.demo.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @ApiModelProperty(value="id",notes="主键",required = true)
    private Long id ;
    @Autowired
    private ApplicationContext applicationContext;
    int i;
    @ApiModelProperty(value="姓名")
    String userName;
    String passWord;
    public User(int i, String toString) {
    }
}
