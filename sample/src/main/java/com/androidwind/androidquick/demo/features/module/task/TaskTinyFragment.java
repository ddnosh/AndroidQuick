package com.androidwind.androidquick.demo.features.module.task;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.module.asynchronize.SimpleTask;
import com.androidwind.androidquick.module.asynchronize.Task;
import com.androidwind.androidquick.module.asynchronize.TinyTaskExecutor;

import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 * <p>
 * about tinytask: ---> https://github.com/ddnosh/android-tiny-task
 */
public class TaskTinyFragment extends BaseFragment {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_task_tiny;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_task_noresult, R.id.btn_task_withresult})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_task_noresult:
                noResult();
                break;
            case R.id.btn_task_withresult:
                withResult();
                break;
        }
    }

    private void withResult() {
        TinyTaskExecutor.execute(new Task<String>() {
            @Override
            public String doInBackground() {
                System.out.println("[new] thread id in tinytask: " + Thread.currentThread().getId());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[new] with callback after 5 sec");
                return "task with sleep 5 sec";
            }

            @Override
            public void onSuccess(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }

    private void noResult() {
        TinyTaskExecutor.execute(new SimpleTask() {

            @Override
            public void run() {
                System.out.println("[new] thread id in tinytask: " + Thread.currentThread().getId());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[new] no callback after 5 sec");
            }
        });
    }

}
