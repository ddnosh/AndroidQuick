package com.androidwind.androidquick.demo.features.architecture.mvvm.activity;

import com.trello.rxlifecycle2.LifecycleProvider;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVVMFactory1 extends ViewModelProvider.NewInstanceFactory {
    private LifecycleProvider<Lifecycle.Event> mActivityEventLifecycleProvider;
    private MVVMRepository1 mRepository;

    public MVVMFactory1(MVVMRepository1 repository, LifecycleProvider<Lifecycle.Event> activityEventLifecycleProvider) {
        mActivityEventLifecycleProvider = activityEventLifecycleProvider;
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MVVMViewModel1(mRepository, mActivityEventLifecycleProvider);
    }
}
