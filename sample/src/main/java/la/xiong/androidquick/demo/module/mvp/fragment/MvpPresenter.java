package la.xiong.androidquick.demo.module.mvp.fragment;

import javax.inject.Inject;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MvpPresenter extends MvpContract.Presenter {

    @Inject
    public MvpPresenter() {

    }

    @Override
    public void initData(String type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mView.refreshView("do in background after 3 seconds");
            }
        }).start();
    }
}
