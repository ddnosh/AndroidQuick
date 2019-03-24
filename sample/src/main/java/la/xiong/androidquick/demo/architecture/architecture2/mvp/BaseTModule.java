package la.xiong.androidquick.demo.architecture.architecture2.mvp;

import android.view.View;

import la.xiong.androidquick.demo.architecture.architecture2.BaseModule;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BaseTModule<T extends BasePresenter> extends BaseModule {

    public T mPresenter;

    protected BaseTModule(View rootView) {
        super(rootView);
    }

    @Override
    protected void initView() {
        mPresenter = MVPUtil.getT(this);
        mPresenter.initV(this);
    }

    @Override
    protected void release() {
        mPresenter.unInitV();
    }
}
