package com.androidwind.androidquick.demo.features.other.code;

import android.os.Bundle;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.ui.view.CommonToolBar;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.BindView;
import us.feras.mdv.MarkdownView;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"Code", "代码"}, description = "一种好看的Android代码显示方式")
public class CodeFragment extends BaseFragment {
    @BindView(R.id.common_tool_bar)
    CommonToolBar mCommonToolBar;
    @BindView(R.id.mv_code)
    MarkdownView mMvCode;

    private Bundle mBundle;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        // mBundle = getActivity().getIntent().getExtras();
        // mCommonToolBar.setTitle(mBundle.getString("title"));
        mMvCode.loadMarkdownFile("file:///android_asset/HttpRequestCallbackString.md", "file:///android_asset/css-themes/classic.css");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_other_code;
    }
}
