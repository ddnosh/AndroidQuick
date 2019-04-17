package la.xiong.androidquick.module.rxjava;

import io.reactivex.functions.Consumer;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseConsumer<T> implements Consumer<T> {
    @Override
    public void accept(T t) throws Exception {
        if (t == null) {
            onError();
        } else {
            onSuccess(t);
        }
    }

    public abstract void onSuccess(T t) ;
    public abstract void onError() ;
}
