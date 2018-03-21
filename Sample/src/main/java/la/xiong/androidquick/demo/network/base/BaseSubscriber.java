package la.xiong.androidquick.network;

import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ToastUtil;
import rx.Subscriber;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private static String TAG = "BaseSubscriber";

    @Override
    public void onCompleted() {

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
