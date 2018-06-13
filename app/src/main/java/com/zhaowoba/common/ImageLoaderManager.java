/*
package com.zhaowoba.common;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.zhaowoba.R;
import com.zhaowoba.activity.AdActivity;
import com.zhaowoba.activity.WelcomActivity;

*/
/**
 * Created by 念阿郎 on 2018/5/16.
 * 图片缓存框架
 *//*


public class ImageLoaderManager extends ImageLoader{

   private static ImageLoaderManager mInstance;

    //实例化对象
    public static ImageLoaderManager getInstance(Context context){
        if(mInstance==null){
            synchronized (ImageLoaderManager.class){
                if(mInstance==null){
                    mInstance=new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    public ImageLoaderManager(Context context){
        if(mInstance==null){
            //采用自定义的配置
            ImageLoaderManager.getInstance().init(customImageLoaderConfig(context));
        }
    }


    //自定义图片显示策略
    public static DisplayImageOptions options=

   //自定义配置
    public ImageLoaderConfiguration customImageLoaderConfig(Context context){
        ImageLoaderConfiguration config =
        return config;
    }
}
*/
