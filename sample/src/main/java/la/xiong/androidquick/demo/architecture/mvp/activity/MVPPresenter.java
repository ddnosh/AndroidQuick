package la.xiong.androidquick.demo.architecture.mvp.activity;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVPPresenter extends MVPContract.Presenter {

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
