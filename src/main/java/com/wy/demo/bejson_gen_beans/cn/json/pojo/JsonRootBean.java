/**
  * Copyright 2022 json.cn 
  */
package com.wy.demo.bejson_gen_beans.cn.json.pojo;
import java.util.List;

/**
 * Auto-generated: 2022-03-10 9:26:26
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class JsonRootBean {

    private List<Message> message;
    private Meta meta;
    public void setMessage(List<Message> message) {
         this.message = message;
     }
     public List<Message> getMessage() {
         return message;
     }

    public void setMeta(Meta meta) {
         this.meta = meta;
     }
     public Meta getMeta() {
         return meta;
     }

}