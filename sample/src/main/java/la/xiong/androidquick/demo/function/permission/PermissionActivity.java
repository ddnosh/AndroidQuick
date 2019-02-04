package la.xiong.androidquick.demo.function.permission;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.constant.Constants;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ToastUtil;
import la.xiong.androidquick.ui.dialog.dialogactivity.CommonDialog;
import la.xiong.androidquick.ui.permission.EasyPermissions;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class PermissionActivity extends BaseActivity {

    private String deniedPermsString;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_permission;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_permission_camera, R.id.btn_permission_fragment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_permission_camera:
                permissionsCheck();
                break;
            case R.id.btn_permission_fragment:
                readyGo(PermissionFragment.class);
        }
    }

    private void permissionsCheck() {
        String[] perms = {Manifest.permission.CAMERA};
        performCodeWithPermission(1, Constants.RC_PERMISSION_PERMISSION_ACTIVITY, perms, new PermissionCallback() {
            @Override
            public void hasPermission(List<String> allPerms) {
                LogUtil.d(TAG, "allPerms:" + allPerms.toString());
                ToastUtil.showToast("Granted");
            }

            @Override
            public void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied) {
                LogUtil.d(TAG, "deniedPerms:" + deniedPerms.toString());
                if (hasPermanentlyDenied) {
                    StringBuilder denied = new StringBuilder();
                    if (deniedPerms.contains(Manifest.permission.CAMERA)) {
                        denied.append("扫描二维码使用");
                    }
                    deniedPermsString = denied.toString();
                    EasyPermissions.goSettingsPermissions(PermissionActivity.this, 2, Constants.RC_PERMISSION_PERMISSION_ACTIVITY, Constants.RC_PERMISSION_BASE);
                }
            }

            @Override
            public void showDialog(int dialogType, final EasyPermissions.DialogCallback callback) {
                switch (dialogType) {
                    case 1:
                        getDialogBuilder(mContext).
                                setTitle(getString(R.string.app_name)).
                                setMessage(getString(R.string.dialog_camera_permission)).
                                setPositiveButton("OK").
                                setBtnClickCallBack(new CommonDialog.DialogBtnCallBack() {
                                    @Override
                                    public void onDialogButClick(boolean isConfirm) {
                                        if (isConfirm)
                                            callback.onGranted();
                                    }
                                }).show().setCancelable(false);
                        break;
                    case 2:
                        getDialogBuilder(mContext).
                                setTitle(getString(R.string.app_name)).
                                setMessage(getString(R.string.dialog_rationale_ask_again, deniedPermsString)).
                                setPositiveButton("Go to setting").
                                setBtnClickCallBack(new CommonDialog.DialogBtnCallBack() {
                                    @Override
                                    public void onDialogButClick(boolean isConfirm) {
                                        if (isConfirm)
                                            callback.onGranted();
                                    }
                                }).show().setCancelable(false);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.d(TAG, "requestCode:" + requestCode);
        if (requestCode == Constants.RC_PERMISSION_BASE) {
            permissionsCheck();
        }
    }

}
