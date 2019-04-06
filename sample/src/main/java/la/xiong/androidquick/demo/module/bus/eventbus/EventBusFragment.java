package la.xiong.androidquick.demo.module.bus.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.eventbus.EventCenter;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class EventBusFragment extends BaseFragment {

    public static final String TAG = "EventBusFragment";

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_bus_eventbus;
    }

    @OnClick( {R.id.btn_eventbus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_eventbus:
                EventBus.getDefault().post(new EventCenter(10003, "this is an event"));
                break;
        }
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        if (eventCenter.getEventCode() == 10003) {
            Toast.makeText(getContext(), (String) eventCenter.getData(), Toast.LENGTH_SHORT).show();
        }
    }
}
