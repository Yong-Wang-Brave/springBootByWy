package com.wy.demo.mybatis.generator.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class UserDTO implements Serializable {
    @ApiModelProperty(value = "用户ID")
    private Long id;
@ApiModelProperty(value = "用户名")
    private String name;
@ApiModelProperty(value = "用户年龄")
    private Short age;
@ApiModelProperty(value = "用户城市")
    private String city;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }
}