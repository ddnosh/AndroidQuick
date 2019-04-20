package la.xiong.androidquick.demo.features.design_patterns.switcher;

import android.util.Log;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DefaultLogProcessor implements ILogProcessor {
    @Override
    public void v(String vLog) {
        Log.v("DefaultLogProcessor", "defaultlog:" + vLog);
    }

    @Override
    public void d(String dLog) {
        Log.d("DefaultLogProcessor", "defaultlog:" + dLog);
    }

    @Override
    public void i(String iLog) {
        Log.i("DefaultLogProcessor", "defaultlog:" + iLog);
    }

    @Override
    public void e(String eLog) {
        Log.e("DefaultLogProcessor", "defaultlog:" + eLog);
    }
}
