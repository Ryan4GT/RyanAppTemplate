package com.ryan.ryanapp.Utils;

import android.util.TypedValue;

import com.ryan.ryanapp.RyanApp;


/**
 * 类描述  长度、字符串等格式转换类
 * 创建日期 ： 2014年12月1日 下午2:30:57
 */
public class UnitFormatter {

    /**
     * 获取以dp为单位的长度值
     */
    public static int getDPUnit(int value) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, RyanApp.instance.getApplicationContext().getResources().getDisplayMetrics());
    }

    /**
     * 获取以sp为单位的长度值
     */
    public static int getSPUnit(int value) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, RyanApp.instance.getApplicationContext().getResources().getDisplayMetrics());
    }

    public static float dp2px(int value) {

        final float scale = RyanApp.instance.getApplicationContext().getResources().getDisplayMetrics().density;
        int px = (int) (value * scale + 0.5f);
        return px;
    }
}
