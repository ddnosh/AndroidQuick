package com.androidwind.androidquick.demo.features.architecture.segment.type2.module3;

import com.androidwind.androidquick.demo.features.architecture.segment.type2.mvp.BasePresenter;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Module3Presenter extends BasePresenter<Module3> implements Module3Contract.Presenter {
    @Override
    public void tryToPrintSomething() {
        mView.toastSomething();
    }
}
