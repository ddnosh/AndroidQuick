package la.xiong.androidquick.demo.features.architecture.mvp.fragment_dagger;

import javax.inject.Inject;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVPDaggerPresenter extends MVPDaggerContract.Presenter {

    @Inject
    public MVPDaggerPresenter() {

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
                getView().refreshView("do in background after 3 seconds");
            }
        }).start();
    }
}
