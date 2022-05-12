package com.wy.demo.mybatis.generator.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryUserDTO {
    @ApiModelProperty(value = "用户ID")
    private Long id;
}
