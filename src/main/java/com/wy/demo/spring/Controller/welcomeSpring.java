package com.wy.demo.spring.Controller;


import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.spring.DTO.InParam;
import com.wy.demo.shejimoshi.策略模式22.TypeFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Think
 * @Title: welocome
 * @ProjectName token-authentication
 * @Description: TODO
 * @date 2019/1/1815:4112211
 */
@RestController
@Log4j2
public class welcomeSpring {

@Autowired
  private TypeFactory typeFactory;


    /**
     * 这是一种设计模式，代码看着更优雅
     * @param param
     * @return
     */
    @PostMapping("/getFactory")
    public Result get(@RequestBody InParam param) {

        return typeFactory.getType(param.getType()).getTypeList(param);
    }



}
