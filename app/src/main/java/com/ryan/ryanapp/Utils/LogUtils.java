package com.ryan.ryanapp.Utils;

import android.util.Log;

import com.ryan.ryanapp.Constants;


/**
 * 类描述	打印日志类
 * 创建日期 ： 2014年12月1日 下午2:29:34
 */
public class LogUtils {

    private static String mTag = "tianma";

    public static final void d(String filterTag, String msg) {

        if (Constants.PRINT_LOG) {
            Log.d(filterTag, filterTag + "   :  " + msg);
        }
    }

    public static final void e(String filterTag, String msg) {

        if (Constants.PRINT_LOG) {
            Log.e(filterTag, filterTag + "   :  " + msg);
        }
    }

    public static final void i(String filterTag, String msg) {

        if (Constants.PRINT_LOG) {
            Log.i(filterTag, filterTag + "   :  " + msg);
        }
    }

    public static final void w(String filterTag, String msg) {

        if (Constants.PRINT_LOG) {
            Log.w(filterTag, filterTag + "   :  " + msg);
        }
    }

    /**
     * 以级别为 e 的形式输出Throwable
     */
    public static void e(Throwable tr) {

        if (Constants.PRINT_LOG) {
            Log.e(mTag, "", tr);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG信息和Throwable
     */
    public static void e(String msg, Throwable tr) {

        if (Constants.PRINT_LOG && null != msg) {
            Log.e(mTag, msg, tr);
        }
    }
}
