package la.xiong.androidquick.ui.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * desc:
 * author: Will .
 * date: 2017/9/2 .
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
