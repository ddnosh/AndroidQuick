package la.xiong.androidquick.demo.architecture.mvvm.activity;

import io.reactivex.Observable;
import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.module.network.retrofit.GankApis;
import la.xiong.androidquick.module.network.retrofit.RetrofitManager;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVVMRepository {

    private RetrofitManager mRetrofitManager = new RetrofitManager();

    public Observable getGankResData() {
        return mRetrofitManager.createApi(MyApplication.getInstance().getApplicationContext(), GankApis.class)
                .getHistoryDate();
    }
}
