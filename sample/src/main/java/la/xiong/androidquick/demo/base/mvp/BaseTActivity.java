package la.xiong.androidquick.demo.base.mvp;

import android.os.Bundle;

import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.tool.TUtil;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.ui.mvp.BasePresenter;

/**
 * mvp
 *
 * @Author: ddnosh
 * @Website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTActivity<T extends BasePresenter> extends BaseActivity {

    protected static String TAG = "BaseTActivity";

    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreate:" + getClass().getSimpleName());

        mPresenter = TUtil.getInstance(this, 0);

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
}
