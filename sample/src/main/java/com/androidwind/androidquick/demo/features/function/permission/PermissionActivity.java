package com.androidwind.androidquick.demo.features.function.permission;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.demo.constant.Constants;
import com.androidwind.androidquick.ui.dialog.ViewHolder;
import com.androidwind.androidquick.ui.dialog.dialogactivity.ADialog;
import com.androidwind.androidquick.ui.dialog.dialogactivity.BaseDialog;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.ACTIVITY, tags = {"permission", "权限"}, description = "Activity + 权限实例")
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
                        new ADialog(mContext)
                                .setConvertListener((BaseDialog.ViewConvertListener) (holder, dialog) -> {
                                    holder.setText(R.id.dialog_title, getString(R.string.app_name));
                                    holder.setText(R.id.dialog_info, getString(R.string.dialog_camera_permission));
                                    holder.setText(R.id.dialog_confirm, "OK");
                                    holder.setOnClickListener(R.id.dialog_confirm, v -> {
                                        dialog.dismiss();
                                        callback.onGranted();
                                    });
                                }).show();
                        break;
                    case 2:
                        new ADialog(mContext)
                                .setConvertListener((BaseDialog.ViewConvertListener) (holder, dialog) -> {
                                    holder.setText(R.id.dialog_title, getString(R.string.app_name));
                                    holder.setText(R.id.dialog_info, getString(R.string.dialog_rationale_ask_again, deniedPermsString));
                                    holder.setText(R.id.dialog_confirm, "Go to setting");
                                    holder.setOnClickListener(R.id.dialog_confirm, v -> {
                                        dialog.dismiss();
                                        callback.onGranted();
                                    });
                                }).show();
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
