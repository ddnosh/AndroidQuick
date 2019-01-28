package la.xiong.androidquick.demo.module.network.retrofit.network1;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.module.network.retrofit.Gank2Apis;
import la.xiong.androidquick.demo.module.network.retrofit.GankApis;
import la.xiong.androidquick.demo.module.network.retrofit.GankRes;
import la.xiong.androidquick.demo.module.network.retrofit.base.BaseSubscriber;
import la.xiong.androidquick.network.retrofit.RetrofitManager;
import la.xiong.androidquick.tool.LogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Network1Presenter extends Network1Contract.Presenter {

    private static final String TAG = "MvpPresenter";
    private RetrofitManager mRetrofitManager;
    private Context mContext;

    @Inject
    public Network1Presenter(Context mContext, RetrofitManager mRetrofitManager) {
        this.mContext = mContext;
        this.mRetrofitManager = mRetrofitManager;
    }

    @Override
    public void initData(String type) {
        if ("get".equals(type)) {
            //获取接口实例
            Gank2Apis gank2Apis = mRetrofitManager.createApi(MyApplication.getInstance().getApplicationContext(), Gank2Apis.class);
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
            addSubscription(mRetrofitManager.createApi(MyApplication.getInstance().getApplicationContext(), GankApis.class)
                    .getHistoryDate()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseSubscriber<GankRes<List<String>>>() {
                        @Override
                        public void onNext(GankRes<List<String>> list) {
                            LogUtil.i(TAG, list.getResults().toString());
                            getView().updateView(list.getResults().toString());
                        }
                    }));
        }
    }
}
