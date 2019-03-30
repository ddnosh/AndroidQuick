package la.xiong.androidquick.ui.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import la.xiong.androidquick.constant.Constant;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BasePresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {
    protected Reference<V> mRefView;

    protected V getView() {
        if (!isViewAttached()) throw new IllegalStateException(Constant.EXCEPTION_MVP_VIEW_NOT_ATTACHED);
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
