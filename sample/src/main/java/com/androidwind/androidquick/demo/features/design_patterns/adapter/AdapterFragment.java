package com.androidwind.androidquick.demo.features.design_patterns.adapter;

import android.os.Bundle;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.features.CodeAndRunFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"adapter", "适配器"}, description = "适配器设计模式")
public class AdapterFragment extends CodeAndRunFragment {

    @Override
    public String getMarkDownUrl() {
        return "AdapterFragment";
    }

    @Override
    public String[] getItems() {
        return new String[]{"适配器"};
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                IChineseCharger iChineseCharger = new ChineseCharger();
                Adapter adapter = new Adapter(iChineseCharger);
                AmericanDevice device = new AmericanDevice(adapter);
                device.work();
                break;
        }
    }
}
