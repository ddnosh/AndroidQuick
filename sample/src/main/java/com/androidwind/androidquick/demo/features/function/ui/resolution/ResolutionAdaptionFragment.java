package com.androidwind.androidquick.demo.features.function.ui.resolution;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.features.function.ui.webview.WebViewActivity;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"resolution", "分辨率"}, description = "屏幕分辨率自动适配实例")
public class ResolutionAdaptionFragment extends BaseFragment {

    @BindView(R.id.btn_click)
    Button btnClick;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_resolutionadaption;
    }

    @OnClick(R.id.btn_click)
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("url", "https://blog.csdn.net/ddnosh/article/details/78941302");
        readyGo(WebViewActivity.class, bundle);
    }
}
