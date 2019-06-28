package la.xiong.androidquick.demo.tool;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;

import la.xiong.androidquick.tool.AppUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class GlideUtils {

    /**
     * Glide特点
     * 使用简单
     * 可配置度高，自适应程度高
     * 支持常见图片格式 Jpg png gif webp
     * 支持多种数据源  网络、本地、资源、Assets 等
     * 高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
     * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
     * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
     * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity
     */

    //默认加载
    public static void loadImageView(String path, ImageView mImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).into(mImageView);
        }
    }

    //加载指定大小
    public static void loadImageViewSize(String path, int width, int height, ImageView mImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).override(width, height).into(mImageView);
        }
    }

    //设置加载中以及加载失败图片
    public static void loadImageViewLoading(String path, ImageView mImageView, int loadingImage, int errorImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).placeholder(loadingImage).error(errorImageView).into(mImageView);
        }
    }

    //设置加载中以及加载失败图片并且指定大小
    public static void loadImageViewLoadingSize(String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into(mImageView);
        }
    }

    //设置跳过内存缓存
    public static void loadImageViewCache(String path, ImageView mImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).skipMemoryCache(true).into(mImageView);
        }
    }

    //设置下载优先级
    public static void loadImageViewPriority(String path, ImageView mImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).priority(Priority.NORMAL).into(mImageView);
        }
    }

    /**
     * 策略解说：
     * <p>
     * all:缓存源资源和转换后的资源
     * <p>
     * none:不作任何磁盘缓存
     * <p>
     * source:缓存源资源
     * <p>
     * result：缓存转换后的资源
     */

    //设置缓存策略
    public static void loadImageViewDiskCache(String path, ImageView mImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
        }
    }

    /**
     * api也提供了几个常用的动画：比如crossFade()
     */

    //设置加载动画
    public static void loadImageViewAnim(String path, int anim, ImageView mImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).animate(anim).into(mImageView);
        }
    }

    /**
     * 会先加载缩略图
     */

    //设置缩略图支持
    public static void loadImageViewThumbnail(String path, ImageView mImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).thumbnail(0.1f).into(mImageView);
        }
    }

    /**
     * api提供了比如：centerCrop()、fitCenter()等
     */

    //设置动态转换
    public static void loadImageViewCrop(String path, ImageView mImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).centerCrop().into(mImageView);
        }
    }

    //设置动态GIF加载方式
    public static void loadImageViewDynamicGif(String path, ImageView mImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).asGif().into(mImageView);
        }
    }

    //设置静态GIF加载方式
    public static void loadImageViewStaticGif(String path, ImageView mImageView) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).asBitmap().into(mImageView);
        }
    }

    //设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘

    //设置监听请求接口
    public static void loadImageViewListener(String path, ImageView mImageView, RequestListener<String, GlideDrawable> requstlistener) {
        if (AppUtil.isContextValid(mImageView.getContext())) {
            Glide.with(mImageView.getContext()).load(path).listener(requstlistener).into(mImageView);
        }
    }

    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排

    //设置要加载的内容
    public static void loadImageViewContent(Context mContext, String path, SimpleTarget<GlideDrawable> simpleTarget) {
        Glide.with(mContext).load(path).centerCrop().into(simpleTarget);
    }

    //清理磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }

    //清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();
    }

}
