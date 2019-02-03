package la.xiong.androidquick.demo.module.task;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.task.Task;
import la.xiong.androidquick.task.TaskScheduler;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TaskSchedulerFragment extends BaseFragment {

    private Task<String> mDemoTask;
    private long mStartMillis;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_task_scheduler;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mDemoTask = new Task<String>() {

            @Override
            public String doInBackground()  {

                mStartMillis = System.currentTimeMillis();
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e) {
                    LogUtil.e(TAG,""+e);
                }
                LogUtil.i(TAG,"withResultTask doInBackground, current thread is ? "+Thread.currentThread().getName());
                return "休眠"+(System.currentTimeMillis() - mStartMillis)/1000+"秒";
            }

            @Override
            public void onSuccess(String result) {
                LogUtil.i(TAG,"withResultTask onSuccess, current thread is main ? "
                        +TaskScheduler.isMainThread()+", result : "+result);
                ToastUtil.showToast("Show callback result on MainUIThread!");
            }

            @Override
            public void onFail(Throwable throwable) {
                super.onFail(throwable);
                LogUtil.i(TAG,"onFail： 休眠 "+(System.currentTimeMillis() - mStartMillis)/1000+"秒");
            }

            @Override
            public void onCancel() {
                super.onCancel();
                LogUtil.i(TAG,"onCancel： 休眠 "+(System.currentTimeMillis() - mStartMillis)/1000+"秒");
                ToastUtil.showToast("Task execution is timeout!");
            }
        };

    }

    @OnClick({R.id.btn_task_noresult, R.id.btn_task_withresult, R.id.btn_task_cancelresult, R.id.btn_task_timeoutresult})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_task_noresult:
                noResult();
                break;
            case R.id.btn_task_withresult:
                withResult();
                break;
            case R.id.btn_task_cancelresult:
                cancelResult();
                break;
            case R.id.btn_task_timeoutresult:
                timeOutResult();
                break;
        }
    }

    private void timeOutResult() {
        TaskScheduler.executeTimeOutTask(3000, mDemoTask);
    }

    private void cancelResult() {
        Log.i(TAG,"cancelTask");
        TaskScheduler.cancelTask(mDemoTask);
    }

    private void withResult() {
        TaskScheduler.execute(mDemoTask);
    }

    private void noResult() {
        TaskScheduler.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.i(TAG,"noResultTask current thread is main ? "+TaskScheduler.isMainThread());
                TaskScheduler.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast("Show callback result on MainUIThread!");
                    }
                });
            }
        });
    }

}
