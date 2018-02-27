package com.studyplatform.web.bean;

import java.math.BigDecimal;

public class FractionBean {
	/** 分数信息编号 */
	private BigDecimal fraction_id;
	/** 分数 */
	private int fraction_content;
	/** 分数产生时间 */
	private String fraction_addtime;
	/** 分数所属课程 */
	private int fraction_course_id;
	/** 分数所属用户 */
	private int fraction_user_id;
	
	/**
	 * 空参数构造函数
	 */
	public FractionBean() {
		super();
	}

	/**
	 * 全参数构造函数
	 */
	public FractionBean(int fraction_content, String fraction_addtime, int fraction_course_id,
			int fraction_user_id) {
		super();
		this.fraction_content = fraction_content;
		this.fraction_addtime = fraction_addtime;
		this.fraction_course_id = fraction_course_id;
		this.fraction_user_id = fraction_user_id;
	}

	public BigDecimal getFraction_id() {
		return fraction_id;
	}

	public void setFraction_id(BigDecimal fraction_id) {
		this.fraction_id = fraction_id;
	}

	public int getFraction_content() {
		return fraction_content;
	}

	public void setFraction_content(int fraction_content) {
		this.fraction_content = fraction_content;
	}

	public String getFraction_addtime() {
		return fraction_addtime;
	}

	public void setFraction_addtime(String fraction_addtime) {
		this.fraction_addtime = fraction_addtime;
	}

	public int getFraction_course_id() {
		return fraction_course_id;
	}

	public void setFraction_course_id(int fraction_course_id) {
		this.fraction_course_id = fraction_course_id;
	}

	public int getFraction_user_id() {
		return fraction_user_id;
	}

	public void setFraction_user_id(int fraction_user_id) {
		this.fraction_user_id = fraction_user_id;
	}

	@Override
	public String toString() {
		return "FractionBean [fraction_id=" + fraction_id + ", fraction_content=" + fraction_content
				+ ", fraction_addtime=" + fraction_addtime + ", fraction_course_id=" + fraction_course_id
				+ ", fraction_user_id=" + fraction_user_id + "]";
	}
}
