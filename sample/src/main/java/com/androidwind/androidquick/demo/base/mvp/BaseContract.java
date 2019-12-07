package com.androidwind.androidquick.demo.base.mvp;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface BaseContract {

    interface BasePresenter<T extends BaseView> {

        void attachView(T view);

        void detachView();
    }


    interface BaseView {

    }

    interface BaseModel {

    }
}
