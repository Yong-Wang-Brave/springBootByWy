package com.wy.demo.mybatis.byme;


import com.wy.demo.mybatis.byme.dto.Name;
import com.wy.demo.mybatis.mappers.NameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {
    @Autowired
    private NameMapper nameMapper;
    @RequestMapping("/addName")
    public String addName(@RequestBody Name name){
        nameMapper.addName(name);
        return "ok";
    }

    @RequestMapping("/getName/{id}")
    public String getName(@PathVariable("id") int id){
       Name name=nameMapper.getName(id);
       return name.toString();
    }
}

