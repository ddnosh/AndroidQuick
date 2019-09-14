package com.androidwind.androidquick.demo.features.design_patterns.adapter;

import android.os.Bundle;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"adapter", "适配器"}, description = "适配器设计模式")
public class AdapterFragment extends BaseFragment {
    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        IChineseCharger iChineseCharger = new ChineseCharger();
        Adapter adapter = new Adapter(iChineseCharger);
        AmericanDevice device = new AmericanDevice(adapter);
        device.work();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_design_pattern_adapter;
    }
}
