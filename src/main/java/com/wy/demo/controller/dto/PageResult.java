package com.wy.demo.controller.dto;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@ApiModel("分页结果")
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -8838342465800221083L;

    @ApiModelProperty(value="当前页码",required = true)
    private Integer pageNo;

    @ApiModelProperty(value="每页数量",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "总记录数",required = true)
    private long totalCount;


    @ApiModelProperty(value = "总页数",required = true)
    private int totalPage;

    @ApiModelProperty(value = "结果集",required = true)
    private List<T> dataList;

    public PageResult(){}
    public PageResult(List<T> list, int pageNum, int pageSize,long total){

        this.pageNo=pageNum;
        this.pageSize=pageSize;
        this.totalCount=total;
        this.totalPage=(int)Math.ceil(total*1.0/pageSize);
        this.dataList = list;
    }

    public PageResult(List list,List destList){
        if (list==null) {
            return;
        }

        if ((list instanceof Page)) {
            Page page =(Page) list;
           this.pageNo = page.getPageNum();
           this.pageSize=page.getPageSize();
           this.totalPage=page.getPages();
           this.totalCount=page.getTotal();
           this.dataList=destList;
        }else{
            throw new RuntimeException("分页异常");
        }
    }
}
