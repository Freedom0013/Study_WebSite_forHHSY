package com.studytree.bean;

import java.math.BigDecimal;

/**
 * 题目选项Bean
 * Title: OptionBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class OptionBean {
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
	public OptionBean() {
		super();
	}

	/**
	 * 必要参数构造函数
	 */
	public OptionBean(BigDecimal option_id, String option_a, String option_b, String option_c, String option_d) {
		super();
		this.option_id = option_id;
		this.option_a = option_a;
		this.option_b = option_b;
		this.option_c = option_c;
		this.option_d = option_d;
	}
	
	/**
	 * 全参数构造函数
	 */
	public OptionBean(BigDecimal option_id, String option_a, String option_b, String option_c, String option_d,
			String option_e, String option_f, String option_g) {
		super();
		this.option_id = option_id;
		this.option_a = option_a;
		this.option_b = option_b;
		this.option_c = option_c;
		this.option_d = option_d;
		this.option_e = option_e;
		this.option_f = option_f;
		this.option_g = option_g;
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
		return "OptionBean [option_id=" + option_id + ", option_a=" + option_a + ", option_b=" + option_b
				+ ", option_c=" + option_c + ", option_d=" + option_d + ", option_e=" + option_e + ", option_f="
				+ option_f + ", option_g=" + option_g + "]";
	}
}
