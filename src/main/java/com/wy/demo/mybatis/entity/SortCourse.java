package com.wy.demo.mybatis.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wy.demo.utils.serializer.PhoneSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortCourse {
    private static final long serialVersionUID = -7843539314352860529L;
    private int catId;
    @JsonSerialize(using = PhoneSerializer.class)
    private String catName;
    private int catPid;
    private int catLevel;
    private String catDeleted;
}
