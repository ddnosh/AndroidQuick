package la.xiong.androidquick.demo.architecture.mvp.activity_dagger;

import la.xiong.androidquick.ui.mvp.BaseContract;
import la.xiong.androidquick.ui.mvp.BaseModel;
import la.xiong.androidquick.ui.mvp.BasePresenter;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface MVPDaggerContract {
    interface Model extends BaseModel {

    }

    interface View extends BaseContract.BaseView {
        void refreshView(String result);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void initData();
    }
}
