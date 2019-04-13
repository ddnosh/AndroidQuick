package la.xiong.androidquick.demo.features.module.task;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidwind.task.AdvancedTask;
import com.androidwind.task.SimpleTask;
import com.androidwind.task.TinyTaskExecutor;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 *
 * about tinytask: ---> https://github.com/ddnosh/android-tiny-task
 *
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
        TinyTaskExecutor.execute(new AdvancedTask<String>() {
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
        TinyTaskExecutor.execute(new SimpleTask<String>() {
            @Override
            public String doInBackground() {
                System.out.println("[new] thread id in tinytask: " + Thread.currentThread().getId());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[new] no callback after 5 sec");
                return "simple task with sleep 5 sec";
            }
        });
    }

}
