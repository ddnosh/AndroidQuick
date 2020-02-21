package com.androidwind.androidquick.demo.features.module.network.retrofit;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.demo.constant.Constants;
import com.androidwind.androidquick.demo.features.function.permission.EasyPermissions;
import com.androidwind.androidquick.ui.dialog.dialogactivity.ADialog;
import com.androidwind.androidquick.ui.dialog.dialogactivity.BaseDialog;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.ToastUtil;

import java.util.List;

import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class NetworkActivity extends BaseActivity {

    private String deniedPermsString;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_network;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @OnClick( {R.id.btn_network_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_network_download:
                permissionsCheck();
                break;
        }
    }

    private void permissionsCheck() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        performCodeWithPermission(1, Constants.RC_PERMISSION_PERMISSION_ACTIVITY, perms, new PermissionCallback() {
            @Override
            public void hasPermission(List<String> allPerms) {
                LogUtil.d(TAG, "allPerms:" + allPerms.toString());
                ToastUtil.showToast("Granted");
                readyGo(DownloadActivity.class);
            }

            @Override
            public void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied) {
                LogUtil.d(TAG, "deniedPerms:" + deniedPerms.toString());
                if (hasPermanentlyDenied) {
                    StringBuilder denied = new StringBuilder();
                    if (deniedPerms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE) || deniedPerms.contains(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        denied.append("存储文件使用");
                    }
                    deniedPermsString = denied.toString();
                    EasyPermissions.goSettingsPermissions(NetworkActivity.this, 2, Constants.RC_PERMISSION_PERMISSION_ACTIVITY, Constants.RC_PERMISSION_BASE);
                }
            }

            @Override
            public void showDialog(int dialogType, final EasyPermissions.DialogCallback callback) {
                switch (dialogType) {
                    case 1:
                        new ADialog(mContext)
                                .setConvertListener((BaseDialog.ViewConvertListener) (holder, dialog) -> {
                                    holder.setText(R.id.dialog_title, getString(R.string.app_name));
                                    holder.setText(R.id.dialog_info, getString(R.string.dialog_storage_permission));
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
