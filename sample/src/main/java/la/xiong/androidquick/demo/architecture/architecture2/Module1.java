package la.xiong.androidquick.demo.architecture.architecture2;

import android.view.View;
import android.widget.TextView;

import la.xiong.androidquick.demo.R;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public class Module1 extends BaseModule{

    private TextView mTextView;

    public Module1(View rootView) {
        super(rootView);
    }

    public void hideView() {
        mTextView.setVisibility(View.GONE);
    }

    public String getContent() {
        return "this is from module1";
    }

    @Override
    protected void initView() {
        mTextView = (TextView) findViewById(R.id.tv_module1);
        mTextView.setText("Module1 loaded!");
    }

    @Override
    protected void release() {
        System.out.println("Module1 released!");
    }
}
