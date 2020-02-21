package com.androidwind.androidquick.demo.features.function.permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.constant.Constants;
import com.androidwind.androidquick.ui.dialog.dialogactivity.ADialog;
import com.androidwind.androidquick.ui.dialog.dialogactivity.BaseDialog;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;
import com.androidwind.permission.OnPermission;
import com.androidwind.permission.Permission;
import com.androidwind.permission.TinyPermission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import butterknife.OnClick;


/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"permission", "权限", "TinyPermission", "RxPermission"}, description = "Fragment + 权限实例")
public class PermissionFragment extends BaseFragment {

    public static final String TAG = "PermissionFragment";

    private RxPermissions rxPermissions;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_permission;
    }

    @OnClick({R.id.btn_permission_call, R.id.btn_permission_audio, R.id.btn_permission_camera})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_permission_call:
                permissionsCheck();
                break;
            case R.id.btn_permission_audio:
                tinyPermissionCheck();
                break;
            case R.id.btn_permission_camera:
                rxPermissionsCheck();
                break;
        }
    }

    private void rxPermissionsCheck() {
        rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        ToastUtil.showToast("授权成功！");
                    } else {
                        ToastUtil.showToast("授权失败！");
                    }
                });
    }

    private void tinyPermissionCheck() {
        TinyPermission.start(getActivity())
                .permission(Permission.RECORD_AUDIO)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            Toast.makeText(getActivity(), "授予所有权限成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "部分权限授予成功，部分权限未正常授予", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean permanent) {
                        if(permanent) {
                            Toast.makeText(getActivity(), "被永久拒绝授权，请手动到设置页面授予权限", Toast.LENGTH_SHORT).show();
                            TinyPermission.gotoPermissionSettings(getActivity());
                        } else {
                            Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void permissionsCheck() {
        String[] perms = {Manifest.permission.CALL_PHONE//电话
        };
        performCodeWithPermission(1, Constants.RC_PERMISSION_PERMISSION_FRAGMENT, perms, new BaseActivity.PermissionCallback() {
            @Override
            public void hasPermission(List<String> allPerms) {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:000")));
            }

            @Override
            public void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied) {
                if (hasPermanentlyDenied) {
                    EasyPermissions.goSettingsPermissions(getActivity(), 2, Constants.RC_PERMISSION_PERMISSION_FRAGMENT, Constants.RC_PERMISSION_BASE);
                }
            }

            @Override
            public void showDialog(int dialogType, final EasyPermissions.DialogCallback callback) {
                switch (dialogType){
                    case 1:
                        new ADialog(mContext)
                                .setConvertListener((BaseDialog.ViewConvertListener) (holder, dialog) -> {
                                    holder.setText(R.id.dialog_title, getString(R.string.app_name));
                                    holder.setText(R.id.dialog_info, getString(R.string.dialog_phone_permission));
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
                                    holder.setText(R.id.dialog_info, getString(R.string.dialog_phone_permission));
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
}
