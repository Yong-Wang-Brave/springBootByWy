package com.wy.demo.utils;

import cn.hutool.core.collection.ListUtil;
import com.github.pagehelper.PageInfo;

import java.util.Arrays;
import java.util.List;

public class PageInfoTest {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> page1 = ListUtil.page(-1, 2, integers);
        System.out.println(page1);
        PageInfo page = new PageInfoUtil().page(integers, 0, 2);
        System.out.println(page);
    }
}
