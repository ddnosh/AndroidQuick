package la.xiong.androidquick.module.rxjava;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import la.xiong.androidquick.module.network.retrofit.exeception.ApiException;
import la.xiong.androidquick.module.network.retrofit.exeception.ExeceptionEngine;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }
    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onError(ExeceptionEngine.handleException(e));
    }

    @Override
    public void onComplete() {
    }
    public abstract void onError(ApiException exception) ;
    public abstract void onSuccess( T t) ;
}
