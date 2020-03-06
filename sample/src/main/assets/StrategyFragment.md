## SingleFragment
<pre>
    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                //tiny log
                LogLoader.load(new TinyLogProcessor());
                LogLoader.getInstance().d("this is tiny log");
                break;
            case 1:
                //system default log
                LogLoader.load(new DefaultLogProcessor());
                LogLoader.getInstance().d("this is system default log");
                // LogLoader2.getInstance().useDmode("this is system default log");
                break;
        }
    }


    public class LogLoader implements ILogProcessor{

    private static volatile LogLoader sInstance = null;
    private static ILogProcessor sILogProcessor;
    private LogLoader() {

    }
    public static LogLoader getInstance () {
        if (sInstance == null) {
            synchronized(LogLoader.class){
                if (sInstance == null) {
                    sInstance = new LogLoader();
                }
            }
        }
        return sInstance;
    }

    public static ILogProcessor load(ILogProcessor logProcessor) {
        return sILogProcessor = logProcessor;
    }

    @Override
    public void v(String vLog) {
        sILogProcessor.v(vLog);
    }

    @Override
    public void d(String dLog) {
        sILogProcessor.d(dLog);
    }

    @Override
    public void i(String iLog) {
        sILogProcessor.i(iLog);
    }

    @Override
    public void e(String eLog) {
        sILogProcessor.e(eLog);
    }
}



</pre>