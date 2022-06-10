package com.wy.demo.controller.dto;

import java.io.Serializable;

public class PageQuery implements Serializable {


    private static final long serialVersionUID = 8474358601148613957L;

    private  Integer pageNo;
    private Integer pageSize;
    public Integer getPageNo(){
        if ((null==pageNo||pageNo<1)) {
            this.pageNo=1;
        }
        return  this.pageNo;
    }
    public void setPageNo(Integer pageNo){
        this.pageNo=pageNo;
    }

    public Integer getPageSize(){
        if ((null==pageSize || pageSize<1)) {
            this.pageSize=10;
        }
        return this.pageSize;
    }
    public void  setPageSize(Integer pageSize){
        this.pageSize=pageSize;
    }
}
