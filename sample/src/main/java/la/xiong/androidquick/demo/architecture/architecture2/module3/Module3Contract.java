package la.xiong.androidquick.demo.architecture.architecture2.module3;

import la.xiong.androidquick.demo.architecture.architecture2.mvp.BaseContract;

public interface Module3Contract {

    interface View extends BaseContract.BaseView {
        void toastSomething();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void tryToPrintSomething();
    }
}
