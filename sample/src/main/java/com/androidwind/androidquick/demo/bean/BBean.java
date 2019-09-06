package com.androidwind.androidquick.demo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BBean implements Serializable, MultiItemEntity {

    private String type;

    private String value;

    public BBean(String value) {
        this.value = value;
    }

    public BBean(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public int itemType;
}
