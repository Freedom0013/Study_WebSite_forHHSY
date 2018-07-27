package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.bean.NewsBean;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.logic.InitLogic;
import com.studytree.log.Logger;
import com.studytree.utils.StringUtils;
import com.studytree.view.MainActivity;
import com.studytree.view.base.BaseFragment;
import com.studytree.view.widget.StudyTreeTitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 资讯页面Fragment
 * Title: NewsFragment
 * @date 2018/7/10 17:16
 * @author Freedom0013
 */
public class NewsFragment extends BaseFragment implements StudyTreeTitleBar.TitleBarClickListener{
    private static final String TAG = NewsFragment.class.getSimpleName();
    private MainActivity mActivity;
    private StudyTreeTitleBar news_tool;
    /** NewsFragment初始化完成标识 */
    private boolean isinit = false;
    private ArrayList<NewsBean> mNewsList;

    /** 空参构造函数（必须） */
    public NewsFragment(){}

    /**
     * 构造函数
     * @param activity Activity对象
     */
    @SuppressLint("ValidFragment")
    public NewsFragment(Activity activity) {
        mActivity = (MainActivity) activity;
    }

    @Override
    public View initView() {
        View mRootView = View.inflate(mActivity, R.layout.tab_news, null);

        //配置toolBar
        news_tool = mRootView.findViewById(R.id.news_tool);
        news_tool.setTitleRightVisibility(false);
        news_tool.setTitle("最新资讯");
        mActivity.setSupportActionBar(news_tool);
        news_tool.setOnTitleBarClickedListener(this);

        mNewsList = new ArrayList<NewsBean>();

        return mRootView;
    }

    @Override
    public void initData() {
        if (isinit) {
            return;
        }
        isinit = true;

        //获取资讯数据
        doGetNews(InitManager.getInstance().getStringPreference("news_list"));
        InitLogic.getInstance().getNewsInfo(new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                doGetNews((String) obj);
            }

            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.e(TAG, "获取News错误！responseCode = " + responseCode + "responseMsg = " + responseMsg);
                showToast("获取News失败！请检查网络");
            }
        });
    }

    /**
     * 解析资讯数据
     * @param dataStr Json
     */
    private void doGetNews(String dataStr) {
        if (StringUtils.isNullOrEmpty(dataStr)) {
            return;
        }
        //本地缓存Json数据
        InitManager.getInstance().saveStringPreference("news_list", dataStr);
        List<NewsBean> newslist = new ArrayList<NewsBean>();
        if (dataStr != null) {
            try {
                //解析Json
                JsonObject data = new JsonParser().parse(dataStr).getAsJsonObject();
                Gson gson = new Gson();
                String datapri = data.getAsJsonPrimitive("data").getAsString();
                datapri = datapri.replaceAll("\\\\","");
                JsonArray jsonArray = new JsonParser().parse(datapri).getAsJsonArray();
                if (jsonArray != null) {
                    for (JsonElement news : jsonArray) {
                        NewsBean newsbean = gson.fromJson(news, NewsBean.class);
                        newslist.add(newsbean);
                    }
                }
                initNewsList(newslist);
            } catch (Exception e) {
                Logger.e(TAG, "解析资讯错误！", e);
                initNewsList(null);
            }
        }
    }

    private void initNewsList(List<NewsBean> newslist) {
        Logger.d(TAG,newslist.toString());
    }

    @Override
    public void onTitleLeftClicked() {
        mActivity.openMenu();
    }

    @Override
    public void onTitleRightClicked() {

    }

    @Override
    public void onTitleClicked() {

    }
}
