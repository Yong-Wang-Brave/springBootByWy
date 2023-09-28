package com.wy.demo.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Component;

@Component
public class BloomFilterUtil {
    private static final int EXPECTED_INSERTIONS = 1000; // 预期插入的数据量
    private static final double FPP = 0.01; // 误判率

    private BloomFilter<String> bloomFilter;

    public BloomFilterUtil() {
        bloomFilter = BloomFilter.create(Funnels.unencodedCharsFunnel(), EXPECTED_INSERTIONS, FPP);
    }

    public void add(String key) {
        bloomFilter.put(key);
    }

    public boolean contains(String key) {
        return bloomFilter.mightContain(key);
    }
}