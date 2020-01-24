package com.androidwind.androidquick.demo.base.mvp_databinding;

import android.content.Context;
import android.os.Bundle;

import com.androidwind.androidquick.demo.MyApplication;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.base.mvp.BasePresenter;
import com.androidwind.androidquick.demo.injector.component.DaggerFragmentComponent;
import com.androidwind.androidquick.demo.injector.component.FragmentComponent;
import com.androidwind.androidquick.demo.injector.module.FragmentModule;

import javax.inject.Inject;

import androidx.databinding.ViewDataBinding;

/**
 * mvp:集成dagger2 + databinding
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTVFragment<T extends BasePresenter, V extends ViewDataBinding> extends BaseFragment {

    @Inject
    public T mPresenter;
    protected Context mContext;
    protected String TAG = "BaseTVFragment";
    public V getViewBinding(){
        return (V) getBinding();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

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
