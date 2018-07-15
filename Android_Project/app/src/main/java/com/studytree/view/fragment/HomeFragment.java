package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.bean.BannerBean;
import com.studytree.bean.DepartmentBean;
import com.studytree.bean.PictureBean;
import com.studytree.commonfile.Constants;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.logic.InitLogic;
import com.studytree.log.Logger;
import com.studytree.utils.StringUtils;
import com.studytree.view.MainActivity;
import com.studytree.view.adapter.HomeBannerAdapter;
import com.studytree.view.adapter.HomeDepartmentGridAdapter;
import com.studytree.view.base.BaseFragment;
import com.studytree.view.widget.InnerViewPager;
import com.studytree.view.widget.StudyTreeTitleBar;
import com.studytree.view.widget.UnscrollGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页界面Fragment
 * Title: HomeFragment
 * @date 2018/7/10 17:16
 * @author Freedom0013
 */
public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener,InnerViewPager.OnSingleTouchListener,AdapterView.OnItemClickListener,StudyTreeTitleBar.TitleBarClickListener{
    private static final String TAG = HomeFragment.class.getSimpleName();
    /** Activity对象 */
    private MainActivity mActivity;
    /** BannerViewPager */
    private InnerViewPager mAdViewPager;
    /** Banner容器 */
    private FrameLayout mAdFrame;
    /** Banner小圆点 */
    private RadioGroup mAdRadioGroup;
    /** BannerAdapter */
    private HomeBannerAdapter mAdAdapter;
    /** BannerView容器 */
    private List<View> mAdViews;
    /** Banner滑动当前页 */
    private volatile int current;
    /** HomeFragment初始化完成标识 */
    private boolean isinit = false;
    /** 系别GridView */
    private UnscrollGridView mGridView;
    /** 系别GridView数据集合 */
    private List<DepartmentBean> mDepartmentData;
    /** 系别GridView适配器 */
    private HomeDepartmentGridAdapter mAdapter;
    /** 首页滑动层 */
    private ScrollView home_scrollview;
    /** 首页ToolBar */
    private StudyTreeTitleBar home_main_tool;
    /** 首页toolbar隐藏标识 */
    private Boolean isShow = true;

    /** 空参构造函数（必须） */
    public HomeFragment(){}

    /**
     * 构造函数
     * @param activity Activity对象
     */
    @SuppressLint("ValidFragment")
    public HomeFragment(Activity activity) {
        mActivity = (MainActivity)activity;
    }

    @Override
    public View initView() {
        View mRootView = View.inflate(mActivity, R.layout.tab_home, null);
        //配置toolBar
        home_scrollview = mRootView.findViewById(R.id.home_scrollview);
        home_main_tool = mRootView.findViewById(R.id.home_tool);
        home_main_tool.setTitleRightVisibility(false);
        //添加系统
        mActivity.setSupportActionBar(home_main_tool);

        //配置toolbar随滑动消失
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            home_scrollview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    float heightPixels = getContext().getResources().getDisplayMetrics().heightPixels;
                    final float fscrollY = scrollY;//该值 大于0
                    final float alpha = 1 - fscrollY / (heightPixels / 3);// 0~1  透明度是1~0
                    //这里选择的screenHeight的1/3 是alpha改变的速率 （根据你的需要你可以自己定义）
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //可设置ToolBar滑动隐藏
//                            home_main_tool.setTranslationY(-fscrollY);
                            home_main_tool.setAlpha(alpha);
                            if (alpha < 0) {
                                if (isShow) {
                                    home_main_tool.setVisibility(View.GONE);
                                    isShow = false;
                                }
                            } else {
                                if (!isShow) {
                                    home_main_tool.setVisibility(View.VISIBLE);
                                    isShow = true;
                                }
                            }
                        }
                    });
                }
            });
        }
        //设置toolbar点击事件
        home_main_tool.setOnTitleBarClickedListener(this);

        //初始化banner
        mAdFrame = mRootView.findViewById(R.id.home_banner_frame);
        mAdViewPager = mRootView.findViewById(R.id.home_banner_viewpager);
        mAdRadioGroup = mRootView.findViewById(R.id.home_banner_radiogroup);
        mAdViewPager.setFocusable(true);
        mAdViewPager.setFocusableInTouchMode(true);
        mAdViewPager.requestFocus();

        //初始化系别GridView
        mGridView = mRootView.findViewById(R.id.home_department_grid);

        return mRootView;

//        final Toolbar home_main_tool = mRootView.findViewById(R.id.home_main_tool);
//        View titlebar = getLayoutInflater().inflate(R.layout.layout_titlebar, home_main_tool);
//        mActivity.setSupportActionBar(home_main_tool);
    }

    @Override
    public void initData() {
        if (isinit) {
            return;
        }
        isinit = true;

        //初始化系别GridView
        mDepartmentData = new ArrayList<DepartmentBean>();
        mAdapter = new HomeDepartmentGridAdapter((MainActivity)mActivity, mDepartmentData);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);

        //获取系别数据
        doGetDepartment(InitManager.getInstance().getStringPreference("department_list"));
        InitLogic.getInstance().getDepartmentInfo(new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                doGetDepartment((String) obj);
            }

            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.e(TAG, "获取Department错误");
            }
        });

        //配置banner
        fixTopFrameSize();
        //优先读取本地Banner数据加载
        doGetBanner(InitManager.getInstance().getStringPreference("banner_list"));
        InitLogic.getInstance().getBannnerInfo(new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                doGetBanner((String) obj);
            }
            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.e(TAG, "获取Banner错误");
            }
        });
        startAutoScroll();
    }

    /**
     * 获取到系别数据
     * @param dataStr 系别Json
     */
    public void doGetDepartment(String dataStr) {
        if (StringUtils.isNullOrEmpty(dataStr)) {
            return;
        }
        //本地缓存Json数据
        InitManager.getInstance().saveStringPreference("department_list", dataStr);
        List<DepartmentBean> departmentlist = new ArrayList<DepartmentBean>();
        if (dataStr != null) {
            try {
                //解析Json
                JsonObject data = new JsonParser().parse(dataStr).getAsJsonObject();
                Gson gson = new Gson();
                JsonArray dataArray = data.getAsJsonArray("data");
                JsonElement rootinfo = dataArray.get(0);
                JsonObject rootinfos = new JsonParser().parse(rootinfo.toString()).getAsJsonObject();
                JsonArray root = rootinfos.getAsJsonArray("root");
                JsonElement picinfo = dataArray.get(1);
                JsonObject picinfos = new JsonParser().parse(picinfo.toString()).getAsJsonObject();
                JsonArray pic = picinfos.getAsJsonArray("pic");
                if (root != null) {
                    for (JsonElement department : root) {
                        DepartmentBean departmentbean = gson.fromJson(department, DepartmentBean.class);
                        departmentlist.add(departmentbean);
                    }
                }
                if (pic != null) {
                    for (JsonElement picture : pic) {
                        PictureBean picturebean = gson.fromJson(picture, PictureBean.class);
                        for (DepartmentBean department : departmentlist) {
                            int isequals = department.department_picture_id.compareTo(picturebean.picture_id);
                            if (isequals == 0) {
                                department.department_image_url = "http://" + Constants.HOST + "/" + picturebean.picture_img;
                            }
                        }
                    }
                }
                initDepartmentList(departmentlist);
            } catch (Exception e) {
                Logger.e(TAG, "解析系别错误！", e);
                initDepartmentList(null);
            }
        }
    }

    /**
     * 更新系别UI
     * @param list 系别数据
     */
    public void initDepartmentList(final List<DepartmentBean> list) {
        MainActivity act = (MainActivity) mActivity;
        act.mBasehandler.post(new Runnable() {
            @Override
            public void run() {
                mDepartmentData.clear();
                if (list != null && !list.isEmpty()) {
                    mDepartmentData.addAll(list);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 网络获取到Banner数据
     * @param dataStr BannerJson
     */
    public void doGetBanner(String dataStr){
        if(StringUtils.isNullOrEmpty(dataStr)){
            return;
        }
        //本地缓存Json数据
        InitManager.getInstance().saveStringPreference("banner_list", dataStr);
        List<BannerBean> bannerlist = new ArrayList<BannerBean>();
        if (dataStr != null) {
            try {
                //解析Json
                JsonObject data = new JsonParser().parse(dataStr).getAsJsonObject();
                Gson gson = new Gson();
                JsonArray jsonArray = data.getAsJsonArray("data");
                if(jsonArray!=null){
                    for (JsonElement banner : jsonArray) {
                        BannerBean bannerbean = gson.fromJson(banner, BannerBean.class);
                        bannerlist.add(bannerbean);
                    }
                }
                initViewPager(bannerlist);
            } catch (Exception e) {
                Logger.e(TAG,"解析Banner错误",e);
                initViewPager(null);
            }
        }
    }

    /**
     * 修正Banner大小
     */
    private void fixTopFrameSize(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mAdFrame.getLayoutParams();
        params.height = (int) (HomeFragment.this.getActivity().getResources().getDisplayMetrics().widthPixels / 3.12);
        mAdFrame.setLayoutParams(params);
    }

    /**
     * Banner轮播
     */
    private void startAutoScroll(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(mAdAdapter != null && mAdAdapter.getCount() > 0){
                        current = mAdViewPager.getCurrentItem();
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            Logger.e(TAG,"Banner轮播出错",e);
                        }
                        current = current + 1;
                        if(current >= mAdAdapter.getCount()){
                            current = 0;
                        }
                        final int pos = current;
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdViewPager.setCurrentItem(pos);
                            }
                        });
                    }
                }
            }
        }).start();
    }

    /**
     * 初始化BannerView
     * @param objs Banner集合
     */
    public void initViewPager(final List<BannerBean> objs) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (objs == null || objs.isEmpty()) {
                    mAdFrame.setVisibility(View.GONE);
                    return;
                }
                mAdFrame.setVisibility(View.VISIBLE);
                if (mAdViews == null) {
                    mAdViews = new ArrayList<View>();
                }
                mAdViews.clear();
                mAdRadioGroup.removeAllViews();
                int size = objs.size();
                //设置小圆点大小
                int rightMargin = mActivity.getResources().getDimensionPixelSize(R.dimen.ad_radio_size);
                for (int i = 0; i < size; i++) {
                    final BannerBean banner = objs.get(i);
                    View bannerView = mActivity.getLayoutInflater().inflate(R.layout.layout_banner, null);
                    final ImageView img = bannerView.findViewById(R.id.banner_image);
                    //设置banner地址，以便点击事件传递
                    bannerView.setTag(banner.banner_url);
                    mAdViews.add(bannerView);
                    //ImageLoader本地缓存Banner图片
                    DisplayImageOptions options = new DisplayImageOptions.Builder()
                            .cacheInMemory(true)//让图片进行内存缓存
                            .cacheOnDisk(true)//让图片进行sdcard缓存
                            .build();
                    //加载图片方法
                    ImageLoader.getInstance().displayImage(banner.banner_image, img, options);
                    //生成小圆点填充
                    RadioButton radioButton = new RadioButton(mActivity);
                    radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
                    try {
                        radioButton.setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.selector_banner_radiobutton_bg));
                    } catch (Resources.NotFoundException e) {
                        Logger.d(TAG, "initViewPager()--未找到资源异常-" + e.toString());
                        e.printStackTrace();
                    }
                    RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                    if (i != (size - 1)) {
                        params.rightMargin = rightMargin;
                    }
                    mAdRadioGroup.addView(radioButton, params);
                }
                ((RadioButton) mAdRadioGroup.getChildAt(0)).setChecked(true);
                mAdAdapter = new HomeBannerAdapter(mAdViews);
                mAdViewPager.setAdapter(mAdAdapter);
                mAdViewPager.setOnPageChangeListener(HomeFragment.this);
                mAdViewPager.setOnSingleTouchListener(HomeFragment.this);
            }
        });
    }

    /**
     * 获取HomeFragment初始化标识
     * @return 初始化标识
     */
    public boolean isInited(){
        return isinit;
    }

    //系别点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DepartmentBean bean = (DepartmentBean) mAdapter.getItem(position);
        Logger.d(TAG,bean.toString());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //当Banner滑动，防止线程重复修改，重新对current赋值
        current = position;
    }

    @Override
    public void onPageSelected(final int position) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //小圆点跟随轮播移动
                ((RadioButton) mAdRadioGroup.getChildAt(position)).setChecked(true);
            }
        });
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    //toolbar点击事件
    @Override
    public void onTitleLeftClicked() {
        //打开侧边栏
        mActivity.main_residelayout.openPane();
    }

    @Override
    public void onTitleRightClicked() {

    }

    @Override
    public void onTitleClicked() {

    }

    //Banner点击事件
    @Override
    public void onSingleTouch(View v) {
        //TODO:待补全Banner进入页面
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int currentIndex = mAdViewPager.getCurrentItem();
                showToast("点击了"+currentIndex);
            }
        });
//        int currentIndex = mAdViewPager.getCurrentItem();
//        View adView = mAdViews.get(currentIndex);
//        String url = (String) adView.getTag();
//        if(!StringUtils.isNullOrEmpty(url)){
//            Intent intent = new Intent(mActivity, WebViewActivity.class);
//            intent.putExtra("from", 0);
//            intent.putExtra("url", url);
//            mActivity.startActivity(intent);
//        }
    }
}
