package com.androidwind.androidquick.demo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class CBean implements Serializable, MultiItemEntity {

    public static final int SINGLE_TEXT = 1;
    public static final int SINGLE_IMG = 2;
    public static final int TEXT_IMG = 3;

    private String text;
    private String img;

    public CBean(String text, String img) {
        this.text = text;
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public int itemType;
}
