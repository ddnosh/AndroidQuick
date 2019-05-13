package la.xiong.androidquick.demo.features.architecture.mvvm.activity;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.bean.NameBean;
import la.xiong.androidquick.demo.features.module.network.retrofit.GankApis;
import la.xiong.androidquick.demo.features.module.network.retrofit.TestApis;
import la.xiong.androidquick.module.network.retrofit.RetrofitManager;
import la.xiong.androidquick.module.network.retrofit.exeception.ApiException;
import la.xiong.androidquick.module.rxjava.BaseObserver;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVVMRepository1 {

    private MutableLiveData<List<NameBean>> testLiveData = new MutableLiveData<>();

    private RetrofitManager mRetrofitManager = new RetrofitManager();

    public Observable getGankResData() {
        return mRetrofitManager.createApi(MyApplication.getInstance().getApplicationContext(), GankApis.class)
                .getHistoryDate();
    }

    public MutableLiveData<List<NameBean>> getTestData() {

        mRetrofitManager.createApi(MyApplication.getInstance().getApplicationContext(), TestApis.class)
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
