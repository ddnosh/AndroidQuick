package com.androidwind.androidquick.demo.features.design_patterns.responsibilitychain;

import com.androidwind.androidquick.util.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class WeightFilter extends BaseFilter {
    @Override
    public void doDetailFilter(String name) {
        if (name.contains("5kg")) {
            LogUtil.d("WeightFilter", "the product's weight is 5kg, pass!");
        }
    }
}
