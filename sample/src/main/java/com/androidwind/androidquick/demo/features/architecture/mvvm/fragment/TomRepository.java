package com.androidwind.androidquick.demo.features.architecture.mvvm.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.androidwind.androidquick.demo.base.mvvm.BaseRepository;
import com.androidwind.androidquick.demo.bean.NameBean;
import com.androidwind.androidquick.demo.features.module.network.retrofit.TestApis;
import com.androidwind.androidquick.module.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.util.RxUtil;

import java.util.List;

import io.reactivex.functions.Function;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TomRepository extends BaseRepository<String> {

    public TomRepository(Context context) {
        super(context);
    }

    public MutableLiveData<String> getTomData() {
        RetrofitManager.getInstance().createApi(TestApis.class)
                .getTestData()
                .map(new Function<List<NameBean>, String>() {
                    @Override
                    public String apply(List<NameBean> nameBeans) throws Exception {
                        String result = nameBeans.toString();
                        Thread.sleep(2000);
                        return result;
                    }
                })
                .compose(RxUtil.applySchedulers())
                .subscribe(new BaseObserver<String>() {

                               @Override
                               public void onError(ApiException exception) {

                               }

                               @Override
                               public void onSuccess(String result) {
                                   liveData.setValue(result);
                               }
                           }
                );
        return liveData;
    }
}
