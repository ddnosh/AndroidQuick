package la.xiong.androidquick.tool;

import android.app.Activity;
import android.content.Context;

import la.xiong.androidquick.ui.dialog.CommonDialog;
import la.xiong.androidquick.ui.dialog.LoadingDialog;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DialogUtil {

    private static LoadingDialog loadingDialog;

    //使用默认的tip
    public static void showLoadingDialog(Context context) {
        showLoadingDialog(context, null);
    }

    public static void showLoadingDialog(Context context, String tips) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {
                try {
                    if (loadingDialog == null) {
                        loadingDialog = new LoadingDialog(context);
                        if (!StringUtil.isEmpty(tips))
                            loadingDialog.setTip(tips);
                        loadingDialog.show();
                    } else {
                        //相同的上下文，无需重新创建
                        if (loadingDialog.getLoadingDialogContext() == context) {
                            loadingDialog.show();
                        } else {
                            loadingDialog.dismiss();
                            loadingDialog = new LoadingDialog(context);
                            if (!StringUtil.isEmpty(tips))
                                loadingDialog.setTip(tips);
                            loadingDialog.show();
                        }
                    }

                } catch (Throwable e) {
                }
            }
        }
    }

    public static void dismissLoadingDialog(Context context){
        try {
            if(!((Activity) context).isFinishing() && loadingDialog!=null && loadingDialog.isShowing()){
                loadingDialog.dismiss();
            }
        }catch(Throwable e){}
    }

    public static CommonDialog.Builder getDialogBuilder(Context context){
        return new CommonDialog.Builder(context);
    }
}
