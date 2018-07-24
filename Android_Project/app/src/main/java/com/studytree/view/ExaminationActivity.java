package com.studytree.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.bean.CourseBean;
import com.studytree.bean.QuestionBean;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.logic.InitLogic;
import com.studytree.log.Logger;
import com.studytree.utils.StringUtils;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.adapter.QuestionViewPagerAdapter;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.StudyTreeTitleBar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 课程测试Activity
 * Title: ExaminationActivity
 * @date 2018/7/22 22:44
 * @author Freedom0013
 */
public class ExaminationActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener,ViewPager.OnPageChangeListener,View.OnClickListener {
    public static final String TAG = ExaminationActivity.class.getSimpleName();
    /** 试题ViewPager */
    private ViewPager exam_pages;
    /** 做题计时器 */
    private TextView exam_pages_time;
    /** 做题页码 */
    private TextView exam_pages_text;
    /** 上级传入的课程信息 */
    private CourseBean mCourseBean;
    /** 跳转来源 */
    private int from;
    /** 试题总数 */
    private int questionCount;
    /** ToolBar */
    private StudyTreeTitleBar examination_tool;
    /** 提交测试 */
    private Button upload_test;
    /** 用户做题结果记录 */
    private HashMap<BigDecimal,String> user_answer_map;
    private String questionJsonData;

    /**
     * 启动ExaminationActivity
     * @param ctx 来源Context
     * @param bean 来源CourseBean
     */
    public static void start(Context ctx, CourseBean bean) {
        Intent intent = new Intent(ctx, ExaminationActivity.class);
        intent.putExtra("CourseBean", bean);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);

        //沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        //解析上级页面传递来的Bean
        mCourseBean = (CourseBean) getIntent().getSerializableExtra("CourseBean");

        initView();
    }

    private void initView() {
        //设置占位View以实现沉浸式状态栏
        View statusBar = findViewById(R.id.statusBarView);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = StudyTreeTools.getStatusBarHeight(ExaminationActivity.this);

        //配置toolBar
        examination_tool = findViewById(R.id.examination_tool);
        examination_tool.setTitleRightVisibility(false);
        examination_tool.setLeftDrawable(R.drawable.titlebar_back);
        examination_tool.setTitle("课程测试");
        examination_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(examination_tool);

        //初始化ViewPager
        exam_pages = findViewById(R.id.exam_pages);

        //初始化Timer
        exam_pages_time = findViewById(R.id.exam_pages_time);
        exam_pages_time.setVisibility(View.GONE);
        exam_pages_text = findViewById(R.id.exam_pages_text);

        //初始化提交
        upload_test = findViewById(R.id.upload_test);
        upload_test.setVisibility(View.GONE);
        upload_test.setOnClickListener(this);

        //配置ViewPager
        exam_pages.setOffscreenPageLimit(10);
        exam_pages.setOnPageChangeListener(this);

        //初始化用户输入
        user_answer_map = new HashMap<BigDecimal,String>();

        showProgressDialog();
        InitLogic.getInstance().getQuestionsInfo(mCourseBean, new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                doGetQuestions((String)obj);
            }

            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.e(TAG, "获取试题失败！responseCode = " + responseCode + "responseMsg = " + responseMsg);
                showToast("获取试题失败！请检查网络");
                dismissProgressDialog();
                finish();
            }
        });
    }

    /**
     * 解析试题Json
     * @param dataStr Json
     */
    private void doGetQuestions(String dataStr){
        if (StringUtils.isNullOrEmpty(dataStr)) {
            return;
        }
        questionJsonData = dataStr;
        //本地缓存Json数据
        InitManager.getInstance().saveStringPreference("questionBean_list", dataStr);

        List<QuestionBean> questionlist = new ArrayList<QuestionBean>();
        if (dataStr != null) {
            try {
                //解析Json
                JsonObject data = new JsonParser().parse(dataStr).getAsJsonObject();
                Gson gson = new Gson();
                JsonArray dataArray = data.getAsJsonArray("data");
                //解析数据
                JsonElement rootinfo = dataArray.get(0);
                JsonObject rootinfos = new JsonParser().parse(rootinfo.toString()).getAsJsonObject();
                JsonArray root = rootinfos.getAsJsonArray("root");

                //封装List
                if (root != null) {
                    for (JsonElement question : root) {
                        QuestionBean questionbean = gson.fromJson(question, QuestionBean.class);
                        questionlist.add(questionbean);
                    }
                }
                initQuestionList(questionlist);
            } catch (Exception e) {
                Logger.e(TAG, "解析专业错误！", e);
                initQuestionList(null);
            }
        }
    }

    /**
     * 配置题目
     * @param questionlist 试题List
     */
    private void initQuestionList(final List<QuestionBean> questionlist) {
        Logger.d(TAG,questionlist.toString());
        questionCount = questionlist.size();
        dismissProgressDialog();
        if (questionlist != null && questionlist.size() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    QuestionViewPagerAdapter adapter = new QuestionViewPagerAdapter(ExaminationActivity.this,getSupportFragmentManager(),questionlist);
                    exam_pages.setAdapter(adapter);
                }
            });
        }else{
            showToast("没有查询到对应的题目,敬请期待");
            finish();
        }
    }

    @Override
    public void onTitleLeftClicked() {
        showEnterResultDialog();
    }

    @Override
    public void onTitleRightClicked() {

    }

    @Override
    public void onTitleClicked() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(final int position) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                exam_pages_text.setText("当前第 " + (position + 1) + " 题  共 " + questionCount + " 题");
                if((position + 1) == questionCount){
                    upload_test.setVisibility(View.VISIBLE);
                }else{
                    upload_test.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                showEnterResultDialog();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showEnterResultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //填充内容
        builder.setTitle("确定结束测试吗？");
        builder.setMessage("您还有 " + (questionCount - user_answer_map.size()) + " 道题未做完！");
        builder.setPositiveButton("立即结束！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterResult();
            }
        });
        builder.setNegativeButton("我再等等", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //Dialog取消按键监听
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View v) {
        enterResult();
    }

    private void enterResult() {
        ExamResultActivity.start(ExaminationActivity.this,questionJsonData,mCourseBean,user_answer_map);
        finish();
    }

    /**
     * 输入用户做题信息
     * @param question_id 题号
     * @param user_answer 用户答案
     */
    public void addUserAnswer(BigDecimal question_id, String user_answer) {
        user_answer_map.put(question_id,user_answer);
    }
}
