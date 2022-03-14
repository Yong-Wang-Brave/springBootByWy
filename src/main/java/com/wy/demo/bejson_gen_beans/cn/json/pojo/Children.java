/**
  * Copyright 2022 json.cn 
  */
package com.wy.demo.bejson_gen_beans.cn.json.pojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Auto-generated: 2022-03-10 9:26:26
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Children {

    private int cat_id;
    private String cat_name;
    private int cat_pid;
    private int cat_level;
    private boolean cat_deleted;
    private String cat_icon;
    private List<Children> children;
    public void setCat_id(int cat_id) {
         this.cat_id = cat_id;
     }
     public int getCat_id() {
         return cat_id;
     }

    public void setCat_name(String cat_name) {
         this.cat_name = cat_name;
     }
     public String getCat_name() {
         return cat_name;
     }

    public void setCat_pid(int cat_pid) {
         this.cat_pid = cat_pid;
     }
     public int getCat_pid() {
         return cat_pid;
     }

    public void setCat_level(int cat_level) {
         this.cat_level = cat_level;
     }
     public int getCat_level() {
         return cat_level;
     }

    public void setCat_deleted(boolean cat_deleted) {
         this.cat_deleted = cat_deleted;
     }
     public boolean getCat_deleted() {
         return cat_deleted;
     }

    public void setCat_icon(String cat_icon) {
         this.cat_icon = cat_icon;
     }
     public String getCat_icon() {
         return cat_icon;
     }

    public void setChildren(List<Children> children) {
         this.children = children;
     }
     public List<Children> getChildren() {
         return children;
     }

}