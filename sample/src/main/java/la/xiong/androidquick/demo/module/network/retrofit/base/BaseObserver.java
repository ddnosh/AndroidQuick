package la.xiong.androidquick.demo.module.network.retrofit.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import la.xiong.androidquick.network.retrofit.CommonEmptyDataException;
import la.xiong.androidquick.network.retrofit.CommonHttpException;
import la.xiong.androidquick.network.retrofit.CommonInternalException;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private static String TAG = "BaseSubscriber";

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof CommonHttpException) {
            if (!handleMzHttpException((CommonHttpException) e)) {
                ToastUtil.showToast(((CommonHttpException) e).getErrorMsg());
            }
        } else if (e instanceof CommonInternalException) {
            if (!handleMzInternalException((CommonInternalException) e)) {
                ToastUtil.showToast(((CommonInternalException) e).getErrorMsg());
            }
        } else if (e instanceof CommonEmptyDataException) {
            handleMzEmptyDataException((CommonEmptyDataException) e);
        } else {
            //未知异常信息
            if (!handleOtherException(e)) {
                LogUtil.e(TAG, e.getMessage());
                ToastUtil.showToast(e.getMessage());
            }
        }

        onPostError(e);
    }

    public boolean handleMzHttpException(CommonHttpException exception) {
        return false;
    }

    public boolean handleMzInternalException(CommonInternalException exception) {
        return false;
    }

    public boolean handleMzEmptyDataException(CommonEmptyDataException exception) {
        return false;
    }

    public boolean handleOtherException(Throwable e) {
        return false;
    }

    public void onPostError(Throwable e) {
        //Override by subclass
    }
}
