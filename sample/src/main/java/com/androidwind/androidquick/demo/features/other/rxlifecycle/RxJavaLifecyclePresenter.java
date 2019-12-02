package com.androidwind.androidquick.demo.features.other.rxlifecycle;

import android.content.Context;

import com.androidwind.androidquick.ui.mvp.BasePresenter;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.RxUtil;
import com.androidwind.androidquick.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class RxJavaLifecyclePresenter extends BasePresenter<RxJavaLifecycleContract.View> implements RxJavaLifecycleContract.Presenter {

    private static final String TAG = "MVPPresenter";
    private Context mContext;

    @Inject
    public RxJavaLifecyclePresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void initDataRxLifecycle() {
        Observable.interval(1, TimeUnit.SECONDS)//execute by every 1 second
                .compose(RxUtil.applySchedulers())
                .compose(getView().<Object>bindToLife()) //使用rxlifecycle
                .subscribe(new Observer<Object>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object aLong) {
                        LogUtil.i(TAG, String.valueOf(aLong));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void initDataCompositeDisposable() { //没用rxlifecycle
        addSubscribe(Observable.interval(1, TimeUnit.SECONDS)//execute by every 1 second
                .compose(RxUtil.applySchedulers())
                .subscribe(aLong -> {
                    LogUtil.i(TAG, String.valueOf(aLong));
                    ToastUtil.showToast(aLong+"");
                }));
    }
}
