package com.androidwind.androidquick.demo.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidwind.androidquick.demo.base.mvp.BaseContract;
import com.androidwind.androidquick.demo.features.function.permission.EasyPermissions;
import com.androidwind.androidquick.demo.ui.activity.FrameActivity;
import com.androidwind.androidquick.module.asynchronize.eventbus.EventCenter;
import com.androidwind.androidquick.ui.base.QuickActivity;
import com.androidwind.androidquick.util.LogUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * 通用抽象方法的实现集合
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseActivity extends QuickActivity implements BaseContract.BaseView{

    protected static String TAG = "BaseActivity";

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected boolean isApplyButterKnife() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        LogUtil.INSTANCE.i(TAG, eventCenter.getEventCode() + "");
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

    /**
     * request permission
     *
     * @param dialogType  dialogType
     * @param requestCode requestCode
     * @param perms       permissions
     * @param callback    callback
     */
    public void performCodeWithPermission(int dialogType,
                                          final int requestCode, @NonNull String[] perms, @NonNull PermissionCallback callback) {
        if (EasyPermissions.hasPermissions(this, perms)) {
            callback.hasPermission(Arrays.asList(perms));
        } else {
            if (mPermissonCallbacks == null) {
                mPermissonCallbacks = new HashMap<>();
            }
            mPermissonCallbacks.put(requestCode, callback);

            if (mPermissions == null) {
                mPermissions = new HashMap<>();
            }
            mPermissions.put(requestCode, perms);

            EasyPermissions.requestPermissions(this, dialogType, requestCode, perms);
        }
    }

    private Map<Integer, PermissionCallback> mPermissonCallbacks = null;
    private Map<Integer, String[]> mPermissions = null;

    public interface PermissionCallback {
        /**
         * has all permission
         *
         * @param allPerms all permissions
         */
        void hasPermission(List<String> allPerms);

        /**
         * denied some permission
         *
         * @param deniedPerms          denied permission
         * @param grantedPerms         granted permission
         * @param hasPermanentlyDenied has permission denied permanently
         */
        void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied);

        /**
         * @param dialogType dialogType
         * @param callback   callback from easypermissions
         */
        void showDialog(int dialogType, EasyPermissions.DialogCallback callback);
    }

}
