package la.xiong.androidquick.demo.base;

import android.content.Context;
import android.view.View;

import la.xiong.androidquick.ui.base.QuickFragment;
import la.xiong.androidquick.ui.eventbus.EventCenter;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseFragment extends QuickFragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }
}
