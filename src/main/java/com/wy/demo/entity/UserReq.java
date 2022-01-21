package com.wy.demo.entity;

import com.wy.demo.mybatiesInterceptor.FillType;
import com.wy.demo.mybatiesInterceptor.SensitiveField;
import com.wy.demo.mybatiesInterceptor.SensitiveTable;

import java.io.Serializable;
import java.sql.Timestamp;
@SensitiveTable("test")
public class UserReq implements Serializable {

    Long id;
    String username;
    String password;
    Integer   delFlag;
    Timestamp createdAt;
    Timestamp updatedAt;
    @SensitiveField(columnName = "pass_word",original = "password",fillType = FillType.ENCRYPT)
    byte[] passwordChiper;
    @SensitiveField(columnName = "pass_word",original = "password",fillType = FillType.HASH)
    byte[] passwordHash;

    public byte[] getPasswordChiper() {
        return passwordChiper;
    }

    public void setPasswordChiper(byte[] passwordChiper) {
        this.passwordChiper = passwordChiper;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
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
}
