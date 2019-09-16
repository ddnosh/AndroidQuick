package com.androidwind.androidquick.demo.features.module.network.retrofit.network3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.features.module.network.retrofit.TestApis;
import com.androidwind.androidquick.module.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.util.LogUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Network3Fragment extends BaseFragment implements View.OnClickListener {

    private static String TAG = "Network3Fragment";

    private Button btnSyncFlatMap, btnSyncConcat, btnAsyncMerge, btnAsyncZip;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        btnSyncFlatMap = getActivity().findViewById(R.id.btnSyncFlatMap);
        btnSyncFlatMap.setOnClickListener(this);
        btnSyncConcat = getActivity().findViewById(R.id.btnSyncConcat);
        btnSyncConcat.setOnClickListener(this);
        btnAsyncMerge = getActivity().findViewById(R.id.btnAsyncMerge);
        btnAsyncMerge.setOnClickListener(this);
        btnAsyncZip = getActivity().findViewById(R.id.btnAsyncZip);
        btnAsyncZip.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_network3;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSyncFlatMap:
                clickSyncFlatMap();
                break;
            case R.id.btnSyncConcat:
                clickSyncConcat();
                break;
            case R.id.btnAsyncMerge:
                clickAsyncMerge();
                break;
            case R.id.btnAsyncZip:
                clickAsyncZip();
                break;
        }
    }

    private void clickAsyncZip() {
        TestApis testApis = RetrofitManager.getInstance().createApi(TestApis.class);
        Observable<TSSCRes<TSSCResult>> o1 = testApis.getTangShiSongCi();
        Observable<XHYRes<XHYResult>> o2 = testApis.getXHY();
        Observable.zip(o1, o2, new BiFunction<TSSCRes<TSSCResult>, XHYRes<XHYResult>, Integer>() {
            @Override
            public Integer apply(TSSCRes<TSSCResult> tsscRes, XHYRes<XHYResult> xhyRes) throws Exception {
                return tsscRes.getResult().size() + xhyRes.getResult().size();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new BaseObserver<Integer>() {

                               @Override
                               public void onError(ApiException exception) {
                                   LogUtil.e(TAG, "error:" + exception.getMessage());
                               }

                               @Override
                               public void onSuccess(Integer count) {
                                   LogUtil.e(TAG, "count = " + count);
                               }
                           }
                );
    }

    private void clickAsyncMerge() {
        TestApis testApis = RetrofitManager.getInstance().createApi(TestApis.class);
        Observable<TSSCRes<TSSCResult>> o1 = testApis.getTangShiSongCi();
        Observable<XHYRes<XHYResult>> o2 = testApis.getXHY();
        Observable
                .merge(o1, o2)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new BaseObserver<Object>() {

                               @Override
                               public void onError(ApiException exception) {
                                   LogUtil.e(TAG, "error:" + exception.getMessage());
                               }

                               @Override
                               public void onSuccess(Object object) {
                                   if (object instanceof TSSCRes) {
                                       LogUtil.i(TAG, object.toString());
                                   } else if (object instanceof XHYRes) {
                                       LogUtil.i(TAG, object.toString());
                                   }
                               }
                           }
                );
    }

    private void clickSyncConcat() {
        TestApis testApis = RetrofitManager.getInstance().createApi(TestApis.class);
        Observable<TSSCRes<TSSCResult>> o1 = testApis.getTangShiSongCi();
        Observable<XHYRes<XHYResult>> o2 = testApis.getXHY();
        Observable
                .concat(o1, o2) // 依次处理, 先处理o1, 再处理o2
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new BaseObserver<Object>() {

                               @Override
                               public void onError(ApiException exception) {
                                   LogUtil.e(TAG, "error:" + exception.getMessage());
                               }

                               @Override
                               public void onSuccess(Object object) {
                                   if (object instanceof TSSCRes) {
                                       LogUtil.i(TAG, object.toString());
                                   } else if (object instanceof XHYRes) {
                                       LogUtil.i(TAG, object.toString());
                                   }
                               }
                           }
                );
    }

    public void clickSyncFlatMap() {
        TestApis testApis = RetrofitManager.getInstance().createApi(TestApis.class);
        testApis.getTangShiSongCi() // 先访问唐诗宋词接口
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<TSSCRes<TSSCResult>, ObservableSource<XHYRes<XHYResult>>>() {
                    @Override
                    public ObservableSource<XHYRes<XHYResult>> apply(TSSCRes<TSSCResult> listTSSCRes) throws Exception {
                        if (listTSSCRes != null && listTSSCRes.getResult() != null) {
                            return testApis.getXHY();  // 再访问歇后语接口
                        } else {
                            return null;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<XHYRes<XHYResult>>() {

                               @Override
                               public void onError(ApiException exception) {
                                   LogUtil.e(TAG, "error:" + exception.getMessage());
                               }

                               @Override
                               public void onSuccess(XHYRes<XHYResult> testBeans) {
                                   LogUtil.i(TAG, testBeans.toString());
                               }
                           }
                );
    }
}
