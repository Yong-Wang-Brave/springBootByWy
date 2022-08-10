package com.wy.demo.mybatis.自定义类型转换没用;

public class User {
    private Integer id;
    private String name;
    private Double money;
    private GenderEnum gender;
 
    public GenderEnum getGender() {
        return gender;
    }
 
    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Double getMoney() {
        return money;
    }
 
    public void setMoney(Double money) {
        this.money = money;
    }
 
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", gender=" + gender +
                '}';
    }
}