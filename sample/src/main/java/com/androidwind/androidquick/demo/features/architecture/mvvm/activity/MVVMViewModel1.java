package com.androidwind.androidquick.demo.features.architecture.mvvm.activity;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import com.androidwind.androidquick.demo.features.module.network.retrofit.GankRes;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.util.RxUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVVMViewModel1 extends ViewModel {

    private MVVMRepository1 repository;
    private LifecycleProvider<ActivityEvent> lifecycleProvider;

    MutableLiveData<List<String>> liveData = new MutableLiveData<>();

    public MVVMViewModel1(MVVMRepository1 repository, LifecycleProvider<ActivityEvent> activityEventLifecycleProvider) {
        this.repository = repository;
        this.lifecycleProvider = activityEventLifecycleProvider;
    }

    public MutableLiveData<List<String>> getData() {
        return liveData;
    }

    public void getGankResData() {
        repository.getGankResData()
                .flatMap(new Function<GankRes<List<String>>, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(GankRes<List<String>> s) throws Exception {
                        Log.d("RxJava", "flatMap1 " + Thread.currentThread().getName());
                        return Observable.just(s.getResults());
                    }
                })
                .compose(RxUtil.applySchedulers())
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe(new BaseObserver<List<String>>() {
                    @Override
                    public void onError(ApiException exception) {
                        Log.e("tag", "error" + exception.getMessage());
                        liveData.setValue(new ArrayList<>());
                    }

                    @Override
                    public void onSuccess(List<String> s) {
                        Log.e("tag", "onSuccess");
                        liveData.setValue(s);
                    }
                });
    }
}
