package la.xiong.androidquick.demo.features.function.immersion;

import android.graphics.Color;
import android.os.Bundle;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.tool.GlideImageLoader;
import la.xiong.androidquick.tool.immersion.StatusBarUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ImmersionActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner mBanner;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_immersion;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3356121154,1179528716&fm=27&gp=0.jpg");
        imageUrls.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2533643392,3611141887&fm=27&gp=0.jpg");
        imageUrls.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=403151593,358130787&fm=27&gp=0.jpg");
        List<String> titles = new ArrayList<>();
        titles.add("图片一");
        titles.add("图片二");
        titles.add("图片三");
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                switch (position) {
                    case 0://黑色字体
                        StatusBarUtil.setTranslucentStatus(ImmersionActivity.this);//透明状态栏
                        StatusBarUtil.setStatusBarDarkTheme(ImmersionActivity.this, true);
                        break;
                    case 1://白色字体
                        StatusBarUtil.setStatusBarDarkTheme(ImmersionActivity.this, false);
                        break;
                    case 2:
                        //设置白色字体，其他背景
                        StatusBarUtil.setStatusBarDarkTheme(ImmersionActivity.this, false);
                        StatusBarUtil.setStatusBarColor(ImmersionActivity.this, Color.parseColor("#58C087"));//设置背景颜色
                        break;
                }
            }
        });

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setImageLoader(new GlideImageLoader())
                .setImages(imageUrls)
                .setBannerTitles(titles)
                .setDelayTime(3000)
                .start();
    }
}
