package la.xiong.androidquick.demo.features.architecture.mvvm.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.base.mvvm.BaseRepository;
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
public class TomRepository extends BaseRepository<List<NameBean>> {

    private RetrofitManager mRetrofitManager = new RetrofitManager();

    public TomRepository(Context context) {
        super(context);
    }

    public MutableLiveData<List<NameBean>> getTomData() {
        mRetrofitManager.createApi(MyApplication.getInstance().getApplicationContext(), TestApis.class)
                .getTestData()
                .compose(RxUtil.applySchedulers())
                .subscribe(new BaseObserver<List<NameBean>>() {

                               @Override
                               public void onError(ApiException exception) {

                               }

                               @Override
                               public void onSuccess(List<NameBean> testBeans) {
                                   liveData.setValue(testBeans);
                               }
                           }
                );
        return liveData;
    }
}
