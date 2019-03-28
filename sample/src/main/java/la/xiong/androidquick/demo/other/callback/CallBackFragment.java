package la.xiong.androidquick.demo.other.callback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.androidwind.task.AdvancedTask;
import com.androidwind.task.TinyTaskExecutor;

import java.util.ArrayList;

import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ToastUtil;
import la.xiong.androidquick.ui.eventbus.EventCenter;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class CallBackFragment extends BaseFragment {

    public static final String TAG = "CallBackFragment";

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_callback;
    }

    @OnClick({R.id.btn_callback_listener, R.id.btn_callback_observer, R.id.btn_callback_eventbus, R.id.btn_callback_broadcast})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_callback_listener:
                Demo demo = new Demo();
                demo.setListener(new Demo.DemoListener() {
                    @Override
                    public void print(String content) {
                        LogUtil.i(TAG, content);
                    }
                });
                demo.tryToPrintSomething();
                break;
            case R.id.btn_callback_observer:
                DemoObserver.addObserver(runnable);
                DemoObserver.notifyObserver();
                break;
            case R.id.btn_callback_eventbus:
                EventBus.getDefault().post(new EventCenter(10003, "this is an event"));
                break;
            case R.id.btn_callback_broadcast:
                registerBroadcastReceiver();
                Intent contactsIntent = new Intent("DEMO_ACTION_CHANGE");
                mContext.sendBroadcast(contactsIntent);
                break;
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LogUtil.i(TAG, "Observer is triggered");
        }
    };

    private DemoReceiver receiver;

    class DemoReceiver extends BroadcastReceiver { //这里处理的是在动态详情页和动态大图页点赞和取消点赞的更新事件
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.i(TAG, "onReceive: DemoReceiver");
            if ("DEMO_ACTION_CHANGE".equals(intent.getAction())) {
                Toast.makeText(getContext(), "Broadcast is triggered", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("DEMO_ACTION_CHANGE");
        receiver = new DemoReceiver();
        mContext.registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DemoObserver.removeObserver(runnable);
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
        }
    }

    public static class Demo {
        private DemoListener listener;

        public void setListener(DemoListener listener) {
            this.listener = listener;
        }

        public void tryToPrintSomething() {
            if (listener != null) {
                listener.print("here is something!");
            }
        }

        public interface DemoListener {
            void print(String content);
        }
    }

    public static class DemoObserver {
        private static ArrayList<Runnable> observers = new ArrayList<>();

        public static void addObserver(Runnable nunnable) {
            if (Looper.myLooper() != MyApplication.getInstance().getMainLooper()) {
                throw new RuntimeException("observer should be add on mainlooper");
            }
            observers.add(nunnable);
        }

        public static void removeObserver(Runnable nunnable) {
            observers.remove(nunnable);
        }

        public static void notifyObserver() {
            TinyTaskExecutor.execute(new AdvancedTask<String>() {
                @Override
                public String doInBackground() {
                    System.out.println("[new] thread id in tinytask: " + Thread.currentThread().getId());
                    for (Runnable runnable : observers) {
                        runnable.run();
                    }
                    System.out.println("[new] with callback after 5 sec");
                    return "task with sleep 5 sec";
                }

                @Override
                public void onSuccess(String s) {
                    ToastUtil.showToast(s);
                }

                @Override
                public void onFail(Throwable throwable) {

                }
            });
        }
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        if (eventCenter.getEventCode() == 10003) {
            Toast.makeText(getContext(), (String) eventCenter.getData(), Toast.LENGTH_SHORT).show();
        }
    }
}
