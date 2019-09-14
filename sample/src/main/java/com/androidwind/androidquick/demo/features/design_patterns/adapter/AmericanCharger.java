package com.androidwind.androidquick.demo.features.design_patterns.adapter;

import com.androidwind.androidquick.util.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class AmericanCharger implements IAmericanCharger {
    @Override
    public void charge4American() {
        LogUtil.i("AmericanCharger", "do American charge");
    }
}
