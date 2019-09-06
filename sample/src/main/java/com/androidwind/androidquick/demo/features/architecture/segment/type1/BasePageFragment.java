package com.androidwind.androidquick.demo.features.architecture.segment.type1;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.util.LogUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BasePageFragment extends BaseFragment {

    private PageController currentPage;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private PageController newController(Class<?> pageConClass) {
        try {
            Class controllerClass = Class.forName(pageConClass.getName());
            Constructor constructor = controllerClass
                    .getDeclaredConstructor(BasePageFragment.class);
            PageController controller = (PageController) constructor.newInstance(this);
            return controller;
        } catch (Exception e) {
            LogUtil.e(TAG, e.toString());
        }
        return null;
    }

    private HashMap map = new HashMap();

    public <T extends PageController> T getPageController(Class<T> pageControll) {

        String controllName = pageControll.getName();
        if (map != null && map.containsKey(controllName)) {
            return (T) map.get(controllName);
        } else {
            PageController controller = newController(pageControll);
            View view = onCreatePage(controller);
            if (view == null) {
                throw new RuntimeException(
                        "没有初始化页面view,please override onCreatePage()");
            }
            controller.setContentView(view);
            map.put(controllName, controller);
            return (T) controller;
        }

    }

    protected abstract View onCreatePage(PageController controller);

    protected synchronized PageController getCurrentPage() {
        return currentPage;
    }

    public synchronized void setCurrentPage(PageController currentPage) {
        PageController prePage = this.currentPage;
        if (prePage != null) {
            prePage.hidden();
            if (!prePage.isOut()) {
                prePage.out();
            }
        }
        this.currentPage = currentPage;
    }

    public <T extends PageController> boolean isPageControllExist(Class<T> pageControll) {
        String name = pageControll.getName();
        return map != null && map.containsKey(name);
    }

    protected void release() {

    }
}
