package com.androidwind.androidquick.demo.features.module.bus.rxbus;

import android.os.Bundle;
import android.view.View;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.eventbus.EventCenter;
import com.androidwind.androidquick.util.ToastUtil;

import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class RxBusFragment extends BaseFragment {
    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        RxBus.getInstance().toObservable().map(new Function<Object, EventCenter>() {
            @Override
            public EventCenter apply(Object o) throws Exception {
                return (EventCenter) o;
            }
        }).subscribe(new Consumer<EventCenter>() {
            @Override
            public void accept(EventCenter eventCenter) throws Exception {
                if (eventCenter != null) {
                    ToastUtil.showToast((String) eventCenter.getData());
                }
            }
        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_bus_rxbus;
    }

    @OnClick( {R.id.btn_rxbus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rxbus:
                RxBus.getInstance().post(new EventCenter(1000, "RxBus Message"));
                break;
        }
    }
}
