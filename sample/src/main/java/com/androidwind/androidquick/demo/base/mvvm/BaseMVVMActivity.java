package com.androidwind.androidquick.demo.base.mvvm;

import android.os.Bundle;

import com.androidwind.androidquick.demo.base.BaseActivity;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseMVVMActivity<T extends BaseViewModel> extends BaseActivity {

    protected T viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewModel == null) {
            viewModel = getViewModel();
        }
    }

    protected abstract T getViewModel();
}
