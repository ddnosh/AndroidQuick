package la.xiong.androidquick.demo.features.module.network.retrofit.download;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
