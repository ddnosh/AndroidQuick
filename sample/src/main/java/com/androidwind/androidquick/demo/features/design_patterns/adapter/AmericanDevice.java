package com.androidwind.androidquick.demo.features.design_patterns.adapter;

import com.androidwind.androidquick.util.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class AmericanDevice {

    private IAmericanCharger mIAmericanCharger;

    public AmericanDevice(IAmericanCharger IAmericanCharger) {
        mIAmericanCharger = IAmericanCharger;
    }

    public void work() {
        mIAmericanCharger.charge4American();
        LogUtil.i("AmericanDevice", "美规电器开始运行！");
    }
}
