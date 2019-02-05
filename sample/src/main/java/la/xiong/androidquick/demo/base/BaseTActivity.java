package la.xiong.androidquick.demo.base;

import android.os.Bundle;

import javax.inject.Inject;

import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.injector.component.ActivityComponent;
import la.xiong.androidquick.demo.injector.component.DaggerActivityComponent;
import la.xiong.androidquick.demo.injector.module.ActivityModule;
import la.xiong.androidquick.tool.LogUtil;

/**
 * @Description: 第二种类型的BaseActivity
 * @Detail: with: mvp, dagger2, rxjava, systembartint, permission
 * @Author: ddnosh
 * @Website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTActivity<T extends BasePresenter> extends BaseActivity {

    protected static String TAG = "BaseTActivity";

    @Inject
    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreate:" + getClass().getSimpleName());
        //dagger2
        initInjector();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
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
