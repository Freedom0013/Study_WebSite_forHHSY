package com.studytree.view.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.studytree.log.Logger;
import com.studytree.view.base.BaseFragment;

import java.util.List;

/**
 * MainActivity-ViewPager-Adapter
 * Title: MainViewPagerAdapter
 * @date 2018/7/10 12:45
 * @author Freedom0013
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = MainViewPagerAdapter.class.getSimpleName();
    /** Activity对象 */
    private Activity mActivity;
    /** 布局集合 */
    List<BaseFragment> mPagerList;

    /**
     * MainActivity-ViewPager-Adapter构造函数
     * @param activity Activity对象
     * @param pagerlist ViewPager集合
     */
    public MainViewPagerAdapter(FragmentManager manager,Activity activity, List<BaseFragment> pagerlist){
        super(manager);
        mActivity = activity;
        mPagerList = pagerlist;
    }

    @Override
    public int getCount() {
        return mPagerList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mPagerList.get(position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        try {
            ViewPager viewPager = (ViewPager) container;
            View v = (View) object;
            viewPager.removeView(v);
        } catch (Exception e) {
            Logger.e(TAG, "BannerAdapter异常", e);
        }
    }
}
