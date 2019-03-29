package la.xiong.androidquick.demo.architecture.mvp.activity;

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
    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getView().refreshView("do in background after 3 seconds");
            }
        }).start();
    }
}
