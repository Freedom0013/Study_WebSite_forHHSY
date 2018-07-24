package com.studytree.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户做题数据封装
 * Title: AnswerQuestionBean
 * @date 2018/7/24 11:08
 * @author Freedom0013
 */
public class AnswerQuestionBean implements Serializable {
    /** 测试题号 */
    public int test_index;
    /** 题目编号 */
    public BigDecimal question_id;
    /** 用户答案 */
    public String user_answer;

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

    @Override
    public String toString() {
        return "AnswerQuestionBean [test_index=" + test_index + ", question_id=" + question_id + ", user_answer="
                + user_answer + "]";
    }
}
