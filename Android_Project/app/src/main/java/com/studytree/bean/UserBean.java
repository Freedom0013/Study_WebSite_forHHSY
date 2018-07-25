package com.studytree.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户Bean
 * Title: UserBean
 * @date 2018年1月21日
 * @author Freedom0013
 */
public class UserBean implements Serializable{
	/** 用户id */
	public int user_id;
	/** 用户头像编号 */
	public BigDecimal user_picture_id;
	/** 用户名 */
	public String user_name;
	/** 用户密码 */
	public String user_password;
	/** 用户昵称 */
	public String user_nickname;
	/** 用户注册时间 */
	public String user_register_time;
	/** 用户真实姓名 */
	public String user_realname;
	/** 用户年龄 */
	public int user_age;
	/** 用户性别 */
	public int user_gendar;
	/** 用户手机 */
	public String user_phone;
	/** 用户邮箱 */
	public String user_email;
	/** 用户分数信息id */
	public int user_fraction_id;
	/** 用户学校 */
	public String user_university;
	/** 用户积分 */
	public int user_integral;
	/** 用户归属地 */
	public String user_city;
	/** 用户QQ */
	public BigDecimal user_qq;
	/** 用户最后登录时间 */
	public String user_lastlogin_time;
	/** 用户年级 */
	public String user_grade;
	/** 用户等级 */
	public int user_status;
	/** 用户管理员标识 */
	public int user_admin_flag;
    /** 用户头像url */
	public String user_picture_url;

	/**
	 * 必要参数构造函数
	 */
	public UserBean(int user_id, String user_name, String user_password, String user_nickname,
			String user_register_time, int user_integral, String user_lastlogin_time, int user_status,
			int user_admin_flag) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_nickname = user_nickname;
		this.user_integral = user_integral;
		this.user_status = user_status;
		this.user_admin_flag = user_admin_flag;
		//最后登录时间
		Date dd = new Date();
		SimpleDateFormat sdfdd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.user_lastlogin_time = sdfdd.format(dd);
		//注册时间
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.user_register_time = sdf.format(d);
	}

	/**
	 * 全参数构造函数
	 */
	public UserBean(int user_id, BigDecimal user_picture_id,String user_name,String user_password,
			String user_nickname,String user_register_time,String user_realname,int user_age,
			int user_gendar,String user_phone,String user_email,int user_fraction_id,
			String user_university,int user_integral,String user_city,BigDecimal user_qq,
			String user_lastlogin_time,String user_grade,int user_status,int user_admin_flag) {
	    this.user_id = user_id;
	    this.user_name = user_name;
		this.user_password = user_password;
		this.user_picture_id = user_picture_id;
		this.user_nickname = user_nickname;
		this.user_realname = user_realname;
		this.user_age = user_age;
		this.user_gendar = user_gendar;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_fraction_id = user_fraction_id;
		this.user_university = user_university;
		this.user_integral = user_integral;
		this.user_city = user_city;
		this.user_qq = user_qq;
		this.user_grade = user_grade;
		this.user_status = user_status;
		this.user_admin_flag = user_admin_flag;
		//最后登录时间
		Date dd = new Date();
        SimpleDateFormat sdfdd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.user_lastlogin_time = sdfdd.format(dd);
		//注册时间
		Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.user_register_time = sdf.format(d);
	}

	public UserBean(){

    }

	@Override
	public String toString() {
		return "UserBean [user_id=" + user_id + ", user_picture_id=" + user_picture_id + ", user_name=" + user_name
				+ ", user_password=" + user_password + ", user_nickname=" + user_nickname + ", user_register_time="
				+ user_register_time + ", user_realname=" + user_realname + ", user_age=" + user_age + ", user_gendar="
				+ user_gendar + ", user_phone=" + user_phone + ", user_email=" + user_email + ", user_fraction_id="
				+ user_fraction_id + ", user_university=" + user_university + ", user_integral=" + user_integral
				+ ", user_city=" + user_city + ", user_qq=" + user_qq + ", user_lastlogin_time=" + user_lastlogin_time
				+ ", user_grade=" + user_grade + ", user_status=" + user_status + ", user_admin_flag=" + user_admin_flag
				+ ", user_picture_url=" + user_picture_url + "]";
	}
}