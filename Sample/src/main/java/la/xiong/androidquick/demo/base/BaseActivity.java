package la.xiong.androidquick.demo.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import la.xiong.androidquick.demo.ui.activity.FrameActivity;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.ui.base.QuickActivity;
import la.xiong.androidquick.ui.eventbus.EventCenter;

/**
 * @Description: 第一种类型的BaseActivity
 * @Detail: 不是每个activity都要用到的方法均保留在此;
 * 每个activity都要用到的方法则在每个activity中实现;
 * @Author: ddnosh
 * @Website http://blog.csdn.net/ddnosh
 */
public abstract class BaseActivity extends QuickActivity {

    protected static String TAG = "BaseActivity";

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        LogUtil.i(TAG, eventCenter.getEventCode() + "");
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.LEFT;
    }

    @Override
    protected boolean isApplySystemBarTint() {
        return true;
    }

    @Override
    protected boolean isLoadDefaultTitleBar() {
        return false;
    }

    @Override
    protected Intent getGoIntent(Class<?> clazz) {
        if (BaseFragment.class.isAssignableFrom(clazz)) {
            Intent intent = new Intent(this, FrameActivity.class);
            intent.putExtra("fragmentName", clazz.getName());
            return intent;
        } else {
            return super.getGoIntent(clazz);
        }
    }
}
