package la.xiong.androidquick.demo.other.rxlifecycle;

import la.xiong.androidquick.ui.mvp.BaseContract;
import la.xiong.androidquick.ui.mvp.BaseModel;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface RxLifecycleContract {
    interface Model extends BaseModel {

    }

    interface View extends BaseContract.BaseView {
        void updateView(String result);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void initData();
    }
}
