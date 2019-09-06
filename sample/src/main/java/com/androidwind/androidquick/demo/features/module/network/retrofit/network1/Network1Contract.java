package com.androidwind.androidquick.demo.features.module.network.retrofit.network1;

import com.androidwind.androidquick.ui.mvp.BaseContract;
import com.androidwind.androidquick.ui.mvp.BaseModel;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface Network1Contract {
    interface Model extends BaseModel {

    }

    interface View extends BaseContract.BaseView {
        void updateView(String result);
//        void refreshAppUpdateProgress(int progress);
        void downloadCompleted(String path);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void initData(String type);
    }
}
