package com.androidwind.androidquick.demo.features.architecture.segment.type2;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseModule {

    private static final Map<Class, BaseModule> modules = new HashMap<>();

    protected View view;

    protected BaseModule(View rootView) {
        this.view = rootView;
        modules.put(getClass(), this);
    }

    protected static void initModulesView() {
        for (BaseModule roomModule : modules.values()) {
            roomModule.initView();
        }
    }

    protected static void releaseModules() {
        for (BaseModule roomModule : modules.values()) {
            roomModule.release();
        }
        modules.clear();
    }

    protected <T extends BaseModule> T getModule(Class<T> tClass) {
        BaseModule module = modules.get(tClass);
        if (module != null) {
            return (T)module;
        } else {
            throw new IllegalStateException("no " + tClass.getName() + "  instance");
        }
    }

    protected abstract void initView();

    protected abstract void release();

    protected View findViewById(int resId) {
        return view.findViewById(resId);
    }
}
