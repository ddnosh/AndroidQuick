package com.androidwind.androidquick.demo.base.mvvm;

import android.os.Bundle;

import com.androidwind.androidquick.demo.base.BaseFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseMVVMFragment<T extends BaseViewModel> extends BaseFragment {

    protected T viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (viewModel == null) {
            viewModel = getViewModel();
        }
    }

    protected abstract T getViewModel();
}
