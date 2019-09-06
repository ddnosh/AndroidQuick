package com.androidwind.androidquick.demo.features.design_patterns.observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"observer", "观察者"}, description = "观察者模式实例(系统自带+自定义)")
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
    public void onDestroy() {
        super.onDestroy();

        if (server != null) {
            server.deleteObservers();
        }
        if (myObserver != null) {
            MyObservable.removeObserver(myObserver);
        }
    }
}
