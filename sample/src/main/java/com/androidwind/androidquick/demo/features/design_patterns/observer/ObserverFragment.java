package com.androidwind.androidquick.demo.features.design_patterns.observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.features.CodeAndRunFragment;
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
public class ObserverFragment extends CodeAndRunFragment {

    Server server;
    MyObservable.MyObserver myObserver;

    @Override
    public String getMarkDownUrl() {
        return "ObserverFragment";
    }

    @Override
    public String[] getItems() {
        return new String[]{"Java自带", "自定义"};
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                server = new Server(2019);
                Client client1 = new Client("张三");
                Client client2 = new Client("李四");
                server.addObserver(client1);
                server.addObserver(client2);
                server.setTime(2018);
                server.setTime(2019);
                break;
            case 1:
                MyObservable.notify(2019);
                break;
        }
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        super.initViewsAndEvents(savedInstanceState);
        myObserver = new MyObservable.MyObserver() {
            @Override
            public void update(int time) {
                ToastUtil.showToast("here is myObserver!");
            }
        };
        MyObservable.addObserver(myObserver);
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
