package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.studytree.R;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.MainActivity;
import com.studytree.view.adapter.MainViewPagerAdapter;
import com.studytree.view.base.BaseFragment;
import com.studytree.view.widget.HorizontalViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面（相对菜单）封装Fragment
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
    private HomeFragment home_pager;
    private NewsFragment news_pager;
    private MineFragment mine_pager;

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
        layoutParams.height = StudyTreeTools.getStatusBarHeight(mActivity);

        //初始化底部Radio
        mRadioGroup = mRootView.findViewById(R.id.main_tab_radio);
        rb_tab_home = mRootView.findViewById(R.id.main_tab_home);
        rb_tab_news = mRootView.findViewById(R.id.main_tab_news);
        rb_tab_me = mRootView.findViewById(R.id.main_tab_me);
        changeImageSize();

        //初始化界面View
        List mPagerList = new ArrayList<BaseFragment>();
        home_pager = new HomeFragment(mActivity);
        news_pager = new NewsFragment(mActivity);
        mine_pager = new MineFragment(mActivity);
        mPagerList.add(home_pager);
        mPagerList.add(news_pager);
        mPagerList.add(mine_pager);
        //初始化ViewPager
        main_viewpager = mRootView.findViewById(R.id.main_viewpager);
        //不允许ViewPager创建新的页面以防止出现页面重复显示Bug（在此项目中必须）
        main_viewpager.setOffscreenPageLimit(3);

        //初始化ViewPager适配器
        mPagerAdapter = new MainViewPagerAdapter(mActivity.getSupportFragmentManager(), mActivity, mPagerList);
        main_viewpager.setAdapter(mPagerAdapter);
        //注意setOnPageChangeListener被addOnPageChangeListener取代
        main_viewpager.addOnPageChangeListener(this);
        main_viewpager.setCurrentItem(0);

        //设置监听
        mRadioGroup.setOnCheckedChangeListener(this);

        return mRootView;
    }

    @Override
    public void initData() {
        super.initData();
        mRadioGroup.check(R.id.main_tab_home);
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

    public HomeFragment getHome_pager() {
        return home_pager;
    }

    public MineFragment getMine_pager() {
        return mine_pager;
    }

    public NewsFragment getNews_pager() {
        return news_pager;
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
            case R.id.main_tab_home:                //首页
                main_viewpager.setCurrentItem(0, false);
                break;
            case R.id.main_tab_news:                //资讯
                main_viewpager.setCurrentItem(1, false);
                break;
            case R.id.main_tab_me:                  //我的
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
        //动画集合
        AnimationSet animationSet = new AnimationSet(false);
        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f,1.0f);
        alphaAnimation.setDuration(200);
        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f,1.0f,0.5f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(200);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        radioButton.startAnimation(animationSet);

        if(!radioButton.isChecked()){
            radioButton.setChecked(true);
        }
        if(main_viewpager.getCurrentItem() != currentIndex){
            main_viewpager.setCurrentItem(currentIndex);
        }
    }
}
