package com.androidwind.androidquick.demo.features.function.ui.bartop;

import android.os.Bundle;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.ui.view.CommonToolBar;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.BindView;


/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"toolbar"}, description = "CommonToolBar实例")
public class CommonToolBarFragment extends BaseFragment {
    @BindView(R.id.common_tool_bar)
    CommonToolBar mCommonToolBar;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_commontoolbar;
    }
}
