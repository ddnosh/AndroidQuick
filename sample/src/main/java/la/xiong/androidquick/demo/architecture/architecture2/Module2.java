package la.xiong.androidquick.demo.architecture.architecture2;

import android.view.View;
import android.widget.TextView;

import la.xiong.androidquick.demo.R;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public class Module2 {
    private IModuleCallback moduleCallback;
    private TextView mTextView;

    public Module2(View rootView, IModuleCallback callback) {
        moduleCallback = callback;
        mTextView = rootView.findViewById(R.id.tv_module2);
    }

    public void modify(String content) {
        mTextView.setText(content);
        moduleCallback.doModify(content);
    }
}
