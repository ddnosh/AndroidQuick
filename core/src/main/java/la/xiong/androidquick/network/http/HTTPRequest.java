package la.xiong.androidquick.network.http;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HTTPRequest {
    private static final String TAG = "HTTPRequest";

    // Components
    private String mPath;
    private String mMethod;
    private ArrayList<NameValuePair> mParameters;
    private int mReadTimeoutMillis;
    private int mConnectTimeoutMillis;
    private HttpCallback mHttpCallback;

    public HttpCallback getHttpCallback() {
        return mHttpCallback;
    }

    public static class Builder {
        // Required parameters
        private String mPath;
        // Optional parameters - initialize with default values
        private String mMethod = "GET";
        private int mReadTimeoutMillis = 10000;
        private int mConnectTimeoutMillis = 15000;
        private ArrayList<NameValuePair> mParameters;
        private HttpCallback mCallback;

        public Builder(String path) {
            mPath = path;
            mParameters = new ArrayList<NameValuePair>();
        }

        public Builder setMethod(String method) {
            mMethod = method;
            Log.i("TEST", "???? " + method + " " + mMethod);

            return this;
        }

        public Builder putParameter(String name, String value) {
            mParameters.add(new BasicNameValuePair(name, value));

            return this;
        }

        public Builder setReadTimeoutMillis(int readTimeoutMillis) {
            mReadTimeoutMillis = readTimeoutMillis;

            return this;
        }

        public Builder setConnectTimeoutMillis(int connectTimeoutMillis) {
            mConnectTimeoutMillis = connectTimeoutMillis;

            return this;
        }

        public Builder setHttpCallback(HttpCallback callback) {
            mCallback = callback;

            return this;
        }

        public HTTPRequest build() {
            return new HTTPRequest(this);
        }

    }

    private HTTPRequest(Builder builder) {
        mPath = builder.mPath;
        mMethod = builder.mMethod;
        mParameters = builder.mParameters;
        mReadTimeoutMillis = builder.mReadTimeoutMillis;
        mConnectTimeoutMillis = builder.mConnectTimeoutMillis;
        mHttpCallback = builder.mCallback;
    }

    public HTTPResponse request() throws IOException {
        if ("GET".equals(mMethod)) {
            return get();
        } else if ("POST".equals(mMethod)) {
            return post();
        } else {
            return null;
        }
    }

    private HTTPResponse post() throws IOException {
        URL url = new URL(mPath);
        HttpURLConnection lConnection = (HttpURLConnection) url.openConnection();

        lConnection.setReadTimeout(mReadTimeoutMillis);
        lConnection.setConnectTimeout(mConnectTimeoutMillis);
        lConnection.setRequestMethod("POST");
        lConnection.setDoInput(true);
        lConnection.setDoOutput(true);

        OutputStream lOutStream = lConnection.getOutputStream();
        BufferedWriter lWriter = new BufferedWriter(new OutputStreamWriter(lOutStream, "UTF-8"));
        lWriter.write(getQuery(mParameters));
        lWriter.flush();
        lWriter.close();
        lOutStream.close();

        HTTPResponse response = readPage(lConnection);

        return response;
    }

    private HTTPResponse get() throws IOException {
        URL url = null;

        if (mParameters.size() > 0) {
            url = new URL(mPath + "?" + getQuery(mParameters));
        } else {
            url = new URL(mPath);
        }

        HttpURLConnection lConnection = (HttpURLConnection) url.openConnection();

        lConnection.setReadTimeout(mReadTimeoutMillis);
        lConnection.setConnectTimeout(mConnectTimeoutMillis);
        lConnection.setRequestMethod("GET");
        lConnection.setDoInput(true);

        HTTPResponse response = readPage(lConnection);

        return response;
    }

    /**
     * @param parameter the NameValuePare List of http parameter.
     *
     * @return the string of http parameter format
     *
     * @throws UnsupportedEncodingException throws from URLEncoder.encode()
     */
    private String getQuery(List<NameValuePair> parameter) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : parameter) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    /**
     * @param connection the HttpURLConnection of web server page.
     *
     * @return the HTTPResponse object
     *
     * @throws IOException throws from HttpURLConnection method.
     */
    private HTTPResponse readPage(HttpURLConnection connection) throws IOException {
        connection.connect();
        int responseCode = connection.getResponseCode();
        String lLine = null;

        String responseMessage = "";
        try {
            BufferedReader messageReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder messageBuilder = new StringBuilder();
            while ((lLine = messageReader.readLine()) != null) {
                messageBuilder.append(lLine);
                messageBuilder.append('\n');
            }
            messageReader.close();
            responseMessage = messageBuilder.toString();
        } catch (Exception e) {
            responseMessage = "";
        }

        String errorMessage = "";
        try {
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder errorBuilder = new StringBuilder();
            while ((lLine = errorReader.readLine()) != null) {
                errorBuilder.append(lLine);
                errorBuilder.append('\n');
            }
            errorReader.close();
            errorMessage = errorBuilder.toString();
        } catch (Exception e) {
            errorMessage = "";
        }

        connection.disconnect();
        return new HTTPResponse(responseCode, responseMessage, errorMessage);
    }

    public void execute() {
        new HttpTask(this).execute();
    }

}
