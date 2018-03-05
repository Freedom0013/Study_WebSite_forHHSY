package com.studyplatform.web.bean;

import java.math.BigDecimal;

/**
 * 试题总封装Bean（冗余）
 * Title: ExaminationBean
 * @date 2018年2月5日
 * @author Freedom0013
 */
public class ExaminationBean {
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
    /** 选项编号 */
    private BigDecimal option_id;
    /** 选项A */
    private String option_a;
    /** 选项B */
    private String option_b;
    /** 选项C */
    private String option_c;
    /** 选项D */
    private String option_d;
    /** 选项E */
    private String option_e;
    /** 选项F */
    private String option_f;
    /** 选项G */
    private String option_g;
    
    /**
     * 空参构造函数
     */
    public ExaminationBean(){
        super();
    }
    
    /**
     * 全参数构造函数
     */
    public ExaminationBean(BigDecimal question_id, String question_stem, BigDecimal question_option_id,
            int question_level, String question_answer, String question_analysis, int question_type,
            String question_addtime, int question_course_id, String question_chapter, BigDecimal option_id,
            String option_a, String option_b, String option_c, String option_d, String option_e, String option_f,
            String option_g) {
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
        this.option_id = option_id;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
        this.option_e = option_e;
        this.option_f = option_f;
        this.option_g = option_g;
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

    public BigDecimal getOption_id() {
        return option_id;
    }

    public void setOption_id(BigDecimal option_id) {
        this.option_id = option_id;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public String getOption_e() {
        return option_e;
    }

    public void setOption_e(String option_e) {
        this.option_e = option_e;
    }

    public String getOption_f() {
        return option_f;
    }

    public void setOption_f(String option_f) {
        this.option_f = option_f;
    }

    public String getOption_g() {
        return option_g;
    }

    public void setOption_g(String option_g) {
        this.option_g = option_g;
    }

    @Override
    public String toString() {
        return "ExaminationBean [question_id=" + question_id + ", question_stem=" + question_stem
                + ", question_option_id=" + question_option_id + ", question_level=" + question_level
                + ", question_answer=" + question_answer + ", question_analysis=" + question_analysis
                + ", question_type=" + question_type + ", question_addtime=" + question_addtime
                + ", question_course_id=" + question_course_id + ", question_chapter=" + question_chapter
                + ", option_id=" + option_id + ", option_a=" + option_a + ", option_b=" + option_b + ", option_c="
                + option_c + ", option_d=" + option_d + ", option_e=" + option_e + ", option_f=" + option_f
                + ", option_g=" + option_g + "]";
    }
}
