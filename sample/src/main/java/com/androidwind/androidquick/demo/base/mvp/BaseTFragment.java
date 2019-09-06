package com.androidwind.androidquick.demo.base.mvp;

import android.os.Bundle;

import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.tool.TUtil;
import com.androidwind.androidquick.ui.mvp.BasePresenter;

/**
 * mvp
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTFragment<T extends BasePresenter> extends BaseFragment {

    public T mPresenter;
    protected String TAG = "BaseTFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = TUtil.getInstance(this, 0);

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
