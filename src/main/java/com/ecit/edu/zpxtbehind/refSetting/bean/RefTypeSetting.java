package com.ecit.edu.zpxtbehind.refSetting.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class RefTypeSetting {
    private Integer pk_type;
    private String name;
    private Integer parent;
    private String children;
    private Date ts;
    public Integer getPk_type() {
        return pk_type;
    }

    public void setPk_type(Integer pk_type) {
        this.pk_type = pk_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
    public void setChildrenArry(ArrayList arry){
        if (arry!=null) {
            this.children = arry.toString();
        }
    }
    public ArrayList getChildrenArry(){
        if (this.children!=null&&!this.children.equals("[]")) {
            return new ArrayList<>(Arrays.asList(this.children.substring(1,this.children.length()-1).split(",")));
        }
        else {return null;}
    }
}
