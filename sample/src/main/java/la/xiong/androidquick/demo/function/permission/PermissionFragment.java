package la.xiong.androidquick.demo.function.permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.util.List;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.constant.Constants;
import la.xiong.androidquick.tool.DialogUtil;
import la.xiong.androidquick.ui.dialog.CommonDialog;
import la.xiong.androidquick.ui.permission.EasyPermissions;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class PermissionFragment extends BaseFragment {

    public static final String TAG = "PermissionFragment";

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_permission;
    }

    @OnClick({R.id.btn_permission_call})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_permission_call:
                permissionsCheck();
                break;
        }
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
                        DialogUtil.getDialogBuilder(mContext).
                                setTitle(getString(R.string.app_name)).
                                setMessage(getString(R.string.dialog_phone_permission)).
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
                        DialogUtil.getDialogBuilder(mContext).
                                setTitle(getString(R.string.app_name)).
                                setMessage(getString(R.string.dialog_phone_permission)).
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
}
