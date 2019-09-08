package com.androidwind.androidquick.demo.base.mvp;

import android.os.Bundle;

import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.demo.tool.TUtil;
import com.androidwind.androidquick.ui.mvp.BasePresenter;
import com.androidwind.androidquick.util.LogUtil;

/**
 * mvp
 *
 * @Author: ddnosh
 * @Website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTActivity<T extends BasePresenter> extends BaseActivity {

    protected static String TAG = "BaseTActivity";

    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = TUtil.getInstance(this, 0);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        LogUtil.i(TAG, "onCreate:" + getClass().getSimpleName());

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        LogUtil.i(TAG, "onDestroy:" + getClass().getSimpleName());

        super.onDestroy();
    }

}
