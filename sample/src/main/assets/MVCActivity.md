## MVCActivity
<pre>
    @BindTag(type = TagInfo.Type.ACTIVITY, tags = {"MVC"}, description = "Activity + MVC实例")
    public class MVCActivity extends CodeAndRunActivity {

    @Override
    public String getMarkDownUrl() {
        return "MVCActivity";
    }

    @Override
    public String[] getItems() {
        return new String[]{"MVC", "点击一下"};
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                showOutput("\"I don't do anything, but this is a MVC show anyway. Click me!");
                break;
            case 1:
                RetrofitManager.INSTANCE.getRetrofit(Constants.GANK_API_URL).create(GankApis.class)
                        .getHistoryDate()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<GankRes<List<String>>>() {

                            @Override
                            public void onError(ApiException exception) {
                                LogUtil.e(TAG, "error:" + exception.getMessage());
                            }

                            @Override
                            public void onSuccess(GankRes<List<String>> listGankRes) {
                                LogUtil.i(TAG, listGankRes.getResults().toString());
                                if (!MVCActivity.this.isFinishing() && !MVCActivity.this.isDestroyed()) {
                                    showOutput("yes, I get it from net!");
                                }
                            }
                        });
                break;
        }
    }
}
</pre>