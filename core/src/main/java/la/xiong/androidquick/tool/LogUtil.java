package la.xiong.androidquick.tool;

import android.util.Log;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class LogUtil {

    public static final String TAG = "LogUtil";
    public static final int LOG_LEVEL = Log.VERBOSE;

    private static boolean logOn = true;

    public static void dumpException(Throwable t) {
        if (isLoggable(Log.WARN)) {
            final int maxLen = 256;
            StringBuilder err = new StringBuilder(maxLen);

            err.append("Got exception: ");
            err.append(t.toString());
            err.append("\n");

            System.out.println(err.toString());
            t.printStackTrace(System.out);
        }
    }

    public static void v(String aTag, String aMsg) {
        log(Log.VERBOSE, aTag, aMsg);
    }

    public static void v(String aTag, String aMsg, Throwable aThrowable) {
        log(Log.VERBOSE, aTag, aMsg, aThrowable);
    }

    public static void d(String aTag, String aMsg) {
        log(Log.DEBUG, aTag, aMsg);
    }

    public static void d(String aTag, String aMsg, Throwable aThrowable) {
        log(Log.DEBUG, aTag, aMsg, aThrowable);
    }

    public static void i(String aTag, String aMsg) {
        log(Log.INFO, aTag, aMsg);
    }

    public static void i(String aTag, String aMsg, Throwable aThrowable) {
        log(Log.INFO, aTag, aMsg, aThrowable);
    }

    public static void w(String aTag, String aMsg) {
        log(Log.WARN, aTag, aMsg);
    }

    public static void w(String aTag, String aMsg, Throwable aThrowable) {
        log(Log.WARN, aTag, aMsg, aThrowable);
    }

    public static void e(String aTag, String aMsg) {
        log(Log.ERROR, aTag, aMsg);
    }

    public static void e(String aTag, String aMsg, Throwable aThrowable) {
        log(Log.ERROR, aTag, aMsg, aThrowable);
    }

    public static void log(int aLogLevel, String aTag, String aMessage) {
        log(aLogLevel, aTag, aMessage, null);
    }

    /**
     * log Send a logLevel log message and log the exception, then collect the log entry.
     *
     * @param aLogLevel  Used to identify log level/
     * @param aTag       Used to identify the source of a log message. It usually identifies the class or activity
     *                   where the
     *                   log call occurs.
     * @param aMessage   The message you would like logged.
     * @param aThrowable An exception to log
     */
    public static void log(int aLogLevel, String aTag, String aMessage, Throwable aThrowable) {
        if (logOn && isLoggable(aLogLevel)) {
            switch (aLogLevel) {
                case Log.VERBOSE:
                    Log.v(TAG, aTag + ": " + aMessage, aThrowable);
                    break;
                case Log.DEBUG:
                    Log.d(TAG, aTag + ": " + aMessage, aThrowable);
                    break;
                case Log.INFO:
                    Log.i(TAG, aTag + ": " + aMessage, aThrowable);
                    break;
                case Log.WARN:
                    Log.w(TAG, aTag + ": " + aMessage, aThrowable);
                    break;
                case Log.ERROR:
                    Log.e(TAG, aTag + ": " + aMessage, aThrowable);
                    break;
                default:
                    Log.e(TAG, aTag + ": " + aMessage, aThrowable);
            }
        }
    }

    /**
     * call when enter the method body that you want to debug with only one line
     */
    public static void method() {
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        if (null == stack || 2 > stack.length) {
            return;
        }

        StackTraceElement s = stack[1];
        if (null != s) {
            String className = s.getClassName();
            String methodName = s.getMethodName();
            d(className, "+++++" + methodName);
        }
    }

    /**
     * call when enter the method body that you want to debug.
     */
    public static void enter() {
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        if (null == stack || 2 > stack.length) {
            return;
        }

        StackTraceElement s = stack[1];
        if (null != s) {
            String className = s.getClassName();
            String methodName = s.getMethodName();
            d(className, "====>" + methodName);
        }
    }

    /**
     * call when leave the method body that you want to debug.
     */
    public static void leave() {
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        if (null == stack || 2 > stack.length) {
            return;
        }

        StackTraceElement s = stack[1];
        if (null != s) {
            String className = s.getClassName();
            String methodName = s.getMethodName();
            d(className, "<====" + methodName);
        }
    }

    /**
     * Checks to see whether or not a log for the specified tag is loggable at the specified level.
     *
     * @param aLevel The level to check.
     *
     * @return Whether or not that this is allowed to be logged.
     */
    public static boolean isLoggable(int aLevel) {

        if (aLevel >= LOG_LEVEL) {
            return true;
        } else {
            return false;
        }
    }

    public static void setLogOn(boolean logOn) {
        LogUtil.logOn = logOn;
    }
}