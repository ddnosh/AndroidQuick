package com.androidwind.androidquick.demo.features.module.network.retrofit.network1;

import android.content.Context;

import com.androidwind.androidquick.demo.base.mvp.BasePresenter;
import com.androidwind.androidquick.demo.constant.Constants;
import com.androidwind.androidquick.demo.features.module.network.retrofit.Gank2Apis;
import com.androidwind.androidquick.demo.features.module.network.retrofit.GankApis;
import com.androidwind.androidquick.demo.features.module.network.retrofit.GankRes;
import com.androidwind.androidquick.demo.features.module.network.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.util.LogUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Network1Presenter extends BasePresenter<Network1Contract.View> implements Network1Contract.Presenter {

    private static final String TAG = "MVPPresenter";
    private Context mContext;

    @Inject
    public Network1Presenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void initData(String type) {
        if ("get".equals(type)) {
            //获取接口实例
            Gank2Apis gank2Apis = RetrofitManager.INSTANCE.getRetrofit(Constants.GANK_API_URL).create(Gank2Apis.class);
            //调用方法得到一个Call
            Call<GankRes<List<String>>> call = gank2Apis.getHistoryDate();
            //进行网络请求
            call.enqueue(new Callback<GankRes<List<String>>>() {
                @Override
                public void onResponse(Call<GankRes<List<String>>> call, Response<GankRes<List<String>>> response) {
                    getView().updateView(response.body().getResults().toString());
                }

                @Override
                public void onFailure(Call<GankRes<List<String>>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            RetrofitManager.INSTANCE.getRetrofit(Constants.GANK_API_URL).create(GankApis.class)
                    .getHistoryDate()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<GankRes<List<String>>>() {

                        @Override
                        public void onError(ApiException exception) {
                            LogUtil.e(TAG, "error:" + exception.getMessage());
                        }

                        @Override
                        public void onSuccess(GankRes<List<String>> listGankRes) {
                            LogUtil.i(TAG, listGankRes.getResults().toString());
                            getView().updateView(listGankRes.getResults().toString());
                        }
                    });
        }
    }

}
