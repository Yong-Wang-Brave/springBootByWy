package com.wy.demo.enumDemo;

import org.springframework.util.StringUtils;

public enum BloodSugerType {
    Fasting("空腹"){
        @Override
        public boolean isTargetTimePoint(String code) {
            return "MorningRise".equals(code);
        }
    },
   AfterMeal("餐后"){
       @Override
       public boolean isTargetTimePoint(String code) {
           return false;
       }
   };


    private String remark;
    BloodSugerType(String remark){this.remark=remark;}
    public abstract boolean isTargetTimePoint(String code);
}
