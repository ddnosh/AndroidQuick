package la.xiong.androidquick.demo.module.network.retrofit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.module.network.retrofit.download.Download;
import la.xiong.androidquick.demo.module.network.retrofit.service.DownloadService;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.StringUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DownloadActivity extends BaseActivity {
    @BindView(R.id.btn_download)
    AppCompatButton mBtnDownload;
    @BindView(R.id.pb_download)
    ProgressBar mPbDownload;
    @BindView(R.id.tv_download)
    TextView mTvDownload;

    public static final String MESSAGE_PROGRESS = "message_progress";
    private LocalBroadcastManager bManager;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_download;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        registerReceiver();

        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DownloadActivity.this, DownloadService.class);
                startService(intent);
            }
        });
    }

    private void registerReceiver() {

        bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注册时，使用注册时的manager解绑
        bManager.unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(MESSAGE_PROGRESS)) {

                Download download = intent.getParcelableExtra("download");
                LogUtil.i(TAG, "download progress:"+download.getProgress());
                mPbDownload.setProgress(download.getProgress());
                if (download.getProgress() == 100) {

                    mTvDownload.setText("File Download Complete");

                } else {
                    String tips = StringUtil.getDataSize(download.getCurrentFileSize())
                            + "/" +
                            StringUtil.getDataSize(download.getTotalFileSize());
                    LogUtil.i(TAG, "download text:"+tips);
                    mTvDownload.setText(tips);
                }
            }
        }
    };

}
