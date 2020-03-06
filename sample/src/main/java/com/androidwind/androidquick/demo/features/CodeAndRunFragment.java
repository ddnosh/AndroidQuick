package com.androidwind.androidquick.demo.features;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.view.bottom.BottomDialogFragment;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.OnClick;
import us.feras.mdv.MarkdownView;

public abstract class CodeAndRunFragment extends BaseFragment {

    @BindView(R.id.mv)
    MarkdownView mMvCode;
    @BindView(R.id.output)
    ScrollView mSvOutput;
    @BindView(R.id.result)
    TextView mtvResult;

    @OnClick({R.id.btn_show, R.id.btn_run})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show:
                mMvCode.setVisibility(View.VISIBLE);
                mSvOutput.setVisibility(View.GONE);
                break;
            case R.id.btn_run:
                BottomDialogFragment.showDialog(getActivity(), getItems()).setListener(position -> clickItem(position));
                break;
        }
    }

    public void showOutput(String result) {
        mMvCode.setVisibility(View.GONE);
        mSvOutput.setVisibility(View.VISIBLE);
        mtvResult.setText(result);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_code_run;
    }

    @Override
    protected void initViewsAndEvents(@Nullable Bundle bundle) {
        mMvCode.loadMarkdownFile("file:///android_asset/" + getMarkDownUrl() + ".md", "file:///android_asset/css-themes/classic.css");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("BottomDialogFragment");
        if (prev != null) {
            DialogFragment df = (DialogFragment) prev;
            df.dismiss();
        }
    }

    public abstract String getMarkDownUrl();

    public abstract String[] getItems();

    public abstract void clickItem(int position);
}
