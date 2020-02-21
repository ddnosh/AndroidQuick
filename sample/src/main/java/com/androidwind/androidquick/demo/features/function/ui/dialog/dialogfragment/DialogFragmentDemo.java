package com.androidwind.androidquick.demo.features.function.ui.dialog.dialogfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.ui.dialog.dialogfragment.BaseDialogFragment;
import com.androidwind.androidquick.ui.dialog.dialogfragment.FDialog;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"dialog", "对话框"}, description = "DialogFragment实例")
public class DialogFragmentDemo extends BaseFragment {

    @BindView(R.id.btn_diaglogfragment_alert)
    Button btn1;
    @BindView(R.id.btn_diaglogfragment_confirm)
    Button btn2;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_dialogfragment;
    }

    @OnClick({R.id.btn_diaglogfragment_alert, R.id.btn_diaglogfragment_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_diaglogfragment_alert:
                new FDialog()
                        .setDialogLayout(R.layout.dialogfragment_alert)
                        .setConvertListener((BaseDialogFragment.ViewConvertListener) (holder, dialog) -> {
                            holder.setText(R.id.df_title, "Alert");
                            holder.setText(R.id.df_message, "This is a alert!");
                            holder.setOnClickListener(R.id.df_confirm, v -> {
                                dialog.dismiss();
                            });
                        })
                        .setMargin(60)
                        .setOutCancel(true)
                        .show(getSupportFragmentManager());
                break;
            case R.id.btn_diaglogfragment_confirm:
                new FDialog()
                        .setDialogLayout(R.layout.dialogfragment_confirm)
                        .setConvertListener((BaseDialogFragment.ViewConvertListener) (holder, dialog) -> {
                            holder.setText(R.id.df_title, "Title");
                            holder.setText(R.id.df_message, "This is content!");
                            holder.setOnClickListener(R.id.df_confirm, v -> {
                                dialog.dismiss();
                            });
                            holder.setOnClickListener(R.id.df_cancel, v -> {
                                dialog.dismiss();
                            });
                        })
                        .setMargin(60)
                        .setOutCancel(true)
                        .show(getSupportFragmentManager());
                break;
        }
    }
}
