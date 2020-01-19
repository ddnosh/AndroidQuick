package com.androidwind.androidquick.demo.features.architecture.mvvm.fragment;

import android.app.Application;

import com.androidwind.androidquick.demo.base.mvvm.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TomViewModel extends BaseViewModel<TomRepository> {
    public TomViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected TomRepository getRepository() {
        return new TomRepository(context);
    }

    public LiveData<String> getData() {
        final MutableLiveData<String> liveData = repository.getData();
        return liveData;
    }

    public void loadData() {
        repository.getTomData();
    }
}
