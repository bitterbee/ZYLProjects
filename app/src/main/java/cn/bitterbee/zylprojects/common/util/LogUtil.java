package cn.bitterbee.zylprojects.common.util;

import android.util.Log;

import cn.bitterbee.zylprojects.BuildConfig;
import cn.bitterbee.zylprojects.application.AppProfile;

/**
 * Created by zyl06 on 9/18/15.
 */
public class LogUtil {
    private static final String TAG = "ZYL";

    public static void logW(String msg) {
        Log.w(TAG, msg);
    }

    public static void logE(String msg) {
        Log.e(TAG, "error running: " + msg);

        try {
            if (!BuildConfig.DEBUG) {
                //Crashlytics.getInstance().log(msg);
            }
        } catch (Exception e) {
            Log.e(TAG, "error running: Crashlytics " + msg);
        }
    }

    public static void logPrintStackTrace(StackTraceElement[] ste) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement s : ste) {
            sb.append('\n');
            sb.append(s.toString());
        }
        logE(sb.toString());
    }

    public static void logD(String msg) {
        if (msg != null) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String Tag, String msg) {
        Log.i(Tag, msg);
    }

    public static void e(String Tag, String msg) {
        if (msg != null) Log.e(Tag, msg);
        try {
            if (!BuildConfig.DEBUG) {
                //Crashlytics.getInstance().log(msg);
            }
        } catch (Exception e) {
            Log.e(TAG, "error running: Crashlytics " + msg);
        }
    }

    public static void d(String Tag, String msg) {
        Log.d(Tag, msg);
    }
}
