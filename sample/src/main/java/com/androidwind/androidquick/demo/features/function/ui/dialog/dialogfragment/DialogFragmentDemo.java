package com.androidwind.androidquick.demo.features.function.ui.dialog.dialogfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.ui.dialog.dialogfragment.CommonDialog;
import com.androidwind.androidquick.ui.dialog.dialogfragment.ViewConvertListener;
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
                CommonDialog.Companion.newInstance()
                        .setDialogLayout(R.layout.dialogfragment_alert)
                        .setConvertListener((ViewConvertListener) (holder, dialog) -> {
                            ((TextView)holder.getView(R.id.df_title)).setText("Alert");
                            ((TextView)holder.getView(R.id.df_message)).setText("This is a alert!");
                            holder.setOnClickListener(R.id.df_confirm, v -> {
                                dialog.dismiss();
                            });
                        })
                        .setMargin(60)
                        .setOutCancel(true)
                        .show(getSupportFragmentManager());
                break;
            case R.id.btn_diaglogfragment_confirm:
                CommonDialog.Companion.newInstance()
                        .setDialogLayout(R.layout.dialogfragment_confirm)
                        .setConvertListener((ViewConvertListener) (holder, dialog) -> {
                            ((TextView)holder.getView(R.id.df_title)).setText("Title");
                            ((TextView)holder.getView(R.id.df_message)).setText("This is content!");
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
