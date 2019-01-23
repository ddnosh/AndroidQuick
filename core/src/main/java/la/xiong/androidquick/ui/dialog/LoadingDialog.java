package la.xiong.androidquick.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import la.xiong.androidquick.R;
import la.xiong.androidquick.tool.StringUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class LoadingDialog extends Dialog implements View.OnClickListener {

    private final static String TAG = "LoadingDialog";
    private LinearLayout mNoBgLinely;
    private TextView mTipTxt;
    private String mTip;
    private Context loadingDialogContext;

    public LoadingDialog(Context context) {
        super(context, R.style.dialog_loading_view);
        loadingDialogContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        mNoBgLinely = (LinearLayout) findViewById(R.id.ll_loading);
        mNoBgLinely.setVisibility(View.VISIBLE);
        mTipTxt = (TextView)findViewById(R.id.tip);
        if(!StringUtil.isEmpty(mTip)){
            mTipTxt.setText(mTip);
        }
    }

    public void setTip(String tip){
        mTip = tip;
    }

    public void onDismiss() {
        if (this.isShowing()) {
            this.dismiss();
        }
    }

    public Context getLoadingDialogContext() {
        return loadingDialogContext;
    }

    @Override
    public void onClick(View v) {
        onDismiss();
    }

    public interface LoadingDialogCallback {
        void onDialogCallback(int type, String id);
    }
}
