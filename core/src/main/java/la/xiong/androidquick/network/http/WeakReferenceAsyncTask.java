package la.xiong.androidquick.network.http;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class WeakReferenceAsyncTask<T, V> extends AsyncTask<Void, Void, V> {

    private WeakReference<T> wf;

    public T getTarget() {
        return wf.get();
    }

    public WeakReferenceAsyncTask(T target) {
        wf = new WeakReference<>(target);
    }

    @Override
    protected V doInBackground(Void... voids) {
        if (getTarget() != null) {
            return handleInBackground();
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(V httpResponse) {
        LogUtil.i("WeakReferenceAsyncTask", getTarget() != null ? "still alive" : "already dead");
        if (httpResponse != null && getTarget() != null) {
            handlePostExecute(httpResponse);
        }
    }

    public abstract V handleInBackground();

    public abstract void handlePostExecute(V httpResponse);
}
