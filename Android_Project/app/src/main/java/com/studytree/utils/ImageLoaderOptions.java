package com.studytree.utils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.studytree.commonfile.Constants;

/**
 * ImageLoader配置类
 * Title: ImageLoaderOptions
 * @date 2018/6/27 15:33
 * @author Freedom0013
 */
public class ImageLoaderOptions {
    /**
     * 获取ImageLoaderOptions信息
     * @return DisplayImageOptions对象
     */
    public static DisplayImageOptions getImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(Constants.default_image) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(Constants.default_image)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(Constants.default_image)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(50))//是否设置为圆角，弧度为多少
                .build();
        return options;
    }
}
