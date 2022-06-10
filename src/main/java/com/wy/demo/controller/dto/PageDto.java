package com.wy.demo.controller.dto;

import lombok.Data;

@Data
public class PageDto extends  PageQuery{
    private int catId;
    private String catName;
    private int catPid;
    private int catLevel;
    private String catDeleted;
}
