package la.xiong.androidquick.demo.module.network.retrofit.network1;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.module.network.retrofit.Gank2Apis;
import la.xiong.androidquick.demo.module.network.retrofit.GankApis;
import la.xiong.androidquick.demo.module.network.retrofit.GankRes;
import la.xiong.androidquick.module.network.retrofit.RetrofitManager;
import la.xiong.androidquick.module.network.retrofit.exeception.ApiException;
import la.xiong.androidquick.module.rxjava.BaseObserver;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.ui.mvp.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Network1Presenter extends BasePresenter<Network1Contract.View> implements Network1Contract.Presenter {

    private static final String TAG = "MVPPresenter";
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
            mRetrofitManager.createApi(MyApplication.getInstance().getApplicationContext(), GankApis.class)
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
