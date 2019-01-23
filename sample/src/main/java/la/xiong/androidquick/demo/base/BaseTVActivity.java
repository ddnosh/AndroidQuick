package la.xiong.androidquick.demo.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import javax.inject.Inject;

import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.injector.component.ActivityComponent;
import la.xiong.androidquick.demo.injector.component.DaggerActivityComponent;
import la.xiong.androidquick.demo.injector.module.ActivityModule;
import la.xiong.androidquick.tool.LogUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTVActivity<T extends BasePresenter, V extends ViewDataBinding> extends BaseActivity {
    @Inject
    public T mPresenter;
    protected Context mContext;
    protected String TAG = "BaseTVFragment";

    public V getBinding() {
        return (V) binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreate:" + getClass().getSimpleName());
        //dagger2
        initInjector();
        if (this instanceof BaseView)
            mPresenter.initVM(this);//如果实现了BaseView这个接口的类，就将实例化过的View和Model传入
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy:" + getClass().getSimpleName());
    }

    protected void initInjector() {

    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
