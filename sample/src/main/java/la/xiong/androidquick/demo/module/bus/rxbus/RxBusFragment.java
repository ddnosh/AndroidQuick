package la.xiong.androidquick.demo.module.bus.rxbus;

import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.eventbus.EventCenter;
import la.xiong.androidquick.tool.ToastUtil;

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
