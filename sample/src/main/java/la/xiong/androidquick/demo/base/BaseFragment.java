package la.xiong.androidquick.demo.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import la.xiong.androidquick.demo.ui.activity.FrameActivity;
import la.xiong.androidquick.ui.base.QuickFragment;
import la.xiong.androidquick.eventbus.EventCenter;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseFragment extends QuickFragment {
    @Override
    protected void initCreate() {

    }

    @Override
    protected void initDestroy() {

    }

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

    @Override
    protected Intent getGoIntent(Class<?> clazz) {
        if (BaseFragment.class.isAssignableFrom(clazz)) {
            Intent intent = new Intent(getActivity(), FrameActivity.class);
            intent.putExtra("fragmentName", clazz.getName());
            return intent;
        } else {
            return super.getGoIntent(clazz);
        }
    }

}
