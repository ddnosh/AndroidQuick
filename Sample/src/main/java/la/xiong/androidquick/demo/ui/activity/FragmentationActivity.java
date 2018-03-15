package la.xiong.androidquick.demo.ui.activity;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFActivity;
import la.xiong.androidquick.demo.base.BaseFFragment;
import la.xiong.androidquick.demo.ui.fragment.Example2Fragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class FragmentationActivity extends BaseFActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_fragmentation;
    }

    @Override
    protected void initViewsAndEvents() {
        BaseFFragment fragment = findFragment(Example2Fragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, Example2Fragment.newInstance());
        }
    }
}
