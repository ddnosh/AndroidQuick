package la.xiong.androidquick.ui.viewstatus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class VaryViewHelper implements IVaryViewHelper {
	private View view;
	private ViewGroup parentView;
	private int viewIndex;
	private ViewGroup.LayoutParams params;
	private View currentView;

	public VaryViewHelper(View view) {
		super();
		this.view = view;
	}

	private void init() {
		params = view.getLayoutParams();
		if (view.getParent() != null) {
			parentView = (ViewGroup) view.getParent();
		} else {
			parentView = (ViewGroup) view.getRootView().findViewById(android.R.id.content);
		}
		int count = parentView.getChildCount();
		for (int index = 0; index < count; index++) {
			if (view == parentView.getChildAt(index)) {
				viewIndex = index;
				break;
			}
		}
		currentView = view;
	}

	@Override
	public View getCurrentLayout() {
		return currentView;
	}

	@Override
	public void restoreView() {
		showLayout(view);
	}

	@Override
	public void showLayout(View view) {
		if (parentView == null) {
			init();
		}
		this.currentView = view;
		// 如果已经是那个view，那就不需要再进行替换操作了
		if (parentView.getChildAt(viewIndex) != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
			parentView.removeViewAt(viewIndex);
			parentView.addView(view, viewIndex, params);
		}
	}

	@Override
	public View inflate(int layoutId) {
		return LayoutInflater.from(view.getContext()).inflate(layoutId, null);
	}

	@Override
	public Context getContext() {
		return view.getContext();
	}

	@Override
	public View getView() {
		return view;
	}
}
