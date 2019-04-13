package la.xiong.androidquick.demo.features.module.network.retrofit;

import java.io.Serializable;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class GankRes<T> implements Serializable {

    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
