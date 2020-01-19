package com.androidwind.androidquick.demo.features.architecture.mvvm.fragment;

import android.content.Context;

import com.androidwind.androidquick.demo.base.mvvm.BaseRepository;
import com.androidwind.androidquick.demo.bean.NameBean;
import com.androidwind.androidquick.demo.constant.Constants;
import com.androidwind.androidquick.demo.features.module.network.retrofit.RetrofitManager;
import com.androidwind.androidquick.demo.features.module.network.retrofit.TestApis;
import com.androidwind.androidquick.demo.tool.RxUtil;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
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
        RetrofitManager.INSTANCE.getRetrofit(Constants.GANK_API_URL).create(TestApis.class)
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
