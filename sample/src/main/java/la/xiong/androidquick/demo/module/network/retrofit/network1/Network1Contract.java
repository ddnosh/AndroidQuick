package la.xiong.androidquick.demo.module.network.retrofit.network1;

import la.xiong.androidquick.demo.base.BaseModel;
import la.xiong.androidquick.demo.base.BasePresenter;
import la.xiong.androidquick.demo.base.BaseView;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface Network1Contract {
    interface Model extends BaseModel {

    }

    interface View extends BaseView {
        void updateView(String result);
//        void refreshAppUpdateProgress(int progress);
        void downloadCompleted(String path);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void initData(String type);
    }
}
