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
 * 主页BannerAdapter
 * Title: HomeBannerAdapter
 * @date 2018/7/12 17:00
 * @author Freedom0013
 */
public class HomeBannerAdapter extends PagerAdapter {
    private static final String TAG = HomeBannerAdapter.class.getSimpleName();
    /** BannerView */
    List<View> views;

    public HomeBannerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        try {
            ViewPager viewPager = (ViewPager) container;
            View v = (View) object;
            viewPager.removeView(v);
        } catch (Exception e) {
            Logger.e(TAG, "BannerAdapter异常", e);
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void finishUpdate(View v) {
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

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(@NonNull View container) {
    }
}
