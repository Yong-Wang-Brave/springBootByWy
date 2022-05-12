package com.wy.demo.mybatis.controller;


import com.wy.demo.mybatis.entity.Customer;
import com.wy.demo.mybatis.mappers.CustomerMapper;
import com.wy.demo.mybatis.typehandler.Encrypt;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huan.fu 2021/5/18 - 上午10:51
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("addCustomer")
    public String addCustomer(@RequestParam("phone") String phone, @RequestParam("address") String address) {
        int result = customerMapper.addCustomer(new Encrypt(phone), address);
        return "添加结果: " + result;
    }

    @GetMapping("findCustomer")
    public Customer findCustomer(@RequestParam("phone") String phone) {
        return customerMapper.findCustomer(new Encrypt(phone));
    }
}
