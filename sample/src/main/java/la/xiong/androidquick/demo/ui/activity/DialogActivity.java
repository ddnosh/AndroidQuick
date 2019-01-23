package la.xiong.androidquick.demo.ui.activity;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.tool.DialogUtil;
import la.xiong.androidquick.tool.ToastUtil;
import la.xiong.androidquick.ui.dialog.CommonDialog;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DialogActivity extends BaseActivity {

    @BindView(R.id.btn_dialog_1)
    Button mDialog1;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_dialog;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.btn_dialog_1, R.id.btn_dialog_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_1:
                DialogUtil.showLoadingDialog(this);
                break;
            case R.id.btn_dialog_2:
                DialogUtil.getDialogBuilder(this)
                        .setTitle(R.string.app_name)
                        .setMessage("this is an information")
                        .setPositiveButton("Confirm")
                        .setNegativeButton("Cancel")
                        .setBtnClickCallBack(new CommonDialog.DialogBtnCallBack() {
                            @Override
                            public void onDialogButClick(boolean isConfirm) {
                                if (isConfirm) {
                                    ToastUtil.showToast("Confirm clicked");
                                }
                            }
                        }).show();
                break;
        }
    }
}
