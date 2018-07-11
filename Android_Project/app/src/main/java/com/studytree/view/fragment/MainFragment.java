package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.studytree.R;
import com.studytree.view.MainActivity;
import com.studytree.view.adapter.MainViewPagerAdapter;
import com.studytree.view.base.BaseFragment;
import com.studytree.view.widget.HorizontalViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页Fragment
 * Title: MainFragment
 * @date 2018/7/10 14:31
 * @author Freedom0013
 */
public class MainFragment extends BaseFragment implements ViewPager.OnPageChangeListener,RadioGroup.OnCheckedChangeListener{
    private static final String TAG = MainFragment.class.getSimpleName();
    /** Activity对象 */
    private MainActivity mActivity;
    /** 底部RadioGroup */
    private RadioGroup mRadioGroup;
    /** 首页 */
    private RadioButton rb_tab_home;
    /** 资讯 */
    private RadioButton rb_tab_news;
    /** 我的 */
    private RadioButton rb_tab_me;
    /** ViewPager容器 */
    private HorizontalViewPager main_viewpager;
    /** 主界面ViewPager适配器 */
    private MainViewPagerAdapter mPagerAdapter;

    /** 空参构造函数（必须） */
    public MainFragment(){}

    /**
     * 主界面Fragment构造函数
     * @param activity Activity对象
     */
    @SuppressLint("ValidFragment")
    public MainFragment(Activity activity) {
        mActivity = (MainActivity) activity;
    }

    @Override
    public View initView() {
        View mRootView = View.inflate(mActivity, R.layout.fragment_main, null);
        //设置占位View以实现沉浸式状态栏
        View statusBar = mRootView.findViewById(R.id.statusBarView);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = getStatusBarHeight(mActivity);

        //初始化Radio
        mRadioGroup = mRootView.findViewById(R.id.main_tab_radio);
        rb_tab_home = mRootView.findViewById(R.id.main_tab_home);
        rb_tab_news = mRootView.findViewById(R.id.main_tab_news);
        rb_tab_me = mRootView.findViewById(R.id.main_tab_me);
        changeImageSize();

        //初始化ViewPager
        List mPagerList = new ArrayList<BaseFragment>();
        mPagerList.add(new HomeFragment(mActivity));
        mPagerList.add(new NewsFragment(mActivity));
        mPagerList.add(new MineFragment(mActivity));
        //初始化ViewPager
        main_viewpager = mRootView.findViewById(R.id.main_viewpager);

        //初始化ViewPager适配器
        mPagerAdapter = new MainViewPagerAdapter(mActivity.getSupportFragmentManager(), mActivity, mPagerList);
        main_viewpager.setAdapter(mPagerAdapter);
        main_viewpager.setCurrentItem(0);

        //设置监听
        mRadioGroup.setOnCheckedChangeListener(this);
        //注意setOnPageChangeListener被addOnPageChangeListener取代
        main_viewpager.addOnPageChangeListener(this);

        return mRootView;
    }

    @Override
    public void initData() {
        super.initData();
        mRadioGroup.check(R.id.main_tab_home);
    }

    /**
     * 反射获取系统状态栏高度
     * @param context Context对象
     * @return 状态栏高度
     */
    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 格式化RadioButton图片大小
     */
    private void changeImageSize() {
        int marginVertical = 10;
        int size = 85;
        //定义底部标签图片大小
        Drawable drawableHome = getResources().getDrawable(R.drawable.selector_tab_home);
        //参数1：是距左右边距离||参数2：是距上下边距离||参数3、4：长度,宽度
        drawableHome.setBounds(0, marginVertical, size, size);
        //只放上面
        rb_tab_home.setCompoundDrawables(null, drawableHome, null, null);
        Drawable drawableNews = getResources().getDrawable(R.drawable.selector_tab_news);
        drawableNews.setBounds(0, marginVertical, size, size);
        rb_tab_news.setCompoundDrawables(null, drawableNews, null, null);
        Drawable drawableMe = getResources().getDrawable(R.drawable.selector_tab_me);
        drawableMe.setBounds(0, marginVertical, size, size);
        rb_tab_me.setCompoundDrawables(null, drawableMe, null, null);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrent(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_tab_home:
                main_viewpager.setCurrentItem(0, false);
                break;
            case R.id.main_tab_news:
                main_viewpager.setCurrentItem(1, false);
                break;
            case R.id.main_tab_me:
                main_viewpager.setCurrentItem(2, false);
                break;
            default:
                break;
        }
    }

    /**
     * 设置ViewPager跟随滑动选择
     * @param currentIndex 当前页面
     */
    public void setCurrent(int currentIndex){
        RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(currentIndex);
        if(!radioButton.isChecked()){
            radioButton.setChecked(true);
        }
        if(main_viewpager.getCurrentItem() != currentIndex){
            main_viewpager.setCurrentItem(currentIndex);
        }
    }

}
