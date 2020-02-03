package com.androidwind.androidquick.demo.features.function.ui.varypagestatus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.ui.multipleviewstatus.MultipleStatusView;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"状态"}, description = "多种页面状态(加载+空+错误)实例")
public class VaryPageStatusFragment extends BaseFragment {
    @BindView(R.id.btn_ui_pagestatus_loading)
    Button mLoading;
    @BindView(R.id.btn_ui_pagestatus_empty)
    Button mEmpty;
    @BindView(R.id.btn_ui_pagestatus_error)
    Button mError;
    @BindView(R.id.btn_ui_pagestatus_networkerror)
    Button mNetworkError;
    @BindView(R.id.container)
    LinearLayout mContainer;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mLayoutStatusView;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_pagestatus;
    }

    @OnClick({R.id.btn_ui_pagestatus_loading, R.id.btn_ui_pagestatus_empty, R.id.btn_ui_pagestatus_error, R.id.btn_ui_pagestatus_networkerror})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ui_pagestatus_loading:
                mLayoutStatusView.showLoading();
                break;
            case R.id.btn_ui_pagestatus_empty:
                mLayoutStatusView.showEmpty();
                break;
            case R.id.btn_ui_pagestatus_error:
                mLayoutStatusView.showError();
                break;
            case R.id.btn_ui_pagestatus_networkerror:
                mLayoutStatusView.showNoNetwork();
                break;
            default:
                break;
        }
    }

}
