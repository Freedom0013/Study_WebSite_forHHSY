package com.studytree.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 题库题目Bean
 * Title: QuestionBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class QuestionBean implements Serializable{
	/** 基础试题类型码 :: 0*/
	public static final int EXAM_QUESTION_EASY_VALUE = 0;
	/** 中级试题类型码  :: 1*/
	public static final int EXAM_QUESTION_NOMAL_VALUE = 1;
	/** 高级试题类型码 :: 2 */
	public static final int EXAM_QUESTION_HARD_VALUE = 2;
	/** 单选试题状态码  :: 0*/
	public static final int EXAM_QUESTION_TYPE_SINGLE = 0;
	/** 多选试题状态码  :: 1*/
	public static final int EXAM_QUESTION_TYPE_MULTI = 1;
	/** 判断试题状态码  :: 2*/
	public static final int EXAM_QUESTION_TYPE_JUDGE = 2;
	/** 题目编号 */
	public BigDecimal question_id;
	/** 题目题干 */
	public String question_stem;
	/** 题目选项id */
	public BigDecimal question_option_id;
	/** 题目难度等级：0基础，1中级，2高级 */
	public int question_level;
	/**题目答案  */
	public String question_answer;
	/** 题目解析 */
	public String question_analysis;
	/** 题目类型:0单选，1多选，2判断 */
	public int question_type;
	/** 题目插入时间 */
	public String question_addtime;
	/** 题目所属课程 */
	public int question_course_id;
	/** 题目所属章节 */
	public String question_chapter;

	public OptionBean option = null;

	/**
	 * 空参数构造函数
	 */
	public QuestionBean(){
	    super();
	}

	/**
	 * 全参数构造函数
	 */
	public QuestionBean(BigDecimal question_id, String question_stem, BigDecimal question_option_id, int question_level,
			String question_answer, String question_analysis, int question_type, String question_addtime,
			int question_course_id, String question_chapter,OptionBean option) {
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
	}

    @Override
    public String toString() {
        return "QuestionBean [question_id=" + question_id + ", question_stem=" + question_stem + ", question_option_id="
                + question_option_id + ", question_level=" + question_level + ", question_answer=" + question_answer
                + ", question_analysis=" + question_analysis + ", question_type=" + question_type
                + ", question_addtime=" + question_addtime + ", question_course_id=" + question_course_id
                + ", question_chapter=" + question_chapter + ", option=" + option.toString() + "]";
    }
}
