package com.wy.demo.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.NumberUtil;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.RoundingMode;
import java.util.List;

/**
 * 分页参数合理化工具
 * @param <T>
 */
@Data
@NoArgsConstructor
public class PageInfoUtil<T> {

    public PageInfo<T> page(List<T> list,int pageNum,int pageSize){
        //初始化返回结果
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setTotal(0L);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageSize(0);
        pageInfo.setPages(0);
        pageInfo.setList(ListUtil.empty());
        if (CollUtil.isNotEmpty(list)) {
            pageInfo.setTotal(list.size());
            pageInfo.setPages(Double.valueOf(NumberUtil.div(list.size(),pageSize,0, RoundingMode.UP)).intValue());
            List<T> pageList = ListUtil.page(pageNum - 1, pageSize, list);
            pageInfo.setSize(pageList.size());
            pageInfo.setList(pageList);
        }
        return  pageInfo;

    }



}
