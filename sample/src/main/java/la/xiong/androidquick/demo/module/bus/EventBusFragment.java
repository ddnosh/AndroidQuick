package la.xiong.androidquick.demo.module.bus;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseTFragment;
import la.xiong.androidquick.ui.eventbus.EventCenter;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class EventBusFragment extends BaseTFragment {

    public static final String TAG = "EventBusFragment";

    @BindView(R.id.btn_bus1)
    Button btn1;

    @Override
    protected void initViewsAndEvents() {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_bus_eventbus;
    }

    @OnClick( {R.id.btn_bus1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bus1:
                EventBus.getDefault().post(new EventCenter(10003, "this is a event"));
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
