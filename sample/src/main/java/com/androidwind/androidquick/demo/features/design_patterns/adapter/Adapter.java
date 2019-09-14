package com.androidwind.androidquick.demo.features.design_patterns.adapter;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Adapter implements IAmericanCharger {
    private IChineseCharger mIChineseCharger;
    public Adapter(IChineseCharger iChineseCharger) {
        mIChineseCharger = iChineseCharger;
    }

    @Override
    public void charge4American() {
        mIChineseCharger.charge4Chinese();
    }
}
