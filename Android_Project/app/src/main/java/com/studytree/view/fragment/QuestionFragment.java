package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studytree.R;
import com.studytree.bean.QuestionBean;
import com.studytree.utils.StringUtils;
import com.studytree.view.ExaminationActivity;
import com.studytree.view.base.BaseFragment;

/**
 * 课程测试Fragment
 * Title: QuestionFragment
 * @date 2018/7/23 18:16
 * @author Freedom0013
 */
public class QuestionFragment extends BaseFragment {
    private static final String TAG = QuestionFragment.class.getSimpleName();
    /** 当前位置 */
    private int position;
    /** ExaminationActivity */
    private ExaminationActivity mActivity;
    /** 试题信息Bean */
    private QuestionBean mQuestion;
    /** 选项Layout */
    private LinearLayout container;
    /** LayoutInflater对象 */
    private LayoutInflater inflater;
    /** 解释Layout */
    private LinearLayout exam_answer_container;
    /** 用户答案TextView */
    private TextView user_answer;
    /** 试题选项容器 */
    private LinearLayout question_content_contaier;
    /** 选项A */
    private View optionA;
    /** 选项B */
    private View optionB;
    /** 选项C */
    private View optionC;
    /** 选项D */
    private View optionD;
    /** 选项不知道 */
    private View optionUnknow;
    /** 选项E */
    private View optionE;
    /** 选项F */
    private View optionF;
    /** 选项G */
    private View optionG;

    @SuppressLint({"NewApi", "ValidFragment"})
    public QuestionFragment(){

    }

    public QuestionFragment(Activity activity,QuestionBean question,int position){
        mActivity = (ExaminationActivity)activity;
        mQuestion = question;
        this.position = position;
    }

    @Override
    public View initView() {
        View mRootView = View.inflate(mActivity, R.layout.fragment_question, null);
        TextView exam_question_type = mRootView.findViewById(R.id.exam_question_type);
        TextView exam_question_isdone = mRootView.findViewById(R.id.exam_question_isdone);
        TextView exam_question_title = mRootView.findViewById(R.id.exam_question_title);
        question_content_contaier = mRootView.findViewById(R.id.question_content_contaier);
        exam_answer_container = mRootView.findViewById(R.id.exam_answer_container);
        TextView question_analysis = mRootView.findViewById(R.id.question_analysis);
        TextView question_grade = mRootView.findViewById(R.id.question_grade);
        TextView right_answer = mRootView.findViewById(R.id.right_answer);
        user_answer = mRootView.findViewById(R.id.user_answer);

        if (mQuestion.question_type == QuestionBean.EXAM_QUESTION_TYPE_SINGLE) {
            exam_question_type.setText("【单选题】");
        } else if (mQuestion.question_type == QuestionBean.EXAM_QUESTION_TYPE_MULTI) {
            exam_question_type.setText("【多选题】");
        }

        exam_question_title.setText(mQuestion.question_stem);
        question_content_contaier.removeAllViews();

        View contentView = getBodyView(mQuestion.question_type == QuestionBean.EXAM_QUESTION_TYPE_MULTI);
        question_content_contaier.addView(contentView);

        exam_answer_container.setVisibility(View.GONE);
        right_answer.setText("正确答案：" + mQuestion.question_answer);
        question_analysis.setText(mQuestion.question_analysis);
        if (mQuestion.question_level == QuestionBean.EXAM_QUESTION_EASY_VALUE) {
            question_grade.setText("等级：基础题");
        } else if (mQuestion.question_level == QuestionBean.EXAM_QUESTION_NOMAL_VALUE) {
            question_grade.setText("等级：中等题");
        } else if (mQuestion.question_level == QuestionBean.EXAM_QUESTION_HARD_VALUE) {
            question_grade.setText("等级：高级题");
        }
        return mRootView;
    }

    /**
     * 配置选项方法
     * @param isMulti 是否多选
     * @return 选项View
     */
    private View getBodyView(boolean isMulti) {
        View v = null;
        if (isMulti) {
            //多选
            createMultiSimpleView();
            v = container;
        } else {
            //单选
            createSingleSimpleView();
            v = container;
        }
        return v;
    }

    /**
     * 创建多选View
     * @return 多选View
     */
    private View createMultiSimpleView() {
        container = new LinearLayout(getActivity());
        container.setOrientation(LinearLayout.VERTICAL);
        if (mQuestion.option != null) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            optionA = initOptionView(mQuestion.option.option_a, "A");
            optionB = initOptionView(mQuestion.option.option_b, "B");
            optionC = initOptionView(mQuestion.option.option_c, "C");
            optionD = initOptionView(mQuestion.option.option_d, "D");
            container.addView(optionA, params);
            container.addView(optionB, params);
            container.addView(optionC, params);
            container.addView(optionD, params);
            View optionUnknow = initOptionView("不知道", "E");
            container.addView(optionUnknow, params);
            if (!StringUtils.isNullOrEmpty(mQuestion.option.option_e)) {
                optionE = initOptionView(mQuestion.option.option_e, "F");
                container.addView(optionE, params);
            }
            if (!StringUtils.isNullOrEmpty(mQuestion.option.option_f)) {
                optionF = initOptionView(mQuestion.option.option_f, "G");
                container.addView(optionF, params);
            }
            if (!StringUtils.isNullOrEmpty(mQuestion.option.option_g)) {
                optionG = initOptionView(mQuestion.option.option_g, "H");
                container.addView(optionG, params);
            }
        }
        return container;
    }

    /**
     * 创建单选View
     * @return 单选View
     */
    private View createSingleSimpleView() {
        container = new LinearLayout(getActivity());
        container.setOrientation(LinearLayout.VERTICAL);
        if (mQuestion.option != null) {
            optionA = initOptionView(mQuestion.option.option_a, "A");
            optionB = initOptionView(mQuestion.option.option_b, "B");
            optionC = initOptionView(mQuestion.option.option_c, "C");
            optionD = initOptionView(mQuestion.option.option_d, "D");
            optionUnknow = initOptionView("不知道", "E");
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            container.addView(optionA, params);
            container.addView(optionB, params);
            container.addView(optionC, params);
            container.addView(optionD, params);
            container.addView(optionUnknow, params);
        }
        return container;
    }

    /**
     * 配置单个选项View
     * @param option_text 选项文字
     * @param option_title 选项
     * @return 单个选项View
     */
    private View initOptionView(final String option_text, final String option_title) {
        View view = getLayoutInflater().inflate(R.layout.layout_question_single_option, null);
        LinearLayout optionsContainer = view.findViewById(R.id.question_option_container);
        final TextView lable = view.findViewById(R.id.question_option_selector);
        final TextView content = view.findViewById(R.id.question_option_content);
//        StringUtils.setTextOrHtml(option_text, content);
        content.setText(option_text);
        lable.setText(option_title);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mQuestion.question_answer.equals(option_title)) {
                    //答对 自动跳转下一题
                    lable.setText("");
                    user_answer.setText("您的答案：" + option_title);
                    lable.setBackgroundResource(R.drawable.icon_correct);
                    content.setTextColor(getActivity().getResources().getColor(R.color.options_text_correct));
                    exam_answer_container.setVisibility(View.VISIBLE);
                    savaUserAnswer(option_title);
                    setAllUnClick();
                } else {
                    //答错 显示错题
                    lable.setText("");
                    user_answer.setText("您的答案：" + option_title);
                    lable.setBackgroundResource(R.drawable.icon_incorrect);
                    content.setTextColor(getActivity().getResources().getColor(R.color.options_text_incorrect));
                    exam_answer_container.setVisibility(View.VISIBLE);
                    savaUserAnswer(option_title);
                    setAllUnClick();
                }
            }
        });
        return view;
    }

    /**
     * 设置用户答案
     * @param answer
     */
    private void savaUserAnswer(String answer) {
        mActivity.addUserAnswer(mQuestion.question_id, answer);
    }

    /**
     * 取消选项点击监听
     */
    private void setAllUnClick() {
        optionA.setClickable(false);
        optionA.setOnClickListener(null);
        optionB.setClickable(false);
        optionB.setOnClickListener(null);
        optionC.setClickable(false);
        optionC.setOnClickListener(null);
        optionD.setClickable(false);
        optionD.setOnClickListener(null);
        optionUnknow.setClickable(false);
        optionUnknow.setOnClickListener(null);
    }
}
