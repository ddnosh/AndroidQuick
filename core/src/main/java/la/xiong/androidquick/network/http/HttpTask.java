package la.xiong.androidquick.network.http;

import java.io.IOException;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class HttpTask extends WeakReferenceAsyncTask<HttpCallback, HTTPResponse> {
    private HTTPRequest mHttpRequest;
    public HttpTask(HTTPRequest httpRequest) {
        super(httpRequest.getHttpCallback());
        mHttpRequest = httpRequest;
    }

    @Override
    public HTTPResponse handleInBackground() {
        try {
            //simulate taking-time task
//            Thread.sleep(30000);
            return mHttpRequest.request();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void handlePostExecute(HTTPResponse httpResponse) {
        String responseMessage = httpResponse.getResponseMessage();
        String errorMessage = httpResponse.getErrorMessage();
        if (!responseMessage.equals("")) {
            getTarget().onSuccess(responseMessage);
        }

        if (!errorMessage.equals("")) {
            getTarget().onFail(errorMessage);
        }
    }
}
