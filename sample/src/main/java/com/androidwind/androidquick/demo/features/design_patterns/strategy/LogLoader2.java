package com.androidwind.androidquick.demo.features.design_patterns.strategy;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class LogLoader2 {

    private static volatile LogLoader2 sInstance = null;
    private static ILogProcessor sILogProcessor;
    private LogLoader2() {

    }
    public static LogLoader2 getInstance () {
        if (sInstance == null) {
            synchronized(LogLoader2.class){
                if (sInstance == null) {
                    sInstance = new LogLoader2();
                }
            }
        }
        return sInstance;
    }

    public static ILogProcessor load(ILogProcessor logProcessor) {
        return sILogProcessor = logProcessor;
    }

    public void useVmode(String vLog) {
        sILogProcessor.v(vLog);
    }

    public void useDmode(String dLog) {
        sILogProcessor.d(dLog);
    }

    public void useImode(String iLog) {
        sILogProcessor.i(iLog);
    }

    public void useEmode(String eLog) {
        sILogProcessor.e(eLog);
    }
}
