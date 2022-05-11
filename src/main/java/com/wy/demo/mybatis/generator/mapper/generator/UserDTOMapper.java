package com.wy.demo.mybatis.generator.mapper.generator;

import com.wy.demo.mybatis.generator.dto.UserDTO;
import com.wy.demo.mybatis.generator.dto.UserDTOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
@Mapper
public interface UserDTOMapper {
    int countByExample(UserDTOExample example);

    int deleteByExample(UserDTOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDTO record);

    int insertSelective(UserDTO record);

    List<UserDTO> selectByExampleWithRowbounds(UserDTOExample example, RowBounds rowBounds);

    List<UserDTO> selectByExample(UserDTOExample example);

    UserDTO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserDTO record, @Param("example") UserDTOExample example);

    int updateByExample(@Param("record") UserDTO record, @Param("example") UserDTOExample example);

    int updateByPrimaryKeySelective(UserDTO record);

    int updateByPrimaryKey(UserDTO record);
}