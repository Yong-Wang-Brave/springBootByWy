package com.wy.demo.mybatis.entity;

import lombok.Data;

@Data
public class SortCourse {
private int catId;
private String catName;
private int catPid;
private int catLevel;
private String catDeleted;
}
