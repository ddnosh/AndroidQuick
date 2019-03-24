package la.xiong.androidquick.ui.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> {

        void attachView(T view);

        void detachView();
    }


    interface BaseView {

        void showLoading();

        void showSuccess();

        void showFail();

        void showRetry();

        void showMessage(String message);

        <T> LifecycleTransformer<T> bindToLife();
    }
}
