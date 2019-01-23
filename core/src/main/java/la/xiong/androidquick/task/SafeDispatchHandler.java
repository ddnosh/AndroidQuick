package la.xiong.androidquick.task;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import la.xiong.androidquick.BuildConfig;


/**
 *
 * @author SilenceDut
 * @date 17/04/18
 *
 * a safe Handler avoid crash
 */
public class SafeDispatchHandler extends Handler{

    private static final String TAG = "SafeDispatchHandler";
    public SafeDispatchHandler(Looper looper) {
        super(looper);
    }

    public SafeDispatchHandler(Looper looper, Callback callback) {
        super(looper, callback);
    }

    public SafeDispatchHandler() {
        super();
    }

    public SafeDispatchHandler(Callback callback) {
        super(callback);
    }

    @Override
    public void dispatchMessage(Message msg) {
        if (BuildConfig.DEBUG) {
            super.dispatchMessage(msg);
        } else {
            try {
                super.dispatchMessage(msg);
            } catch (Exception e) {
                Log.d(TAG, "dispatchMessage Exception " + msg + " , " + e);
            } catch (Error error) {
                Log.d(TAG, "dispatchMessage error " + msg + " , " + error);
            }
        }
    }
}
