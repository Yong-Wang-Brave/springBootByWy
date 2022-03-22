package com.wy.demo.mybatis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//1）json转换成实体类
//下载GSONformatPlush 插件
//操作 code  -generate-GSONformatPlush
//2) json.cn 也可以实现
@NoArgsConstructor
@Data
public class XiaoChengXu {

    private List<MessageDTO> message;
    private MetaDTO meta;

    @NoArgsConstructor
    @Data
    public static class MetaDTO {
        private String msg;
        private Integer status;
    }

    @NoArgsConstructor
    @Data
    public static class MessageDTO {
        private Integer catId;
        private String catName;
        private Integer catPid;
        private Integer catLevel;
        private Boolean catDeleted;
        private String catIcon;
        private List<ChildrenDTO> children;

        @NoArgsConstructor
        @Data
        public static class ChildrenDTO {
            private Integer catId;
            private String catName;
            private Integer catPid;
            private Integer catLevel;
            private Boolean catDeleted;
            private String catIcon;
            private List<ChildrenDTO1> children;

            @NoArgsConstructor
            @Data
            public static class ChildrenDTO1 {
                private Integer catId;
                private String catName;
                private Integer catPid;
                private Integer catLevel;
                private Boolean catDeleted;
                private String catIcon;
            }
        }
    }
}
