package com.androidwind.androidquick.demo.features.design_patterns.observer;

import java.util.Observable;
import java.util.Observer;

import com.androidwind.androidquick.util.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Client implements Observer {
    private String name;
    public Client(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Server) {
            ToastUtil.showToast(name + "say: time is up!" + arg);
        }
    }
}
