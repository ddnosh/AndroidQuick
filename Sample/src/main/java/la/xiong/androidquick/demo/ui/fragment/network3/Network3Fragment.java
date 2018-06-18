package la.xiong.androidquick.demo.ui.fragment.network3;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.http.HTTPRequest;
import la.xiong.androidquick.http.HTTPResponse;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Network3Fragment extends BaseFragment {

    protected static String TAG = "NetworkActivity";

    // Layout Components
    private EditText mInputUrl;
    private Button mButtonSet;
    private EditText mInputKey;
    private EditText mInputValue;
    private Button mButtonAdd;
    private RadioGroup mRadioGroupMethod;
    private RadioButton mRadioGet;
    private RadioButton mRadioPost;
    private Button mButtonRequest;
    private Button mButtonClean;

    private TextView mTextResult;

    // Components
    private HTTPRequest.Builder mBuilder;
    private HTTPRequest mRequest;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_network3;
    }

    @Override
    protected void initViewsAndEvents() {
        loadGUI();
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void loadGUI() {
        mInputUrl = (EditText) getActivity().findViewById(R.id.inputUrl);
        mButtonSet = (Button) getActivity().findViewById(R.id.buttonSet);
        mInputKey = (EditText) getActivity().findViewById(R.id.inputKey);
        mInputValue = (EditText) getActivity().findViewById(R.id.inputValue);
        mButtonAdd = (Button) getActivity().findViewById(R.id.buttonAdd);
        mRadioGroupMethod = (RadioGroup) getActivity().findViewById(R.id.radioGroupMethod);
        mRadioGet = (RadioButton) getActivity().findViewById(R.id.radioGet);
        mRadioPost = (RadioButton) getActivity().findViewById(R.id.radioPost);
        mButtonRequest = (Button) getActivity().findViewById(R.id.buttonRequest);
        mButtonClean = (Button) getActivity().findViewById(R.id.buttonClean);
        mTextResult = (TextView) getActivity().findViewById(R.id.textResult);

        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = mInputUrl.getText().toString();

                if(!inputString.equals("")) {
                    mInputUrl.setEnabled(false);
                    mButtonSet.setEnabled(false);
                    mInputKey.setEnabled(true);
                    mInputValue.setEnabled(true);
                    mButtonAdd.setEnabled(true);
                    mRadioGet.setEnabled(true);
                    mRadioPost.setEnabled(true);
                    mButtonRequest.setEnabled(true);
                    mButtonClean.setEnabled(true);

                    mBuilder = new HTTPRequest.Builder(inputString);
                } else {
                    Toast.makeText(getActivity().getApplication(), "type url!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mInputKey.getText().toString();
                String value = mInputValue.getText().toString();
                mInputKey.setText("");
                mInputValue.setText("");
                mBuilder.putParameter(name, value);
            }
        });

        mButtonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = mRadioGroupMethod.getCheckedRadioButtonId();

                switch(radioId) {
                    case R.id.radioPost:
                        mBuilder.setMethod("POST");
                        break;
                    case R.id.radioGet:
                        mBuilder.setMethod("GET");
                        break;
                }

                mRequest = mBuilder.build();

                new AsyncTask<Void, Void, HTTPResponse>() {

                    @Override
                    protected HTTPResponse doInBackground(Void... params) {
                        try {
                            return mRequest.request();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                    @Override
                    protected void onPostExecute(HTTPResponse httpResponse) {
                        if(httpResponse != null) {
                            String responseMessage = httpResponse.getResponseMessage();
                            String errorMessage = httpResponse.getErrorMessage();

                            if(!responseMessage.equals("")) {
                                mTextResult.setText(responseMessage);
                            }

                            if(!errorMessage.equals("")) {
                                mTextResult.setText(errorMessage);
                            }
                        }
                    }
                }.execute();
            }
        });

        mButtonClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });
    }

    public void init() {
        mInputUrl.setText("https://www.apiopen.top/femaleNameApi?page=1");
        mInputKey.setText("");
        mInputValue.setText("");
        mRadioGet.setChecked(true);
        mTextResult.setText("");

        mInputUrl.setEnabled(true);
        mButtonSet.setEnabled(true);
        mInputKey.setEnabled(false);
        mInputValue.setEnabled(false);
        mButtonAdd.setEnabled(false);
        mRadioGet.setEnabled(false);
        mRadioPost.setEnabled(false);
        mButtonRequest.setEnabled(false);
        mButtonClean.setEnabled(false);
    }

}
