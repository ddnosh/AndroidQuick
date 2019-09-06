package com.androidwind.androidquick.demo.features.function.ui.fragment;

import android.os.Bundle;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.fragmentation.BaseFFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"fragment"}, description = "Example2Fragment实例")
public class Example2Fragment extends BaseFFragment {

    public static Example2Fragment newInstance() {
        return new Example2Fragment();
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_example2;
    }
}
