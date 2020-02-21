package com.androidwind.androidquick.demo.base.rxlifecycle;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
/**
 * @Description: Activity的一个基类, 提供丰富的功能, 旨在开发一款新APP应用的时候直接继承使用
 * @Detail: 1.   默认提供六种转场动画, 如需自定义, 请在子类调用父类的onCreate后添加overridePendingTransition
 * 2.   封装页面跳转传参
 * 3.   EventBus事件总线
 * 4.   管理所有启动的activity
 * 5.   设备屏幕信息
 * 6.   监听网络状态变化
 * 7.   支持butterknife 8+, databinding
 * 8.   页面跳转: readyGo, readyGoThenKill, readyGoForResult
 * 9.   提供页面状态展示: loading, network error, error, empty
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidwind.androidquick.R;
import com.androidwind.androidquick.demo.base.mvp.BaseContract;
import com.androidwind.androidquick.demo.features.function.permission.EasyPermissions;
import com.androidwind.androidquick.module.asynchronize.eventbus.EventBusUtil;
import com.androidwind.androidquick.module.asynchronize.eventbus.EventCenter;
import com.androidwind.androidquick.ui.dialog.ViewHolder;
import com.androidwind.androidquick.ui.dialog.dialogactivity.ADialog;
import com.androidwind.androidquick.ui.dialog.dialogactivity.BaseDialog;
import com.androidwind.androidquick.ui.multipleviewstatus.MultipleStatusView;
import com.androidwind.androidquick.ui.receiver.NetStateReceiver;
import com.androidwind.androidquick.util.StringUtil;
import com.androidwind.androidquick.util.immersion.StatusBarUtil;
import com.androidwind.androidquick.util.manager.QActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class RxQuickActivity extends RxAppCompatActivity implements EasyPermissions.PermissionWithDialogCallbacks, BaseContract.BaseView {

    protected static String TAG = "QuickActivity";

    protected Context mContext = null;

    /**
     * loading view status controller: empty/loading/error
     */
    protected MultipleStatusView mLayoutStatusView;

    /**
     * overridePendingTransition mode
     */
    public enum TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
    }

    /**
     * butterknife 8+ support
     */
    private Unbinder mUnbinder;

    /**
     * databinding
     */
    private View mainView;
    protected ViewDataBinding binding;

    /**
     * default toolbar
     */
    protected TextView tvTitle;
    protected TextView tvRight;
    protected Toolbar toolbar;
    protected FrameLayout toolbarLayout;
    private LinearLayout contentView;
    /**
     * dialog
     */
    protected ADialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // activity manager
        QActivity.addActivity(this);
        // animation
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
        // extras
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        // eventbus
        if (isBindEventBus()) {
            EventBusUtil.register(this);
        }
        // layout
        int layoutId = getContentViewLayoutID();
        if (layoutId != 0) {
            try {
                // databinding support
                binding = DataBindingUtil.setContentView(this, layoutId);
                if (binding != null) {
                    mainView = binding.getRoot();
                } else {
                    setContentView(layoutId);
                }

            } catch (NoClassDefFoundError e) {
                setContentView(layoutId);
            }
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        // system status bar immersion
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        // network status
        NetStateReceiver.registerNetworkStateReceiver(mContext);

        initViewsAndEvents(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        // 添加toolbar布局
        if (isLoadDefaultTitleBar()) {
            initToolBarView();
            initContentView();
            contentView.addView(toolbarLayout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            View view = getContentView(layoutResID, contentView);
            if (view == null) {

                LayoutInflater.from(this).inflate(layoutResID, contentView, true);

                super.setContentView(contentView, new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                super.setContentView(view, new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
            }
        } else {
            super.setContentView(layoutResID);
        }
        mUnbinder = ButterKnife.bind(this);
    }

    protected View getContentView(int layoutResID, LinearLayout contentView) {
        return null;
    }

    protected void initContentView() {
        contentView = new LinearLayout(this);
        contentView.setOrientation(LinearLayout.VERTICAL);
    }

    private void initToolBarView() {
        toolbarLayout = new FrameLayout(this);
        LayoutInflater.from(this).inflate(R.layout.layout_toolbar, toolbarLayout,
                true);
        tvTitle = (TextView) toolbarLayout.findViewById(R.id.tv_title);
        tvRight = (TextView) toolbarLayout.findViewById(R.id.tv_right);
        toolbar = (Toolbar) toolbarLayout.findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        initToolBarSet(actionBar);
    }

    protected void initToolBarSet(ActionBar actionBar) {
        actionBar.setDisplayShowTitleEnabled(false);

        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void finish() {
        super.finish();
        QActivity.removeActivity(this);
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        NetStateReceiver.unRegisterNetworkStateReceiver(mContext);
        if (isBindEventBus()) {
            EventBusUtil.unregister(this);
        }
        dismissLoadingDialog();
    }

    /**
     * default title bar
     *
     * @return
     */
    protected abstract boolean isLoadDefaultTitleBar();

    /**
     * bind eventbus
     *
     * @return
     */
    protected abstract boolean isBindEventBus();

    /**
     * get bundle data
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewLayoutID();

    /**
     * when event coming
     *
     * @param eventCenter
     */
    protected abstract void onEventComing(EventCenter eventCenter);

    /**
     * init all views and add events
     */
    protected abstract void initViewsAndEvents(Bundle savedInstanceState);

    /**
     * toggle overridePendingTransition
     *
     * @return
     */
    protected abstract boolean toggleOverridePendingTransition();

    /**
     * get the overridePendingTransition mode
     */
    protected abstract TransitionMode getOverridePendingTransitionMode();

    /**
     * 会被子类覆盖去封装 跳转到不同的activity 或者fragment的页面
     *
     * @param clazz
     * @return
     */
    protected Intent getGoIntent(Class<?> clazz) {
        return new Intent(this, clazz);
    }


    /**
     * startActivity
     *
     * @param clazz
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = getGoIntent(clazz);
        startActivity(intent);
    }

    /**
     * startActivity with  flag
     * @param clazz
     * @param flag
     */
    protected void readyGo(Class<?> clazz, int flag) {
        Intent intent = getGoIntent(clazz);
        intent.setFlags(flag);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = getGoIntent(clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity with bundle and flag
     * @param clazz
     * @param bundle
     * @param flag
     */
    protected void readyGo(Class<?> clazz, Bundle bundle, int flag) {
        Intent intent = getGoIntent(clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        intent.setFlags(flag);
        startActivity(intent);
    }


    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = getGoIntent(clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = getGoIntent(clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = getGoIntent(clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(EventCenter eventCenter) {
        if (null != eventCenter) {
            onEventComing(eventCenter);
        }
    }

    /**
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     * Android6.0权限控制
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     */
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (mPermissonCallbacks == null || !mPermissonCallbacks.containsKey(requestCode)) {
            return;
        }
        if (mPermissions == null || !mPermissions.containsKey(requestCode)) {
            return;
        }

        // 100% granted permissions
        if (mPermissions.get(requestCode).length == perms.size()) {
            mPermissonCallbacks.get(requestCode).hasPermission(Arrays.asList(mPermissions.get(requestCode)));
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (mPermissonCallbacks == null || !mPermissonCallbacks.containsKey(requestCode)) {
            return;
        }
        if (mPermissions == null || !mPermissions.containsKey(requestCode)) {
            return;
        }

        //granted permission
        List<String> grantedPerms = new ArrayList<>();
        for (String perm : mPermissions.get(requestCode)) {
            if (!perms.contains(perm)) {
                grantedPerms.add(perm);
            }
        }

        //check has permission denied permanently
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            mPermissonCallbacks.get(requestCode).noPermission(perms, grantedPerms, true);
        } else {
            mPermissonCallbacks.get(requestCode).noPermission(perms, grantedPerms, false);
        }
    }

    @Override
    public void onDialog(int requestCode, int dialogType, EasyPermissions.DialogCallback callback) {
        if (mPermissonCallbacks == null || !mPermissonCallbacks.containsKey(requestCode)) {
            return;
        }
        mPermissonCallbacks.get(requestCode).showDialog(dialogType, callback);
    }

    public void showLoadingDialog() {
        showLoadingDialog(null);
    }

    public void showLoadingDialog(String tips) {
        if (!isFinishing()) {
            try {
                if (loadingDialog == null) {
                    loadingDialog = new ADialog(this);
                    loadingDialog.setDialogLayout(R.layout.dialog_loading);
                    if (!StringUtil.isEmpty(tips)) {
                        loadingDialog.setConvertListener(new BaseDialog.ViewConvertListener() {
                            @Override
                            public void convertView(@NotNull ViewHolder viewHolder, @NotNull BaseDialog baseDialog) {
                                viewHolder.setText(R.id.tip, "正在努力加载...");
                            }
                        });
                    }
                    loadingDialog.show();
                } else {
                    //相同的上下文，无需重新创建
                    if (loadingDialog.getContext() == this) {
                        loadingDialog.show();
                    } else {
                        loadingDialog.dismiss();
                        loadingDialog = new ADialog(this);
                        loadingDialog.setDialogLayout(R.layout.dialog_loading);
                        if (!StringUtil.isEmpty(tips)) {
                            loadingDialog.setConvertListener(new BaseDialog.ViewConvertListener() {
                                @Override
                                public void convertView(@NotNull ViewHolder viewHolder, @NotNull BaseDialog baseDialog) {
                                    viewHolder.setText(R.id.tip, "正在努力加载...");
                                }
                            });
                        }
                        loadingDialog.show();
                    }
                }

            } catch (Throwable e) {
            }
        }
    }

    public void dismissLoadingDialog() {
        try {
            if (!isFinishing() && loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        } catch (Throwable e) {
        }
    }

}

