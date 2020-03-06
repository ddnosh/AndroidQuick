package com.androidwind.androidquick.demo.view.bottom;

import android.app.Dialog;
import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.features.search.SearchAdapter;
import com.androidwind.androidquick.demo.features.search.SearchManager;
import com.androidwind.androidquick.demo.ui.activity.MainActivity;
import com.androidwind.androidquick.ui.dialog.ViewHolder;
import com.androidwind.androidquick.ui.dialog.dialogfragment.BaseDialogFragment;
import org.jetbrains.annotations.NotNull;

public class BottomDialogFragment extends BaseDialogFragment {

    private static final String TAG = "BottomDialogFragment";

    private Window window;
    private String[] itemData;
    private OnBottomItemClick listener;

    public void setListener(OnBottomItemClick listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        itemData = bundle.getStringArray("key");
        // 去掉默认的标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(getContext(), R.layout.fragment_bottom, null);
        ListView listView = view.findViewById(R.id.lv_result);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, itemData);
        listView.setAdapter(mAdapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener((parent, v, position, id) -> {
            listener.onClick(position);
            dismiss();
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        window = getDialog().getWindow();
        window.setBackgroundDrawableResource(android.R.color.white);
        window.setWindowAnimations(R.style.df_bottom);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(params);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        listener = null;
        dismiss();
        super.onDismiss(dialog);
    }

    public static BottomDialogFragment newInstance(String[] itemData) {
        Bundle args = new Bundle();
        args.putStringArray("key", itemData);
        BottomDialogFragment fragment = new BottomDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static BottomDialogFragment showDialog(FragmentActivity appCompatActivity, String[] itemData) {
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        BottomDialogFragment bottomDialogFragment =
                (BottomDialogFragment) fragmentManager.findFragmentByTag(TAG);
        if (null == bottomDialogFragment) {
            bottomDialogFragment = newInstance(itemData);
        }

        if (!appCompatActivity.isFinishing()
                && null != bottomDialogFragment
                && !bottomDialogFragment.isAdded()) {
            fragmentManager.beginTransaction()
                    .add(bottomDialogFragment, TAG)
                    .commitAllowingStateLoss();
        }

        return bottomDialogFragment;
    }

    @Override
    public void convertView(@NotNull ViewHolder viewHolder, @NotNull BaseDialogFragment baseDialogFragment) {

    }

    public interface OnBottomItemClick {
        void onClick(int position);
    }
}