package com.androidwind.androidquick.demo.features.architecture.mvvm.activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVVMFactory2 extends ViewModelProvider.NewInstanceFactory {
    private MVVMRepository2 mRepository;

    public MVVMFactory2(MVVMRepository2 repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MVVMViewModel2(mRepository);
    }
}
