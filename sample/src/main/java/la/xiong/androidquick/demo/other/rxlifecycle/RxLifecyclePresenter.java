package la.xiong.androidquick.demo.other.rxlifecycle;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import la.xiong.androidquick.ui.mvp.BasePresenter;
import la.xiong.androidquick.module.network.retrofit.RetrofitManager;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class RxLifecyclePresenter extends BasePresenter<RxLifecycleContract.View> implements RxLifecycleContract.Presenter {

    private static final String TAG = "MvpPresenter";
    private RetrofitManager mRetrofitManager;
    private Context mContext;

    @Inject
    public RxLifecyclePresenter(Context mContext, RetrofitManager mRetrofitManager) {
        this.mContext = mContext;
        this.mRetrofitManager = mRetrofitManager;
    }

    @Override
    public void initData() {
        Observable.interval(1, TimeUnit.SECONDS)//execute by every 1 second
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getView().<Object>bindToLife())
                .subscribe(new Observer<Object>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object aLong) {
                        Log.i("receive data", String.valueOf(aLong));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
