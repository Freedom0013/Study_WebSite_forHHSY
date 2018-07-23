package com.studytree.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 题目选项Bean
 * Title: OptionBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class OptionBean implements Serializable{
	/** 选项编号 */
	public BigDecimal option_id;
	/** 选项A */
	public String option_a;
	/** 选项B */
	public String option_b;
	/** 选项C */
	public String option_c;
	/** 选项D */
	public String option_d;
	/** 选项E */
	public String option_e;
	/** 选项F */
	public String option_f;
	/** 选项G */
	public String option_g;
	
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

	@Override
	public String toString() {
		return "OptionBean [option_id=" + option_id + ", option_a=" + option_a + ", option_b=" + option_b
				+ ", option_c=" + option_c + ", option_d=" + option_d + ", option_e=" + option_e + ", option_f="
				+ option_f + ", option_g=" + option_g + "]";
	}
}
