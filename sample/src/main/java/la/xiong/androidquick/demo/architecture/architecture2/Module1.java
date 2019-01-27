package la.xiong.androidquick.demo.architecture.architecture2;

import android.view.View;
import android.widget.TextView;

import la.xiong.androidquick.demo.R;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public class Module1 {

    private IModuleCallback moduleCallback;
    private TextView mTextView;

    public Module1(View rootView, IModuleCallback callback) {
        moduleCallback = callback;
        mTextView = rootView.findViewById(R.id.tv_module1);
    }

    public void hideView() {
        mTextView.setVisibility(View.GONE);
    }
}
