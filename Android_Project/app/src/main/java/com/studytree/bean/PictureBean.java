package com.studytree.bean;

import java.math.BigDecimal;

/**
 * 图片资源Bean
 * Title: PictureBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class PictureBean {
	/** 图片编号 */
	public BigDecimal picture_id;
	/** 图片名称 */
	public String picture_name;
	/** 图片描述 */
	public String picture_caption;
	/** 图片存储路径 */
	public String picture_img;
	/** 图片储存时间 */
	public String picture_addtime;
	
	/**
	 * 空参构造函数
	 */
	public PictureBean() {
	}
	
	/**
	 * 全参数构造函数
	 */
	public PictureBean(BigDecimal picture_id, String picture_name, String picture_caption, String picture_img,
			String picture_addtime) {
		super();
		this.picture_id = picture_id;
		this.picture_name = picture_name;
		this.picture_caption = picture_caption;
		this.picture_img = picture_img;
		this.picture_addtime = picture_addtime;
	}
	
	@Override
	public String toString() {
		return "PictureBean [picture_id=" + picture_id + ", picture_name=" + picture_name + ", picture_caption="
				+ picture_caption + ", picture_img=" + picture_img + ", picture_addtime=" + picture_addtime + "]";
	}
}
