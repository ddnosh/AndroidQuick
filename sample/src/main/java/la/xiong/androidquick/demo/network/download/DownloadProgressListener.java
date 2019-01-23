package la.xiong.androidquick.demo.network.download;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
