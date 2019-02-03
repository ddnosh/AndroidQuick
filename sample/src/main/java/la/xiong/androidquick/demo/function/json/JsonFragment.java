package la.xiong.androidquick.demo.function.json;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseTFragment;
import la.xiong.androidquick.demo.bean.FuliBean;
import la.xiong.androidquick.demo.bean.GankFuliBean;
import la.xiong.androidquick.demo.module.network.retrofit.GankRes;
import la.xiong.androidquick.demo.tool.FastJsonHelper;
import la.xiong.androidquick.tool.GsonHelper;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class JsonFragment extends BaseTFragment {

    public static final String TAG = "JsonFragment";

    @BindView(R.id.tv_console)
    TextView mTvConsole;

    private String testJsonString;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        testJsonString = "{\n" +
                "  \"error\": false, \n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"_id\": \"5a6e5f88421aa9115696004f\", \n" +
                "      \"createdAt\": \"2018-01-29T07:40:56.269Z\", \n" +
                "      \"desc\": \"1-29\", \n" +
                "      \"publishedAt\": \"2018-01-29T07:53:57.676Z\", \n" +
                "      \"source\": \"chrome\", \n" +
                "      \"type\": \"\\u798f\\u5229\", \n" +
                "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20180129074038_O3ydq4_Screenshot.jpeg\", \n" +
                "      \"used\": true, \n" +
                "      \"who\": \"daimajia\"\n" +
                "    }, \n" +
                "    {\n" +
                "      \"_id\": \"5a65381a421aa91156960022\", \n" +
                "      \"createdAt\": \"2018-01-22T09:02:18.715Z\", \n" +
                "      \"desc\": \"1-22\", \n" +
                "      \"publishedAt\": \"2018-01-23T08:46:45.132Z\", \n" +
                "      \"source\": \"chrome\", \n" +
                "      \"type\": \"\\u798f\\u5229\", \n" +
                "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20180122090204_A4hNiG_Screenshot.jpeg\", \n" +
                "      \"used\": true, \n" +
                "      \"who\": \"daimajia\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_json;
    }

    @OnClick({R.id.btn_tools_gsonhelper_common, R.id.btn_tools_gsonhelper_generic, R.id.btn_tools_fastjsonhelper})
    public void onClick(View view) {
        mTvConsole.setText("");
        switch (view.getId()) {
            case R.id.btn_tools_gsonhelper_common:
                long startTime1 = System.nanoTime();
                GankFuliBean gsonObj = GsonHelper.fromJson(testJsonString, GankFuliBean.class);
                List<GankFuliBean.FuliBean> fulis =  gsonObj.getResults();
                String desc = " ";
                for(GankFuliBean.FuliBean fuli : fulis ){
                    desc += fuli.getDesc()+"\n";
                }
                ToastUtil.showToast(desc);
                mTvConsole.setText(gsonObj.toString());
                break;
            case R.id.btn_tools_gsonhelper_generic:
                long startTime2 = System.nanoTime();
                GankRes<List<FuliBean>> gsonObj2 = GsonHelper.fromJson(testJsonString, new TypeToken<GankRes<List<FuliBean>>>() {
                });
                long consumingTime2 = System.nanoTime() - startTime2;
                ToastUtil.showToast("耗时" + consumingTime2 / 1000 + "微秒");
                break;
            case R.id.btn_tools_fastjsonhelper:
                long startTime3 = System.nanoTime();
                GankRes<List<FuliBean>> fastObj = FastJsonHelper.parseObject(testJsonString, new TypeReference<GankRes<List<FuliBean>>>() {
                });
                long consumingTime3 = System.nanoTime() - startTime3;
                ToastUtil.showToast("耗时" + consumingTime3 / 1000 + "微秒");
                break;
        }
    }
}
