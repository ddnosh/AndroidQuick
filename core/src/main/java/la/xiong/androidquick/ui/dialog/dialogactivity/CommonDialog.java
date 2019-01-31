package la.xiong.androidquick.ui.dialog.dialogactivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import la.xiong.androidquick.R;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.StringUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class CommonDialog extends Dialog implements View.OnClickListener {

    private final static String TAG = "CommonDialog";
    private Context mContext;
    private TextView mTitleView;
    private TextView mInfoView;
    private Button mConfirmBtn;
    private Button mCancelBtn;

    private String mTitle;
    private String mInfoText;
    private String mConfirmText, mCancelText;
    private DialogCallback mCallback;
    private DialogBtnCallBack mDialogBtnCallBack;
    private boolean mCanConfirmButtonDismiss = true;

    public CommonDialog(Context context) {
        super(context, R.style.dialog_common_style);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(); //悬浮对话框的情况z
    }

    private void init() {
        setContentView(R.layout.dialog_common);
        mTitleView = (TextView) findViewById(R.id.dialog_title);
        mInfoView = (TextView) findViewById(R.id.dialog_info);
        mConfirmBtn = (Button) findViewById(R.id.dialog_confirm);
        mCancelBtn = (Button) findViewById(R.id.dialog_cancel);
        mConfirmBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);

        if (mInfoText != null && !StringUtil.isEmpty(mInfoText)) {
            mInfoView.setText(mInfoText);
        }

        //设置title content 等具体名称
        mTitleView.setText(mTitle);

        if (mConfirmText != null && !StringUtil.isEmpty(mConfirmText)) {
            mConfirmBtn.setText(mConfirmText);
        }
        if (mCancelText != null && !StringUtil.isEmpty(mCancelText)) {
            mCancelBtn.setText(mCancelText);
        }

    }

    public void setDialogBtnCallback(DialogBtnCallBack dialogBtnCallback) {
        mDialogBtnCallBack = dialogBtnCallback;
    }

    public void setValue(String title, String info, String confirmBtn, String cancelBtn, String doNotShowAgain,
                         DialogCallback callback) {
        LogUtil.i(TAG, "dialog title:" + title + "  mes:0" + info + " positiveBtn:" + confirmBtn + " negativeBtn:" + cancelBtn + " not_tip:" + doNotShowAgain + " callback" + callback);
        mTitle = title;
        mInfoText = info;
        mConfirmText = confirmBtn;
        mCancelText = cancelBtn;
        mCallback = callback;
    }

    public void onDismiss() {
        if (this.isShowing()) {
            this.dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.dialog_confirm) {
            if (mCanConfirmButtonDismiss)
                onDismiss();
            if (mDialogBtnCallBack != null)
                mDialogBtnCallBack.onDialogButClick(true);
        } else if (i == R.id.dialog_cancel) {
            onDismiss();
            if (mDialogBtnCallBack != null) {
                mDialogBtnCallBack.onDialogButClick(false);
            }
        }

    }

    public interface DialogCallback {
        void onDialogCallback(boolean isConfirm, boolean isNottip, int Position);
    }

    public interface DialogBtnCallBack {
        void onDialogButClick(boolean isConfirm);
    }

    public static class Builder {
        private String mTitle;
        private String mInfoText;
        private String mConfirmText;
        private String mCancelText;
        private String mDoNotShowAgain;
        private DialogCallback mDialogCallback;
        private DialogBtnCallBack mDialogBtnCallback;
        private Context mContext;
        private boolean mSelected = false;
        private boolean mCancelable = true;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder setCancelable(boolean isCancelable) {
            mCancelable = isCancelable;
            return this;
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setTitle(int id) {
            return setTitle(mContext.getString(id));
        }

        public Builder setMessage(String info) {
            this.mInfoText = info;
            return this;
        }

        /***
         * 文本内容是否可以被选择去复制
         **/
        public Builder setContectTextSelected(boolean selected) {
            this.mSelected = selected;
            return this;
        }

        public Builder setMessage(int id) {
            return setMessage(mContext.getString(id));
        }

        /**
         * 设置“确定”按钮的text ,点击监听，通过CommonDialog.DialogCallBack 函数回调监听
         **/
        public Builder setPositiveButton(String confirmBtnText) {
            this.mConfirmText = confirmBtnText;
            return this;
        }

        /**
         * 设置“确定”按钮的text ,点击监听，通过CommonDialog.DialogCallBack 函数回调监听
         **/
        public Builder setPositiveButton(int id) {
            return setPositiveButton(mContext.getString(id));
        }

        /**
         * 设置“取消”按钮的text ,点击监听，通过CommonDialog.DialogCallBack 函数回调监听
         **/
        public Builder setNegativeButton(String cancelBtnText) {
            this.mCancelText = cancelBtnText;
            return this;
        }

        /**
         * 设置“取消”按钮的text ,点击监听，通过CommonDialog.DialogCallBack 函数回调监听
         **/
        public Builder setNegativeButton(int id) {
            return setNegativeButton(mContext.getString(id));
        }

        /**
         * 设置“不再提示”的text ,点击监听，通过CommonDialog.DialogCallBack 函数回调监听
         **/
        public Builder setNotTipText(String doNotShowAgainText) {
            this.mDoNotShowAgain = doNotShowAgainText;
            return this;
        }

        public Builder setClickCallBack(DialogCallback dialogCallback) {
            this.mDialogCallback = dialogCallback;
            return this;
        }

        public Builder setBtnClickCallBack(DialogBtnCallBack dialogBtnCallBack) {
            this.mDialogBtnCallback = dialogBtnCallBack;
            return this;
        }

        /**
         * 每次调用该函数，都会根据之前设置的各项参数，生成一个新的 commonDialog 对象作为返回,但是该dialog 默认没有show,如果想一步就显示出对话框，可以调用show()方法
         **/
        public CommonDialog create() {
            CommonDialog dialog = new CommonDialog(mContext);
            dialog.setValue(this.mTitle, this.mInfoText, this.mConfirmText, this.mCancelText, this.mDoNotShowAgain, mDialogCallback);
            dialog.setDialogBtnCallback(mDialogBtnCallback);
            return dialog;
        }


        /**
         * 每次调用该函数，都会根据之前设置的各项成熟，生成一个新的 commonDialog 对象作为返回, 该dialog 默认show
         **/
        public CommonDialog show() {
            LogUtil.i(TAG, " title " + mTitle + " message" + mInfoText + "  show()  to be invoked");
            CommonDialog dialog = create();
            dialog.show();
            dialog.setCancelable(mCancelable);
            return dialog;
        }

    }
}
