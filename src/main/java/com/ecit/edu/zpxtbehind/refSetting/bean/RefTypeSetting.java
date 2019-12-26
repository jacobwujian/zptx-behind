package com.ecit.edu.zpxtbehind.refSetting.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class RefTypeSetting {
    private int pk_type;
    private String name;
    private String parent;
    private String children;
    private Date ts;
    public int getPk_type() {
        return pk_type;
    }

    public void setPk_type(int pk_type) {
        this.pk_type = pk_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
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
    public void setParentArry(ArrayList arry){
        if (arry!=null) {
            this.parent = arry.toString();
        }
    }
    public ArrayList getParentArry(){
        if (this.parent!=null) {
            return new ArrayList<>(Arrays.asList(this.parent.split(",")));
        }
        else {return null;}
    }
}
