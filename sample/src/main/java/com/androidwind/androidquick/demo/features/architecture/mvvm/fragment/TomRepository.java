package com.androidwind.androidquick.demo.features.architecture.mvvm.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import io.reactivex.functions.Function;
import com.androidwind.androidquick.demo.base.mvvm.BaseRepository;
import com.androidwind.androidquick.demo.bean.NameBean;
import com.androidwind.androidquick.demo.features.module.network.retrofit.TestApis;
import com.androidwind.androidquick.module.network.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.network.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.util.RxUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TomRepository extends BaseRepository<String> {

    private RetrofitManager mRetrofitManager = new RetrofitManager();

    public TomRepository(Context context) {
        super(context);
    }

    public MutableLiveData<String> getTomData() {
        mRetrofitManager.createApi(TestApis.class)
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
