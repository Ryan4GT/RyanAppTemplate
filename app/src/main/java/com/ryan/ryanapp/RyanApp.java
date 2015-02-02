package com.ryan.ryanapp;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.ryan.ryanapp.leancloud.UniversualImageLoaderUtils;
import com.ryan.ryanapp.leancloud.bean.TestAVObject;
import com.ryan.ryanapp.model.Size;

import com.ryan.ryanapp.leancloud.bean.Goods;
import com.ryan.ryanapp.leancloud.LeanCloudUtils;
import com.ryan.ryanapp.leancloud.bean.GoodsComment;


/**
 * Created by Ryan
 * 类描述 ：
 * 创建时间 ：  2015/1/23 13:12.
 */
public class RyanApp extends Application {

    public static RyanApp instance;
    private Size screenSize;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Class[] clazz = new Class[]{Goods.class, GoodsComment.class, TestAVObject.class};
        LeanCloudUtils.initLeanCloud(this, false, clazz);
        UniversualImageLoaderUtils.initImageLoader(getApplicationContext());
    }

    /**
     * 获取屏幕尺寸大小
     */
    public Size getScreentSize() {

        if(screenSize == null) {
            WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics();
            display.getMetrics(outMetrics);
            screenSize = new Size(outMetrics.widthPixels, outMetrics.heightPixels);
        }
        return screenSize;
    }



}
