package com.wy.demo.mybatis.mappers;

import com.wy.demo.entity.User;
import com.wy.demo.entity.UserReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Think
 * @Title: UserMapper
 * @ProjectName token-authentication
 * @Description: TODO
 * @date 2019/1/1823:04
 */

@Mapper
public interface UserMapper {

         Integer updateUser(UserReq userReq);

   @Select("select * from t_user where username=#{username}")
   public User getUser(String username);



//        @Delete("delete from where id=#{id}")
//        public int deleteDeptById(Integer id);
//
//        @Options(useGeneratedKeys = true, keyProperty = "id")
//        @Insert("insert into department(departmentName) values (#{departmentName})")
//        public int insertDept(Department department);
//
//
//        @Update("update department set departmentName=#{departmentName} where id=#{id}")
//        public int updateDept(Department department);

}
