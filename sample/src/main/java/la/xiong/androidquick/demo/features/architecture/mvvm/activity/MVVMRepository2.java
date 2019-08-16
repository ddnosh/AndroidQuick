package la.xiong.androidquick.demo.features.architecture.mvvm.activity;

import android.arch.lifecycle.MutableLiveData;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;

import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.bean.NameBean;
import la.xiong.androidquick.demo.features.module.network.retrofit.TestApis;
import la.xiong.androidquick.module.network.retrofit.RetrofitManager;
import la.xiong.androidquick.module.network.retrofit.exeception.ApiException;
import la.xiong.androidquick.module.rxjava.BaseObserver;
import la.xiong.androidquick.tool.RxUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVVMRepository2 {

    private LifecycleProvider<ActivityEvent> lifecycleProvider;

    private MutableLiveData<List<NameBean>> testLiveData = new MutableLiveData<>();

    private RetrofitManager mRetrofitManager = new RetrofitManager();

    public MVVMRepository2(LifecycleProvider<ActivityEvent> activityEventLifecycleProvider) {
        lifecycleProvider = activityEventLifecycleProvider;
    }

    public MutableLiveData<List<NameBean>> getTestData() {

        mRetrofitManager.createApi(TestApis.class)
                .getTestData()
                .compose(RxUtil.applySchedulers())
                .compose(lifecycleProvider.bindToLifecycle())
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
