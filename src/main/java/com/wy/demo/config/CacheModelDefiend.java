package com.wy.demo.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum CacheModelDefiend {
    HOUR_HALF(CacheModelDefiend.KEY_HOUR_HALF,30*60*1000),
    HOUR_ONE(CacheModelDefiend.KEY_HOUR_ONE,30*60*1000);
    private  String name;
    private long ttl;
    public static final String  KEY_HOUR_HALF="HOUR_HALF";
    public static final String  KEY_HOUR_ONE="HOUR_ONE";

}
