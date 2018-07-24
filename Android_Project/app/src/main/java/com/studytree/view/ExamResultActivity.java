package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studytree.R;
import com.studytree.bean.AnswerQuestionBean;
import com.studytree.bean.CourseBean;
import com.studytree.bean.PictureBean;
import com.studytree.bean.ResourceBean;
import com.studytree.commonfile.Constants;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.logic.InitLogic;
import com.studytree.log.Logger;
import com.studytree.utils.StringUtils;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.adapter.ResourceListAdapter;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.StudyTreeTitleBar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 测试结果Activity
 * Title: ExamResultActivity
 * @date 2018/7/24 11:26
 * @author Freedom0013
 */
public class ExamResultActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener,ResourceListAdapter.ResourceItemOnClickListener {
    public static final String TAG = ExamResultActivity.class.getSimpleName();
    /** 课程bean */
    private CourseBean mCourseBean;
    /** 测试试题Json信息 */
    private String questionJsonData;
    /** 测试用户答案信息 */
    private HashMap<BigDecimal, String> user_answer_map;
    /** 分数 */
    private String score;
    /** 分数显示 */
    private TextView user_score;
    /** 分数提示 */
    private TextView exam_result_hint;
    /** 资源RecyclerView */
    private RecyclerView resource_list;
    /** 资源列表 */
    private List<ResourceBean> mResourcelist;
    /** 资源RecyclerView适配器 */
    private ResourceListAdapter mResAdapter;

    /**
     * 启动SettingActivity
     * @param ctx 来源Context
     */
    public static void start(Context ctx, String questionJsonData, CourseBean bean, HashMap<BigDecimal, String> user_answer_map) {
        Intent intent = new Intent(ctx, ExamResultActivity.class);
        intent.putExtra("questionJsonData", questionJsonData);
        intent.putExtra("CourseBean", bean);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user_answer_map", user_answer_map);
        intent.putExtras(bundle);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examresult);

        //沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        //解析上级页面传递来的Bean
        mCourseBean = (CourseBean) getIntent().getSerializableExtra("CourseBean");
        questionJsonData = getIntent().getStringExtra("questionJsonData");
        Bundle bundle = getIntent().getExtras();
        user_answer_map = (HashMap<BigDecimal, String>) bundle.get("user_answer_map");
        initView();
    }

    /**
     * Hash数据转换Json
     * @param user_answer_map 用户测试输入数据
     * @return Json
     */
    private String HashMapToJson(HashMap<BigDecimal, String> user_answer_map) {
        List<AnswerQuestionBean> Answerlist = new ArrayList<AnswerQuestionBean>();
        int index = 0;
        for (BigDecimal question_id : user_answer_map.keySet()) {
            AnswerQuestionBean answerbean = new AnswerQuestionBean();
            answerbean.test_index = index++;
            answerbean.question_id = question_id;
            answerbean.user_answer = user_answer_map.get(question_id);
            Answerlist.add(answerbean);
        }
        Gson gson = new Gson();
        JsonObject returnData = new JsonObject();
        returnData.add("user_data", new JsonParser().parse(gson.toJson(Answerlist)));
        return returnData.toString();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        //设置占位View以实现沉浸式状态栏
        View statusBar = findViewById(R.id.statusBarView);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = StudyTreeTools.getStatusBarHeight(ExamResultActivity.this);

        //配置toolBar
        StudyTreeTitleBar exam_result_tool = findViewById(R.id.exam_result_tool);
        exam_result_tool.setTitleRightVisibility(false);
        exam_result_tool.setLeftDrawable(R.drawable.titlebar_back);
        exam_result_tool.setTitle("测试结果");
        exam_result_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(exam_result_tool);

        //分数显示区
        user_score = findViewById(R.id.user_score);
        exam_result_hint = findViewById(R.id.exam_result_hint);

        //初始化RecyclerView
        resource_list = findViewById(R.id.resource_list);
        mResourcelist = new ArrayList<ResourceBean>();
        mResAdapter = new ResourceListAdapter(ExamResultActivity.this,mResourcelist,this);
        resource_list.setLayoutManager(new LinearLayoutManager(ExamResultActivity.this));
        resource_list.setAdapter(mResAdapter);

        //用户做题数据
        String user_answer_jsonStr = HashMapToJson(user_answer_map);

        showProgressDialog();
        InitLogic.getInstance().getExamResultInfo(questionJsonData, mCourseBean, user_answer_jsonStr, new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                doGetTestResult((String)obj);
            }

            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.e(TAG, "获取测试结果失败！responseCode = " + responseCode + "responseMsg = " + responseMsg);
                showToast("获取测试结果失败！请检查网络");
                dismissProgressDialog();
                finish();
            }
        });
    }

    /**
     * 解析Json
     * @param dataStr json
     */
    private void doGetTestResult(String dataStr) {
        if (StringUtils.isNullOrEmpty(dataStr)) {
            return;
        }
        List<ResourceBean> resourcelist = new ArrayList<ResourceBean>();
        try {
            //解析Json
            JsonObject data = new JsonParser().parse(dataStr).getAsJsonObject();
            Gson gson = new Gson();
            JsonArray dataArray = data.getAsJsonArray("data");
            //解析数据
            JsonElement resoursesinfo = dataArray.get(0);
            JsonObject resoursesinfos = new JsonParser().parse(resoursesinfo.toString()).getAsJsonObject();
            JsonArray resourses_json = resoursesinfos.getAsJsonArray("resourses_json");
            //解析图片
            JsonElement picinfo = dataArray.get(1);
            JsonObject picinfos = new JsonParser().parse(picinfo.toString()).getAsJsonObject();
            JsonArray pic = picinfos.getAsJsonArray("pic");

            //解析附属department_id
            JsonElement scoreinfo = dataArray.get(2);
            JsonObject scoreinfojson = new JsonParser().parse(scoreinfo.toString()).getAsJsonObject();
            score = scoreinfojson.get("score").getAsString();

            //封装List
            if (resourses_json != null) {
                for (JsonElement resource : resourses_json) {
                    ResourceBean resourcebean = gson.fromJson(resource, ResourceBean.class);
                    resourcelist.add(resourcebean);
                }
            }
            //匹配封装对应Image
            if (pic != null) {
                for (JsonElement picture : pic) {
                    PictureBean picturebean = gson.fromJson(picture, PictureBean.class);
                    for (ResourceBean resourcebean : resourcelist) {
                        int isequals = resourcebean.resource_picture_id.compareTo(picturebean.picture_id);
                        if (isequals == 0) {
                            resourcebean.resource_image_url = "http://" + Constants.HOST + "/" + picturebean.picture_img;
                        }
                    }
                }
            }
            initResourceList(resourcelist);
        } catch (Exception e) {
            Logger.e(TAG, "解析专业错误！", e);
            initResourceList(null);
        }
    }

    /**
     * 更新UI
     * @param resourcelist 资源列表
     */
    private void initResourceList(final List<ResourceBean> resourcelist) {
        dismissProgressDialog();
        ExamResultActivity.this.mBasehandler.post(new Runnable() {
            @Override
            public void run() {
                user_score.setText(score+"分");
                mResourcelist.clear();
                if (resourcelist != null && !resourcelist.isEmpty()) {
                    mResourcelist.addAll(resourcelist);
                }
                //去除进度
                dismissProgressDialog();
                mResAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(View v, int position) {
        WebViewActivity.start(ExamResultActivity.this,mResourcelist.get(position).resource_detail,"资源推荐",2);
    }

    @Override
    public void onTitleLeftClicked() {
        finish();
    }

    @Override
    public void onTitleRightClicked() {

    }

    @Override
    public void onTitleClicked() {

    }
}
