package la.xiong.androidquick.demo.features.search;

import com.androidwind.annotation.annotation.TagInfo;
import com.androidwind.annotation.core.SearchEngine;
import com.androidwind.annotation.core.SearchHouse;

import java.util.ArrayList;
import java.util.List;

import la.xiong.androidquick.tool.StringUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class SearchManager {

    public List<TagInfo> searchResults = new ArrayList();

    private static volatile SearchManager sInstance = null;

    private SearchManager() {

    }

    public static SearchManager getInstance() {
        if (sInstance == null) {
            synchronized (SearchManager.class) {
                if (sInstance == null) {
                    sInstance = new SearchManager();
                }
            }
        }
        return sInstance;
    }

    public void init() {
        //初始化AOP生成的文件
        SearchEngine.getsInstance().init();

        // TinyTaskExecutor.execute(new AdvancedTask<Void>() {
        //     @Override
        //     public Void doInBackground() {
        //         // TinyLog.d(Arrays.toString(tagService.map.entrySet().toArray()));
        //         JSONObject jsonObject = new JSONObject();
        //         for (Map.Entry<String, TagInfo> entry : SearchHouse.tags.entrySet()) {
        //             TagInfo value = entry.getValue();
        //             String sTemp = value.getDetail().substring(1, value.getDetail().length() - 1);
        //             String[] sArray = sTemp.split(", ");
        //             int count = 0;
        //             for (String s : sArray) {
        //                 count++;
        //                 if (!StringUtil.isEmpty(s)) {
        //                     jsonObject.put("tag" + count, s);
        //                 }
        //             }
        //             TagDao.getInstance().createOrUpdate(entry.getKey(), jsonObject.toJSONString());
        //         }
        //         return null;
        //     }
        //
        //     @Override
        //     public void onSuccess(Void s) {
        //         ToastUtil.showToast("Tag updated!");
        //         List<Tag> tagList = TagDao.getInstance().getAllTag();
        //         LogUtil.i("SearchManager", tagList.toString());
        //     }
        //
        //     @Override
        //     public void onFail(Throwable throwable) {
        //
        //     }
        // });
    }

    public void getResults(String keyword) {
        // List<Tag> tags = TagDao.getInstance().getAllTag();
        for (String key : SearchHouse.tags.keySet()) {
            if (StringUtil.isEmpty(SearchHouse.tags.get(key).getDetail())) {
                return;
            }
            String sTemp = SearchHouse.tags.get(key).getDetail().substring(1, SearchHouse.tags.get(key).getDetail().length() - 1);
            String[] sArray = sTemp.split(", ");
            for (int i = 0; i < sArray.length; i++) {
                String result = sArray[i];
                if (!StringUtil.isEmpty(result) && result.toLowerCase().contains(keyword.toLowerCase())) {
                    searchResults.add(SearchHouse.tags.get(key));
                }
            }
        }
    }

}
