package la.xiong.androidquick.demo.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import la.xiong.androidquick.demo.constant.Constants;
import la.xiong.androidquick.ui.base.BaseContract;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BasePresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {
    protected Reference<V> mRefView;

//    protected CompositeSubscription mCompositeSubscription;
//
//    protected void unSubscribe() {
//        if (mCompositeSubscription != null) {
//            mCompositeSubscription.unsubscribe();
//        }
//    }
//
//    protected void addSubscription(Subscription subscription) {
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription = new CompositeSubscription();
//        }
//        mCompositeSubscription.add(subscription);
//    }

    protected V getView() {
        if (!isViewAttached()) throw new IllegalStateException(Constants.EXCEPTION_MVP_VIEW_NOT_ATTACHED);
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
    }
}
