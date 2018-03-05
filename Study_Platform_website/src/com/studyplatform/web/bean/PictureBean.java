package com.studyplatform.web.bean;

import java.math.BigDecimal;

/**
 * 图片资源Bean
 * Title: PictureBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class PictureBean {
	/** 图片编号 */
	private BigDecimal picture_id;
	/** 图片名称 */
	private String picture_name;
	/** 图片描述 */
	private String picture_caption;
	/** 图片存储路径 */
	private String picture_img;
	/** 图片储存时间 */
	private String picture_addtime;
	
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
	
	public BigDecimal getPicture_id() {
		return picture_id;
	}
	public void setPicture_id(BigDecimal picture_id) {
		this.picture_id = picture_id;
	}
	public String getPicture_name() {
		return picture_name;
	}
	public void setPicture_name(String picture_name) {
		this.picture_name = picture_name;
	}
	public String getPicture_caption() {
		return picture_caption;
	}
	public void setPicture_caption(String picture_caption) {
		this.picture_caption = picture_caption;
	}
	public String getPicture_img() {
		return picture_img;
	}
	public void setPicture_img(String picture_img) {
		this.picture_img = picture_img;
	}
	public String getPicture_addtime() {
		return picture_addtime;
	}
	public void setPicture_addtime(String picture_addtime) {
		this.picture_addtime = picture_addtime;
	}
	
	@Override
	public String toString() {
		return "PictureBean [picture_id=" + picture_id + ", picture_name=" + picture_name + ", picture_caption="
				+ picture_caption + ", picture_img=" + picture_img + ", picture_addtime=" + picture_addtime + "]";
	}
}
