package com.studyplatform.web.servlet.formbean;

import java.math.BigDecimal;

/**
 * 带答案的试题Bean
 * Title: AnswerQuestionBean
 * @date 2018年3月5日
 * @author Freedom0013
 */
public class AnswerQuestionBean {
    /** 测试题号 */
    private int test_index;
    /** 题目编号 */
    private BigDecimal question_id;
    /** 用户答案 */
    private String user_answer;

    /**
     * 空参数构造函数
     */
    public AnswerQuestionBean(){
        super();
    }

    public AnswerQuestionBean(int test_index, BigDecimal question_id, String user_answer) {
        super();
        this.test_index = test_index;
        this.question_id = question_id;
        this.user_answer = user_answer;
    }

    public int getTest_index() {
        return test_index;
    }

    public void setTest_index(int test_index) {
        this.test_index = test_index;
    }

    public BigDecimal getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(BigDecimal question_id) {
        this.question_id = question_id;
    }

    public String getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }

    @Override
    public String toString() {
        return "AnswerQuestionBean [test_index=" + test_index + ", question_id=" + question_id + ", user_answer="
                + user_answer + "]";
    }
}
