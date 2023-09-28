package com.wy.demo.bloomFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BloomFilterController {
    private final BloomFilterUtil bloomFilterUtil;

    @Autowired
    public BloomFilterController(BloomFilterUtil bloomFilterUtil) {
        this.bloomFilterUtil = bloomFilterUtil;
    }

    @GetMapping("/add/{key}")
    public String addToBloomFilter(@PathVariable String key) {
        bloomFilterUtil.add(key);
        return "Key added to Bloom Filter: " + key;
    }

    @GetMapping("/contains/{key}")
    public String checkBloomFilter(@PathVariable String key) {
        boolean exists = bloomFilterUtil.contains(key);
        return "Key " + key + " exists in Bloom Filter: " + exists;
    }
}