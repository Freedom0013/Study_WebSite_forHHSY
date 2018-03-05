package com.studyplatform.web.bean;

import java.math.BigDecimal;

/**
 * 学校专业Bean
 * Title: ProfessionBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class ProfessionBean {
	/** 专业编号 */
	private int profession_id;
	/** 专业名称 */
	private String profession_name;
	/** 专业图片编号 */
	private BigDecimal profession_picture_id;
	/** 专业描述 */
	private String profession_caption;
	/** 专业添加时间 */
	private String profession_addtime;
	/** 专业所属系别 */
	private int profession_department_id;
	
	/**
	 * 空参数构造函数
	 */
	public ProfessionBean() {
		
	}

	/**
	 * 全参数构造函数
	 */
	public ProfessionBean(int profession_id, String profession_name, BigDecimal profession_picture_id,
			String profession_caption, String profession_addtime, int profession_department_id) {
		super();
		this.profession_id = profession_id;
		this.profession_name = profession_name;
		this.profession_picture_id = profession_picture_id;
		this.profession_caption = profession_caption;
		this.profession_addtime = profession_addtime;
		this.profession_department_id = profession_department_id;
	}
	
	public int getProfession_id() {
		return profession_id;
	}
	public void setProfession_id(int profession_id) {
		this.profession_id = profession_id;
	}
	public String getProfession_name() {
		return profession_name;
	}
	public void setProfession_name(String profession_name) {
		this.profession_name = profession_name;
	}
	public BigDecimal getProfession_picture_id() {
		return profession_picture_id;
	}
	public void setProfession_picture_id(BigDecimal profession_picture_id) {
		this.profession_picture_id = profession_picture_id;
	}
	public String getProfession_caption() {
		return profession_caption;
	}
	public void setProfession_caption(String profession_caption) {
		this.profession_caption = profession_caption;
	}
	public String getProfession_addtime() {
		return profession_addtime;
	}
	public void setProfession_addtime(String profession_addtime) {
		this.profession_addtime = profession_addtime;
	}
	public int getProfession_department_id() {
		return profession_department_id;
	}
	public void setProfession_department_id(int profession_department_id) {
		this.profession_department_id = profession_department_id;
	}
	
	@Override
	public String toString() {
		return "ProfessionBean [profession_id=" + profession_id + ", profession_name=" + profession_name
				+ ", profession_picture_id=" + profession_picture_id + ", profession_caption=" + profession_caption
				+ ", profession_addtime=" + profession_addtime + ", profession_department_id="
				+ profession_department_id + "]";
	}
}
