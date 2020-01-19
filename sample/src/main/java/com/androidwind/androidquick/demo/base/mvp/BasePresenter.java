package com.androidwind.androidquick.demo.base.mvp;

import com.androidwind.androidquick.constant.Constant;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BasePresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {
    protected Reference<V> mRefView;

    protected V getView() {
        if (!isViewAttached()) {
            throw new IllegalStateException(Constant.EXCEPTION_MVP_VIEW_NOT_ATTACHED);
        }
        return mRefView.get();
    }

    protected boolean isViewAttached() {
        return mRefView != null && mRefView.get() != null;
    }

    @Override
    public void attachView(V view) {
        mRefView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mRefView != null) {
            mRefView.clear();
            mRefView = null;
        }
        unSubscribe();
    }

    //增加使用CompositeDisposable方式处理防止RxJava内存泄漏的问题
    private CompositeDisposable mCompositeDisposable;

    public void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    private void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();//dispose防止下游(订阅者)收到观察者发送的消息
        }
    }

    //with rxlifecycle, 需要extends RxAppCompatActivity 和 RxFragment
    protected LifecycleProvider<Lifecycle.Event> getLifecycleProvider() {
        LifecycleProvider<Lifecycle.Event> lifecycleProvider = null;
        if (null != getView()) {
            lifecycleProvider = AndroidLifecycle.createLifecycleProvider((LifecycleOwner) getView());
        }
        return lifecycleProvider;
    }
}
