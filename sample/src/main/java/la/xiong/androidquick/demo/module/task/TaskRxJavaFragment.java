package la.xiong.androidquick.demo.module.task;

import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TaskRxJavaFragment extends BaseFragment {

    private static String TAG = "TaskRxJavaFragment";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_task_rxjava;
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

    private void noResult() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                Thread.sleep(5000);
                LogUtil.i(TAG,"[create] the thread has slept 5s, the current thread is " + Thread.currentThread().getName());
                emitter.onNext("the thread has slept 5s");
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.i(TAG,"[onNext]the current thread is " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void withResult() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                Thread.sleep(5000);
                LogUtil.i(TAG,"[create] the thread has slept 5s, the current thread is " + Thread.currentThread().getName());
                emitter.onNext("the thread has slept 5s");
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.i(TAG,"[onNext]the current thread is " + Thread.currentThread().getName());
                        ToastUtil.showToast(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
