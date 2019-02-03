package la.xiong.androidquick.demo.architecture.architecture1;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.tool.LogUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Architecture1Fragment extends BasePageFragment {

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        final boolean v = getPageController(Page1.class).getVisibility() == View.VISIBLE;
        if (!v) {
            getPageController(Page1.class).show();
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_architecture1;
    }

    @Override
    protected View onCreatePage(PageController controller) {
        View view = null;
        String name = controller.getClass().getName();
        LogUtil.i(TAG, "onCreatePage:" + name);
        if (name.equals(Page1.class.getName())) {
            ViewStub page1 = (ViewStub) getActivity().findViewById(R.id.view_stub_page1);
            page1.inflate();
            view = getActivity().findViewById(R.id.page1);
        } else if (name.equals(Page2.class.getName())) {
            ViewStub page2 = (ViewStub) getActivity().findViewById(R.id.view_stub_page2);
            page2.inflate();
            view = getActivity().findViewById(R.id.page2);
        }
        return view;
    }

    @OnClick({R.id.view_stub_page_show1, R.id.view_stub_page_show2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_stub_page_show1:
                final boolean v1 = getPageController(Page1.class).getVisibility() == View.VISIBLE;
                if (!v1) {
                    getPageController(Page1.class).show();
                }
                break;
            case R.id.view_stub_page_show2:
                final boolean v2 = getPageController(Page2.class).getVisibility() == View.VISIBLE;
                if (!v2) {
                    getPageController(Page2.class).show();
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        release();
    }

    @Override
    protected void release() {
        try {
            if (isPageControllExist(Page1.class)) {
                getPageController(Page1.class).release();
            }
            if (isPageControllExist(Page2.class)) {
                getPageController(Page2.class).release();
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "release err.." + e.getMessage());
        } finally {
            super.release();
        }
    }
}
