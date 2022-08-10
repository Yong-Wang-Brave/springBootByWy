package com.wy.demo.mybatis.mappers;

import com.wy.demo.mybatis.byme.dto.Name;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface NameMapper {
    int addName(@Param(value="name")Name  name);

    Name getName(@Param(value="id")Integer id);
}

