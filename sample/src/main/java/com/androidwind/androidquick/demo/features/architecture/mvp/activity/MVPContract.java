package com.androidwind.androidquick.demo.features.architecture.mvp.activity;

import com.androidwind.androidquick.ui.mvp.BaseContract;
import com.androidwind.androidquick.ui.mvp.BaseModel;
import com.androidwind.androidquick.ui.mvp.BasePresenter;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface MVPContract {
    interface Model extends BaseModel {

    }

    interface View extends BaseContract.BaseView {
        void refreshView(String result);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void initData();
    }
}
