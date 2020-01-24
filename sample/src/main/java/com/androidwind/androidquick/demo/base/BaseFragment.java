package com.androidwind.androidquick.demo.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidwind.androidquick.demo.base.mvp.BaseContract;
import com.androidwind.androidquick.demo.features.function.permission.PermissionActivity;
import com.androidwind.androidquick.demo.ui.activity.FrameActivity;
import com.androidwind.androidquick.module.asynchronize.eventbus.EventCenter;
import com.androidwind.androidquick.ui.base.QuickActivity;
import com.androidwind.androidquick.ui.base.QuickFragment;

import androidx.annotation.NonNull;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseFragment extends QuickFragment implements BaseContract.BaseView{

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
    protected View setDefaultVaryViewRoot() {
        return null;
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
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

    public void performCodeWithPermission(@NonNull int dialogType,
                                          final int requestCode, @NonNull String[] perms, @NonNull PermissionActivity.PermissionCallback callback) {
        if (getActivity() != null && getActivity() instanceof QuickActivity) {
            ((BaseActivity) getActivity()).performCodeWithPermission(dialogType, requestCode, perms, callback);
        }
    }
}
