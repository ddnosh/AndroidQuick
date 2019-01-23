package la.xiong.androidquick.demo.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BasePresenter<V> {
    public V mView;

    protected CompositeSubscription mCompositeSubscription;
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void initVM(V v) {
        mView = v;
    }

    public void onDestroy() {
        mView = null;
        unSubscribe();
    }
}
