package la.xiong.androidquick.demo.architecture.architecture2.mvp;

import android.content.Context;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BasePresenter<V> {
    public Context context;
    public V mView;

    public void initV(V v) {
        this.mView = v;
    }

    public void unInitV() {
        this.mView = null;
    }

}
