package la.xiong.androidquick.demo.module.network.retrofit.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;

import la.xiong.androidquick.demo.module.network.retrofit.download.Download;
import la.xiong.androidquick.demo.module.network.retrofit.download.DownloadManager;
import la.xiong.androidquick.demo.module.network.retrofit.download.DownloadProgressListener;
import la.xiong.androidquick.demo.module.network.retrofit.network1.Network1Fragment;
import la.xiong.androidquick.tool.StringUtil;
import rx.Subscriber;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DownloadService extends IntentService {
    private static final String TAG = "DownloadService";

    int downloadCount = 0;

    private String apkUrl = "https://imtt.dd.qq.com/16891/D92DD871CD08F11CE8F053EF61B0D40B.apk?fsname=net.csdn.csdnplus_3.5.0_50.apk&csr=1bbd";
    public DownloadService() {
        super("DownloadService");
    }

    private File outputFile;

    @Override
    protected void onHandleIntent(Intent intent) {
        download();
    }

    private void download() {
        DownloadProgressListener listener = new DownloadProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                //不频繁发送通知，防止通知栏下拉卡顿
                int progress = (int) ((bytesRead * 100) / contentLength);
                if ((downloadCount == 0) || progress > downloadCount) {
                    Download download = new Download();
                    download.setTotalFileSize(contentLength);
                    download.setCurrentFileSize(bytesRead);
                    download.setProgress(progress);

                    sendIntent(download);
                }
            }
        };
        outputFile = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS), "file.apk");

        if (outputFile.exists()) {
            outputFile.delete();
        }


        String baseUrl = StringUtil.getHostName(apkUrl);

        new DownloadManager(baseUrl, listener).downloadAPK(apkUrl, outputFile, new Subscriber() {
            @Override
            public void onCompleted() {
                downloadCompleted();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                downloadCompleted();
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(Object o) {

            }
        });
    }

    private void downloadCompleted() {
        Download download = new Download();
        download.setProgress(100);
        sendIntent(download);

        //安装apk
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        intent.setDataAndType(Uri.fromFile(outputFile), "application/vnd.android.package-archive");
//        startActivity(intent);
    }

    private void sendIntent(Download download) {

        Intent intent = new Intent(Network1Fragment.MESSAGE_PROGRESS);
        intent.putExtra("download", download);
        LocalBroadcastManager.getInstance(DownloadService.this).sendBroadcast(intent);
    }

}
