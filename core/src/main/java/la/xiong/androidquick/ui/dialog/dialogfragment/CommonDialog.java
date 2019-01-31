package la.xiong.androidquick.ui.dialog.dialogfragment;

import android.support.annotation.LayoutRes;

/**
 * 公用样式Dialog
 */
public class CommonDialog extends BaseDialogFragment {
    private ViewConvertListener convertListener;

    public static CommonDialog newInstance() {
        CommonDialog dialog = new CommonDialog();
        return dialog;
    }

    public CommonDialog setDialogLayout(@LayoutRes int layoutId) {
        this.mLayoutResId = layoutId;
        return this;
    }

    public CommonDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }

    @Override
    public void convertView(ViewHolder holder, BaseDialogFragment dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }
}
