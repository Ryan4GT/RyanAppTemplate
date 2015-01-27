package com.ryan.ryanapp;

import android.app.Application;

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

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Class[] clazz = new Class[]{Goods.class, GoodsComment.class};
        LeanCloudUtils.initLeanCloud(this, false, clazz);
    }
}
