package com.studytree.view.adapter;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.studytree.log.Logger;

import java.util.List;

/**
 * 新手引导页ViewPager适配器
 * Title: IntroViewPagerAdapter
 * @date 2018/7/6 17:27
 * @author Freedom0013
 */
public class IntroViewPagerAdapter extends PagerAdapter {
    public static final String TAG = IntroViewPagerAdapter.class.getSimpleName();
    List<View> views;

    /**
     * 构造适配器
     * @param views 引导页集合
     */
    public IntroViewPagerAdapter(List<View> views) {
        this.views = views;
    }

    /** 回收条目 */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        try {
            ViewPager viewPager = (ViewPager) container;
            View v = (View) object;
            viewPager.removeView(v);
        } catch (Exception e) {
            Logger.e(TAG,"destroyItem错误",e);
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(views.get(position), 0);
        return views.get(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
    }

    @Nullable
    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
    }
}
