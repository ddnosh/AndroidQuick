package com.androidwind.androidquick.demo.features.architecture.mvvm.activity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVVMFactory1 extends ViewModelProvider.NewInstanceFactory {
    private LifecycleProvider<ActivityEvent> mActivityEventLifecycleProvider;
    private MVVMRepository1 mRepository;

    public MVVMFactory1(MVVMRepository1 repository, LifecycleProvider<ActivityEvent> activityEventLifecycleProvider) {
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
