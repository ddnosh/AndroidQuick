package la.xiong.androidquick.demo.features.module.network.common;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.features.module.network.common.http.HTTPRequest;
import la.xiong.androidquick.demo.features.module.network.common.http.HttpCallback;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"http", "网络请求"}, description = "一个简单的http封装实例")
public class CommonHttpFragment extends BaseFragment {

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
    //
    private String defaultUrl = "https://www.apiopen.top/femaleNameApi";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_network_common;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        initUI();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Runtime.getRuntime().gc();
    }

    private void initUI() {
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

                mBuilder.setHttpCallback(new HttpCallback() {
                    @Override
                    public void onSuccess(String response) {
                        mTextResult.setText(response);
                    }

                    @Override
                    public void onFail(String response) {
                        mTextResult.setText(response);
                    }
                });

                mBuilder.build().execute();
            }
        });

        mButtonClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInputKey.setText("");
                mInputValue.setText("");
                initData();
            }
        });
    }

    public void initData() {
        mInputUrl.setText(defaultUrl);
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
