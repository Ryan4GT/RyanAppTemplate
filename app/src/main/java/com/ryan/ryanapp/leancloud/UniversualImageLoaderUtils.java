package com.ryan.ryanapp.leancloud;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.BaseDiscCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.ryan.ryanapp.R;

import java.io.File;
/**
 * 作者: Ryan
 * 创建时间:15/1/28 11:42
 * 类描述:
 */
public class UniversualImageLoaderUtils {

    public static final void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, context.getPackageName() + "/imgs");
        BaseDiscCache baseDiscCache = new UnlimitedDiscCache(cacheDir);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO).diskCache(baseDiscCache).build();
        //                .writeDebugLogs() // Remove for release app
        ImageLoader.getInstance().init(config);
    }

    public static DisplayImageOptions getDisplayImageOptions(){

        return  new DisplayImageOptions.Builder().considerExifParams(true).cacheInMemory(true).cacheOnDisk(true).showImageOnLoading(R.drawable.naruto).displayer(new FadeInBitmapDisplayer(3000)).build();
    }

}
