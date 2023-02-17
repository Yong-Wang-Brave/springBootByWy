package com.wy.demo.jihe;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * 双向映射
 */
public class BitMapTest {
    public static void main(String[] args) {
        HashBiMap<Object, Object> biMap = HashBiMap.create();

        biMap.put("beijing","北京");
        BiMap<Object, Object> inverse = biMap.inverse();
        System.out.println(inverse);
    }
}
