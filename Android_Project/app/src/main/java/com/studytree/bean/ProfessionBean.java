package com.studytree.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 学校专业Bean
 * Title: ProfessionBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class ProfessionBean implements Serializable{
	/** 专业编号 */
	public int profession_id;
	/** 专业名称 */
	public String profession_name;
	/** 专业图片编号 */
	public BigDecimal profession_picture_id;
	/** 专业描述 */
	public String profession_caption;
	/** 专业添加时间 */
	public String profession_addtime;
	/** 专业所属系别 */
	public int profession_department_id;
	/** 专业图片地址 */
	public String profession_image_url;

	/**
	 * 空参数构造函数
	 */
	public ProfessionBean() {
		
	}

	/**
	 * 全参数构造函数
	 */
	public ProfessionBean(int profession_id, String profession_name, BigDecimal profession_picture_id,
			String profession_caption, String profession_addtime, int profession_department_id, String profession_image_url) {
		super();
		this.profession_id = profession_id;
		this.profession_name = profession_name;
		this.profession_picture_id = profession_picture_id;
		this.profession_caption = profession_caption;
		this.profession_addtime = profession_addtime;
		this.profession_department_id = profession_department_id;
		this.profession_image_url = profession_image_url;
	}

	@Override
	public String toString() {
		return "ProfessionBean [profession_id=" + profession_id + ", profession_name=" + profession_name
				+ ", profession_picture_id=" + profession_picture_id + ", profession_caption=" + profession_caption
				+ ", profession_addtime=" + profession_addtime + ", profession_department_id="
				+ profession_department_id + ", profession_image_url=" + profession_image_url+ "]";
	}
}
