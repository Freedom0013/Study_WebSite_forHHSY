package com.studytree.bean;

import java.math.BigDecimal;

/**
 * 用户自行组卷内容Bean
 * Title: User_Test_ContentBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class User_Test_ContentBean {
	/** 试卷内容编号 */
	private BigDecimal contents_id;
	/** 单选题数量 */
	private int contents_single_num;
	/** 单选题分数 */
	private int contents_single_scoue;
	/** 单选题难度 */
	private int contents_single_difficulty;
	/** 多选题数量 */
	private int contents_mul_num;
	/** 多选题分数 */
	private int contents_mul_score;
	/** 多选题难度 */
	private int contents_mul_difficulty;
	/** 判断题数量 */
	private int contents_judge_num;
	/** 判断题分数 */
	private int contents_judge_score;
	/** 判断题难度 */
	private int contents_judge_difficulty;
	/** 总分 */
	private int contents_allscore;
	
	/**
	 * 空参构造函数
	 */	
	public User_Test_ContentBean() {
		super();
	}

	/**
	 * 全参数构造函数
	 */
	public User_Test_ContentBean(BigDecimal contents_id, int contents_single_num, int contents_single_scoue,
			int contents_single_difficulty, int contents_mul_num, int contents_mul_score, int contents_mul_difficulty,
			int contents_judge_num, int contents_judge_score, int contents_judge_difficulty, int contents_allscore) {
		super();
		this.contents_id = contents_id;
		this.contents_single_num = contents_single_num;
		this.contents_single_scoue = contents_single_scoue;
		this.contents_single_difficulty = contents_single_difficulty;
		this.contents_mul_num = contents_mul_num;
		this.contents_mul_score = contents_mul_score;
		this.contents_mul_difficulty = contents_mul_difficulty;
		this.contents_judge_num = contents_judge_num;
		this.contents_judge_score = contents_judge_score;
		this.contents_judge_difficulty = contents_judge_difficulty;
		this.contents_allscore = contents_allscore;
	}

	public BigDecimal getContents_id() {
		return contents_id;
	}

	public void setContents_id(BigDecimal contents_id) {
		this.contents_id = contents_id;
	}

	public int getContents_single_num() {
		return contents_single_num;
	}

	public void setContents_single_num(int contents_single_num) {
		this.contents_single_num = contents_single_num;
	}

	public int getContents_single_scoue() {
		return contents_single_scoue;
	}

	public void setContents_single_scoue(int contents_single_scoue) {
		this.contents_single_scoue = contents_single_scoue;
	}

	public int getContents_single_difficulty() {
		return contents_single_difficulty;
	}

	public void setContents_single_difficulty(int contents_single_difficulty) {
		this.contents_single_difficulty = contents_single_difficulty;
	}

	public int getContents_mul_num() {
		return contents_mul_num;
	}

	public void setContents_mul_num(int contents_mul_num) {
		this.contents_mul_num = contents_mul_num;
	}

	public int getContents_mul_score() {
		return contents_mul_score;
	}

	public void setContents_mul_score(int contents_mul_score) {
		this.contents_mul_score = contents_mul_score;
	}

	public int getContents_mul_difficulty() {
		return contents_mul_difficulty;
	}

	public void setContents_mul_difficulty(int contents_mul_difficulty) {
		this.contents_mul_difficulty = contents_mul_difficulty;
	}

	public int getContents_judge_num() {
		return contents_judge_num;
	}

	public void setContents_judge_num(int contents_judge_num) {
		this.contents_judge_num = contents_judge_num;
	}

	public int getContents_judge_score() {
		return contents_judge_score;
	}

	public void setContents_judge_score(int contents_judge_score) {
		this.contents_judge_score = contents_judge_score;
	}

	public int getContents_judge_difficulty() {
		return contents_judge_difficulty;
	}

	public void setContents_judge_difficulty(int contents_judge_difficulty) {
		this.contents_judge_difficulty = contents_judge_difficulty;
	}

	public int getContents_allscore() {
		return contents_allscore;
	}

	public void setContents_allscore(int contents_allscore) {
		this.contents_allscore = contents_allscore;
	}

	@Override
	public String toString() {
		return "User_Test_ContentBean [contents_id=" + contents_id + ", contents_single_num=" + contents_single_num
				+ ", contents_single_scoue=" + contents_single_scoue + ", contents_single_difficulty="
				+ contents_single_difficulty + ", contents_mul_num=" + contents_mul_num + ", contents_mul_score="
				+ contents_mul_score + ", contents_mul_difficulty=" + contents_mul_difficulty + ", contents_judge_num="
				+ contents_judge_num + ", contents_judge_score=" + contents_judge_score + ", contents_judge_difficulty="
				+ contents_judge_difficulty + ", contents_allscore=" + contents_allscore + "]";
	}
}
