package com.androidwind.androidquick.demo.features.design_patterns.adapter;

import com.androidwind.androidquick.util.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ChineseCharger implements IChineseCharger {
    @Override
    public void charge4Chinese() {
        LogUtil.i("AmericanCharger", "do Chinese charge");
    }
}
