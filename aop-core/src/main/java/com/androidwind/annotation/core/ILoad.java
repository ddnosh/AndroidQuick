package com.androidwind.annotation.core;

import com.androidwind.annotation.annotation.TagInfo;

import java.util.Map;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface ILoad {
    void load(Map<String, TagInfo> params);
}