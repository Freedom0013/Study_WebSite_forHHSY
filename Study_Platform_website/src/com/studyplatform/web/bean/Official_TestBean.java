package com.studyplatform.web.bean;

import java.math.BigDecimal;

/**
 * 官方组卷题库Bean
 * Title: Official_TestBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class Official_TestBean {
	/** 试卷编号 */
	private BigDecimal officialtest_id;
	/** 试卷名称 */
	private String officialtest_name;
	/** 试卷添加时间 */
	private String officialtest_addtime;
	/** 试卷内容 */
	private String officialtest_content;
	/** 试卷描述 */
	private String officialtest_caption;
	/** 试卷所属系别 */
	private int officialtest_dep_id;
	/** 试卷所属专业 */
	private int officialtest_pro_id;
	/** 试卷所属课程 */
	private int officialtest_course_id;
	/** 试卷难度默认3  0~5级 */
	private String officialtest_difficulty;

	/**
	 * 空参构造函数
	 */
	public Official_TestBean() {
		
	}

	/**
	 * 全参数构造函数
	 */
	public Official_TestBean(BigDecimal officialtest_id, String officialtest_name, String officialtest_addtime,
			String officialtest_content, String officialtest_caption, int officialtest_dep_id, int officialtest_pro_id,
			int officialtest_course_id, String officialtest_difficulty) {
		super();
		this.officialtest_id = officialtest_id;
		this.officialtest_name = officialtest_name;
		this.officialtest_addtime = officialtest_addtime;
		this.officialtest_content = officialtest_content;
		this.officialtest_caption = officialtest_caption;
		this.officialtest_dep_id = officialtest_dep_id;
		this.officialtest_pro_id = officialtest_pro_id;
		this.officialtest_course_id = officialtest_course_id;
		this.officialtest_difficulty = officialtest_difficulty;
	}

	public BigDecimal getOfficialtest_id() {
		return officialtest_id;
	}

	public void setOfficialtest_id(BigDecimal officialtest_id) {
		this.officialtest_id = officialtest_id;
	}

	public String getOfficialtest_name() {
		return officialtest_name;
	}

	public void setOfficialtest_name(String officialtest_name) {
		this.officialtest_name = officialtest_name;
	}

	public String getOfficialtest_addtime() {
		return officialtest_addtime;
	}

	public void setOfficialtest_addtime(String officialtest_addtime) {
		this.officialtest_addtime = officialtest_addtime;
	}

	public String getOfficialtest_content() {
		return officialtest_content;
	}

	public void setOfficialtest_content(String officialtest_content) {
		this.officialtest_content = officialtest_content;
	}

	public String getOfficialtest_caption() {
		return officialtest_caption;
	}

	public void setOfficialtest_caption(String officialtest_caption) {
		this.officialtest_caption = officialtest_caption;
	}

	public int getOfficialtest_dep_id() {
		return officialtest_dep_id;
	}

	public void setOfficialtest_dep_id(int officialtest_dep_id) {
		this.officialtest_dep_id = officialtest_dep_id;
	}

	public int getOfficialtest_pro_id() {
		return officialtest_pro_id;
	}

	public void setOfficialtest_pro_id(int officialtest_pro_id) {
		this.officialtest_pro_id = officialtest_pro_id;
	}

	public int getOfficialtest_course_id() {
		return officialtest_course_id;
	}

	public void setOfficialtest_course_id(int officialtest_course_id) {
		this.officialtest_course_id = officialtest_course_id;
	}

	public String getOfficialtest_difficulty() {
		return officialtest_difficulty;
	}

	public void setOfficialtest_difficulty(String officialtest_difficulty) {
		this.officialtest_difficulty = officialtest_difficulty;
	}

	@Override
	public String toString() {
		return "Official_TestBean [officialtest_id=" + officialtest_id + ", officialtest_name=" + officialtest_name
				+ ", officialtest_addtime=" + officialtest_addtime + ", officialtest_content=" + officialtest_content
				+ ", officialtest_caption=" + officialtest_caption + ", officialtest_dep_id=" + officialtest_dep_id
				+ ", officialtest_pro_id=" + officialtest_pro_id + ", officialtest_course_id=" + officialtest_course_id
				+ ", officialtest_difficulty=" + officialtest_difficulty + "]";
	}
}
