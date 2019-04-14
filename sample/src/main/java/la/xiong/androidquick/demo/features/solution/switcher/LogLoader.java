package la.xiong.androidquick.demo.features.solution.switcher;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
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
