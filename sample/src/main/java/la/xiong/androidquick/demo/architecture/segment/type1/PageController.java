package la.xiong.androidquick.demo.architecture.segment.type1;

import android.view.View;

public abstract class PageController {

    private static final String TAG = "PageController";
    protected BasePageFragment fragment;

    private boolean isOut;

    public PageController(BasePageFragment fragment) {
        this.fragment = fragment;
    }

    protected View contentView;

    public void setContentView(View contentView) {
        this.contentView = contentView;
        initView();
    }

    public View getContentView() {
        return contentView;
    }

    protected abstract void initView();

    public View findViewById(int id) {
        return fragment.getActivity().findViewById(id);
    }

    public int getVisibility() {
        if (contentView != null) {
            return contentView.getVisibility();
        }
        return View.GONE;
    }

    public boolean isOut() {
        return isOut;
    }

    private void setVisibility(int v) {
        if (contentView != null) {
            contentView.setVisibility(v);
        }
    }

    public void post(Runnable task) {
        if (contentView != null) {
            contentView.post(task);
        }
    }

    public void show() {
        fragment.setCurrentPage(this);
        setVisibility(View.VISIBLE);
        enter();
    }

    public void hidden() {
        setVisibility(View.GONE);
    }

    public void enter() {
        isOut = false;
    }

    public void out() {
        isOut = true;
    }

    protected void onActivityStart() {

    }

    public void release() {

    }
}
