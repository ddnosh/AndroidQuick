package la.xiong.androidquick.demo.base;

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

        void showSuccess();

        void showFail();

        <T> LifecycleTransformer<T> bindToLife();
    }
}
