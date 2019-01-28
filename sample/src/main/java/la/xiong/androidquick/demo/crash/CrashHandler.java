package la.xiong.androidquick.demo.crash;

import android.content.Context;

import la.xiong.androidquick.demo.constant.Constants;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandler INSTANCE = new CrashHandler();

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 获取系统默认的UncaughtException处理器
        Thread.setDefaultUncaughtExceptionHandler(this);// 设置该CrashHandler为程序的默认处理器
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (mDefaultHandler != null) {
            if (Constants.EXCEPTION_MVP_VIEW_NOT_ATTACHED.equals(e.getMessage())) {// for MVP when presenter calls view but view has already destroyed
                return;
            }
            mDefaultHandler.uncaughtException(t, e);// 退出程序
        }
    }
}
