package com.androidwind.androidquick.demo.base.mvp_databinding;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.androidwind.androidquick.demo.MyApplication;
import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.demo.injector.component.ActivityComponent;
import com.androidwind.androidquick.demo.injector.component.DaggerActivityComponent;
import com.androidwind.androidquick.demo.injector.module.ActivityModule;
import com.androidwind.androidquick.ui.mvp.BasePresenter;
import com.androidwind.androidquick.util.LogUtil;

import javax.inject.Inject;

/**
 * mvp:集成dagger2 + databinding
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTVActivity<T extends BasePresenter, V extends ViewDataBinding> extends BaseActivity {
    @Inject
    public T mPresenter;
    protected Context mContext;
    protected String TAG = "BaseTVFragment";

    public V getBinding() {
        return (V) binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreate:" + getClass().getSimpleName());
        //dagger2
        initInjector();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        LogUtil.i(TAG, "onDestroy:" + getClass().getSimpleName());
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

        super.onDestroy();
    }

    protected void initInjector() {

    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
