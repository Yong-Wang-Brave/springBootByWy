package com.wy.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Think
 * @Title: User
 * @ProjectName token-authentication
 * @Description: TODO
 * @date 2019/1/1815:47
 */
@Data
@ApiModel("测试用户")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    //内部类
    public List<Book> books;
    @ApiModelProperty(value="id",notes="主键",required = true)
    Long id;
    @ApiModelProperty(value="姓名你妹1")
    String username;
String name;
    @ApiModelProperty(value="密码13332")
    String password;

    Integer   delFlag;
    Timestamp createdAt;
    Timestamp updatedAt;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Book {
        String name;
        String category;
    }
    public User(int i, String 小红a,List<Book> book) {
        this.delFlag=i;
        this.name=小红a;
        this.books=book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", delFlag=" + delFlag +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
