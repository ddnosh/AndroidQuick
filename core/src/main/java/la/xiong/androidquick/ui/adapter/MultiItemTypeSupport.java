package la.xiong.androidquick.ui.adapter;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface MultiItemTypeSupport<T> {

    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
