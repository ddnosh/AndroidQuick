package com.androidwind.androidquick.demo.features.architecture.mvvm.activity;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import com.androidwind.androidquick.demo.MyApplication;
import com.androidwind.androidquick.demo.bean.NameBean;
import com.androidwind.androidquick.demo.features.module.network.retrofit.GankApis;
import com.androidwind.androidquick.demo.features.module.network.retrofit.TestApis;
import com.androidwind.androidquick.module.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVVMRepository1 {

    private MutableLiveData<List<NameBean>> testLiveData = new MutableLiveData<>();

    private RetrofitManager mRetrofitManager = new RetrofitManager();

    public Observable getGankResData() {
        return mRetrofitManager.createApi(GankApis.class)
                .getHistoryDate();
    }

    public MutableLiveData<List<NameBean>> getTestData() {

        mRetrofitManager.createApi(TestApis.class)
                .getTestData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<NameBean>>() {

                               @Override
                               public void onError(ApiException exception) {

                               }

                               @Override
                               public void onSuccess(List<NameBean> testBeans) {
                                   testLiveData.setValue(testBeans);
                               }
                           }
                );
        return testLiveData;
    }
}
