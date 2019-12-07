package com.androidwind.androidquick.demo.base.mvp_dagger2;

import android.os.Bundle;

import com.androidwind.androidquick.demo.MyApplication;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.base.mvp.BasePresenter;
import com.androidwind.androidquick.demo.injector.component.DaggerFragmentComponent;
import com.androidwind.androidquick.demo.injector.component.FragmentComponent;
import com.androidwind.androidquick.demo.injector.module.FragmentModule;

import javax.inject.Inject;

/**
 * mvp:集成dagger2
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTFragment<T extends BasePresenter> extends BaseFragment {
    @Inject
    public T mPresenter;
    protected String TAG = "BaseTFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //dagger2
        initInjector();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

        super.onDestroy();
    }

    protected void initInjector() {

    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .applicationComponent(MyApplication.getApplicationComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

}
