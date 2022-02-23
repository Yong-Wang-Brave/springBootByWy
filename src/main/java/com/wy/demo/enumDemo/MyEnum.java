package com.wy.demo.enumDemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public enum MyEnum {
//这个初始化要放发到前面
    L0("没有结果",0){
        @Override
        public  boolean isTargetFasting(Double bloodSugerValue)
        {return null==bloodSugerValue;}

    @Override
    public boolean isAfterMeal(Double bloodSugerValue) {
        return null==bloodSugerValue;
    }
},
    L1("L1",20){
        @Override
        public  boolean isTargetFasting(Double bloodSugerValue)
        {return bloodSugerValue.compareTo(3.0)<0;}

        @Override
        public boolean isAfterMeal(Double bloodSugerValue) {
            return false;
        }
    },
    L2("L2",40){
        @Override
        public  boolean isTargetFasting(Double bloodSugerValue)
        {return bloodSugerValue.compareTo(3.0)>=0 && bloodSugerValue.compareTo(3.9)<0;}

        @Override
        public boolean isAfterMeal(Double bloodSugerValue) {
            return false;
        }
    };

    private String remark;//备注

    private int basicScore;//基础分

    MyEnum(String remark, int basicScore) {
        this.remark = remark;
        this.basicScore = basicScore;
    }

public abstract boolean isTargetFasting(Double bloodSugerValue);
public abstract boolean isAfterMeal(Double bloodSugerValue);

//根据传入的值与类型判断返回的枚举类
public static MyEnum    judgeLevel(Double bloodSugerValue,BloodSugerType bloodSugerType){

    if ((null==bloodSugerType || null ==bloodSugerValue)) {
        return MyEnum.L0;
    }
    //values()是所有枚举的集合
    List<MyEnum> collect = Arrays.stream(values()).collect(Collectors.toList());
    Optional<MyEnum> first =null;
    switch (bloodSugerType){
        case Fasting:
         first = collect.stream().filter(item -> item.isTargetFasting(bloodSugerValue)).findFirst();
            break;
        case AfterMeal:
         first = collect.stream().filter(item -> item.isAfterMeal(bloodSugerValue)).findFirst();
            break;
    }

    if (first.isPresent()) {
        return first.get();
    }else{
        return MyEnum.L0;
    }


}


}



