package la.xiong.androidquick.demo.features.design_patterns.observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ObserverFragment extends BaseFragment {

    @BindView(R.id.btn_observer_1)
    Button mButton1;
    @BindView(R.id.btn_observer_2)
    Button mButton2;

    Server server;
    MyObservable.MyObserver myObserver;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        myObserver = new MyObservable.MyObserver() {
            @Override
            public void update(int time) {
                ToastUtil.showToast("here is myObserver!");
            }
        };
        MyObservable.addObserver(myObserver);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_design_pattern_observer;
    }

    @OnClick({R.id.btn_observer_1, R.id.btn_observer_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_observer_1:
                server = new Server(2019);
                Client client1 = new Client("张三");
                Client client2 = new Client("李四");
                server.addObserver(client1);
                server.addObserver(client2);
                server.setTime(2018);
                server.setTime(2019);
                break;
            case R.id.btn_observer_2:
                MyObservable.notify(2019);
                break;
        }
    }

    @Override
    protected void initDestroy() {
        if (server != null) {
            server.deleteObservers();
        }
        if (myObserver != null) {
            MyObservable.removeObserver(myObserver);
        }
    }
}
