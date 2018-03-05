package com.studyplatform.web.servlet.formbean;

import java.math.BigDecimal;

import com.studyplatform.web.bean.OptionBean;

/**
 * 显示答案页面显示的试题Bean
 * Title: ReplayQuestion
 * @date 2018年3月5日
 * @author Freedom0013
 */
public class ReplayQuestion {
    /** 题目编号 */
    private BigDecimal question_id;
    /** 题目题干 */
    private String question_stem;
    /** 题目选项id */
    private BigDecimal question_option_id;
    /** 题目难度等级：0基础，1中级，2高级 */
    private int question_level;
    /**题目答案  */
    private String question_answer;
    /** 题目解析 */
    private String question_analysis;
    /** 题目类型:0单选，1多选，2判断 */
    private int question_type;
    /** 题目插入时间 */
    private String question_addtime;
    /** 题目所属课程 */
    private int question_course_id;
    /** 题目所属章节 */
    private String question_chapter;
    /** 选项 */
    private OptionBean option = null;
    /** 用户答案*/
    private String user_answer = null;
    /** 测试题号 */
    private int test_index;
    
    /**
     * 空参数构造函数
     */
    public ReplayQuestion(){
        super();
    }

    public ReplayQuestion(BigDecimal question_id, String question_stem, BigDecimal question_option_id,
            int question_level, String question_answer, String question_analysis, int question_type,
            String question_addtime, int question_course_id, String question_chapter, OptionBean option,
            String user_answer, int test_index) {
        super();
        this.question_id = question_id;
        this.question_stem = question_stem;
        this.question_option_id = question_option_id;
        this.question_level = question_level;
        this.question_answer = question_answer;
        this.question_analysis = question_analysis;
        this.question_type = question_type;
        this.question_addtime = question_addtime;
        this.question_course_id = question_course_id;
        this.question_chapter = question_chapter;
        this.option = option;
        this.user_answer = user_answer;
        this.test_index = test_index;
    }

    public BigDecimal getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(BigDecimal question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_stem() {
        return question_stem;
    }

    public void setQuestion_stem(String question_stem) {
        this.question_stem = question_stem;
    }

    public BigDecimal getQuestion_option_id() {
        return question_option_id;
    }

    public void setQuestion_option_id(BigDecimal question_option_id) {
        this.question_option_id = question_option_id;
    }

    public int getQuestion_level() {
        return question_level;
    }

    public void setQuestion_level(int question_level) {
        this.question_level = question_level;
    }

    public String getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }

    public String getQuestion_analysis() {
        return question_analysis;
    }

    public void setQuestion_analysis(String question_analysis) {
        this.question_analysis = question_analysis;
    }

    public int getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(int question_type) {
        this.question_type = question_type;
    }

    public String getQuestion_addtime() {
        return question_addtime;
    }

    public void setQuestion_addtime(String question_addtime) {
        this.question_addtime = question_addtime;
    }

    public int getQuestion_course_id() {
        return question_course_id;
    }

    public void setQuestion_course_id(int question_course_id) {
        this.question_course_id = question_course_id;
    }

    public String getQuestion_chapter() {
        return question_chapter;
    }

    public void setQuestion_chapter(String question_chapter) {
        this.question_chapter = question_chapter;
    }

    public OptionBean getOption() {
        return option;
    }

    public void setOption(OptionBean option) {
        this.option = option;
    }

    public String getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }

    public int getTest_index() {
        return test_index;
    }

    public void setTest_index(int test_index) {
        this.test_index = test_index;
    }

    @Override
    public String toString() {
        return "ReplayQuestion [question_id=" + question_id + ", question_stem=" + question_stem
                + ", question_option_id=" + question_option_id + ", question_level=" + question_level
                + ", question_answer=" + question_answer + ", question_analysis=" + question_analysis
                + ", question_type=" + question_type + ", question_addtime=" + question_addtime
                + ", question_course_id=" + question_course_id + ", question_chapter=" + question_chapter + ", option="
                + option + ", user_answer=" + user_answer + ", test_index=" + test_index + "]";
    }
}
