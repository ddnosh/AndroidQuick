package la.xiong.androidquick.demo.architecture.architecture3;

import android.content.Context;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public interface LoadJsCallback {

    void loadJavaScript(String js);

    Context getContext();
}
