package com.wy.demo.mybatis.mappers;


import com.wy.demo.mybatis.entity.Customer;
import com.wy.demo.mybatis.typehandler.Encrypt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author huan.fu 2021/5/18 - 上午10:43
 */
@Mapper
public interface CustomerMapper {

    int addCustomer(@Param("phone") Encrypt phone, @Param("address") String address);

    Customer findCustomer(@Param("phone") Encrypt phone);
}
