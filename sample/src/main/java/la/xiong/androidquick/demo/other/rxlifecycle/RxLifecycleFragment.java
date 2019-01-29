package la.xiong.androidquick.demo.other.rxlifecycle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class RxLifecycleFragment extends BaseFragment {

    @BindView(R.id.btn_rxlifecycle)
    Button btnRxLifecycle;

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_other_rxlifecycle;
    }

    @OnClick({R.id.btn_rxlifecycle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rxlifecycle:
                Observable.interval(1, TimeUnit.SECONDS)//execute by every 1 second
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(bindToLifecycle())
                        .subscribe(new Observer<Object>() {

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Object aLong) {
                                Log.i("receive data", String.valueOf(aLong));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }
}
