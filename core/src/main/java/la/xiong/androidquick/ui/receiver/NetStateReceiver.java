package la.xiong.androidquick.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import de.greenrobot.event.EventBus;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.NetUtil;
import la.xiong.androidquick.eventbus.EventCenter;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class NetStateReceiver extends BroadcastReceiver {

    private final static String TAG = "NetStateReceiver";
    private final static String CUSTOM_ANDROID_NET_CHANGE_ACTION = "la.xiong.androidquick.net.conn.CONNECTIVITY_CHANGE";
    private final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    private static boolean isNetAvailable = false;
    private static NetUtil.NetType mNetType;
    private static BroadcastReceiver mBroadcastReceiver;

    private static BroadcastReceiver getReceiver() {
        if (null == mBroadcastReceiver) {
            synchronized (NetStateReceiver.class) {
                if (null == mBroadcastReceiver) {
                    mBroadcastReceiver = new NetStateReceiver();
                }
            }
        }
        return mBroadcastReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mBroadcastReceiver = NetStateReceiver.this;
        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION) || intent.getAction().equalsIgnoreCase(CUSTOM_ANDROID_NET_CHANGE_ACTION)) {
            if (!NetUtil.isNetworkAvailable(context)) {
                LogUtil.i(TAG, "<--- network disconnected --->");
                isNetAvailable = false;
                EventBus.getDefault().post(new EventCenter(10001));
            } else {
                LogUtil.i(TAG, "<--- network connected --->");
                isNetAvailable = true;
                mNetType = NetUtil.getAPNType(context);
                EventBus.getDefault().post(new EventCenter(10002));
            }
        }
    }

    public static void registerNetworkStateReceiver(Context mContext) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);
        filter.addAction(ANDROID_NET_CHANGE_ACTION);
        mContext.getApplicationContext().registerReceiver(getReceiver(), filter);
    }

    public static void checkNetworkState(Context mContext) {
        Intent intent = new Intent();
        intent.setAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);
        mContext.sendBroadcast(intent);
    }

    public static void unRegisterNetworkStateReceiver(Context mContext) {
        if (mBroadcastReceiver != null) {
            try {
                mContext.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
            } catch (Exception e) {
                LogUtil.e(TAG, e.getMessage());
            }
        }

    }

    public static boolean isNetworkAvailable() {
        return isNetAvailable;
    }

    public static NetUtil.NetType getAPNType() {
        return mNetType;
    }

}