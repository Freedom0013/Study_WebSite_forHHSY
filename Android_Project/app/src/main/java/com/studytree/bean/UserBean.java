package com.studytree.bean;

import com.google.gson.JsonObject;

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
	private int user_id;
	/** 用户头像编号 */
	private BigDecimal user_picture_id;
	/** 用户名 */
	private String user_name;
	/** 用户密码 */
	private String user_password;
	/** 用户昵称 */
	private String user_nickname;
	/** 用户注册时间 */
	private String user_register_time;
	/** 用户真实姓名 */
	private String user_realname;
	/** 用户年龄 */
	private int user_age;
	/** 用户性别 */
	private int user_gendar;
	/** 用户手机 */
	private String user_phone;
	/** 用户邮箱 */
	private String user_email;
	/** 用户分数信息id */
	private int user_fraction_id;
	/** 用户学校 */
	private String user_university;
	/** 用户积分 */
	private int user_integral;
	/** 用户归属地 */
	private String user_city;
	/** 用户QQ */
	private BigDecimal user_qq;
	/** 用户最后登录时间 */
	private String user_lastlogin_time;
	/** 用户年级 */
	private String user_grade;
	/** 用户等级 */
	private int user_status;
	/** 用户管理员标识 */
	private int user_admin_flag;
	
	/**
	 * 空参构造函数
	 */
	public UserBean(JsonObject json) {
		
	}
	
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
	
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public BigDecimal getUser_picture_id() {
		return user_picture_id;
	}

	public void setUser_picture_id(BigDecimal user_picture_id) {
		this.user_picture_id = user_picture_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_register_time() {
		return user_register_time;
	}

	public void setUser_register_time(String user_register_time) {
		this.user_register_time = user_register_time;
	}

	public String getUser_realname() {
		return user_realname;
	}

	public void setUser_realname(String user_realname) {
		this.user_realname = user_realname;
	}

	public int getUser_age() {
		return user_age;
	}

	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}

	public int getUser_gendar() {
		return user_gendar;
	}

	public void setUser_gendar(int user_gendar) {
		this.user_gendar = user_gendar;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getUser_fraction_id() {
		return user_fraction_id;
	}

	public void setUser_fraction_id(int user_fraction_id) {
		this.user_fraction_id = user_fraction_id;
	}

	public String getUser_university() {
		return user_university;
	}

	public void setUser_university(String user_university) {
		this.user_university = user_university;
	}

	public int getUser_integral() {
		return user_integral;
	}

	public void setUser_integral(int user_integral) {
		this.user_integral = user_integral;
	}

	public String getUser_city() {
		return user_city;
	}

	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}

	public BigDecimal getUser_qq() {
		return user_qq;
	}

	public void setUser_qq(BigDecimal user_qq) {
		this.user_qq = user_qq;
	}

	public String getUser_lastlogin_time() {
		return user_lastlogin_time;
	}

	public void setUser_lastlogin_time(String user_lastlogin_time) {
		this.user_lastlogin_time = user_lastlogin_time;
	}

	public String getUser_grade() {
		return user_grade;
	}

	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}

	public int getUser_status() {
		return user_status;
	}

	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}

	public int getUser_admin_flag() {
		return user_admin_flag;
	}

	public void setUser_admin_flag(int user_admin_flag) {
		this.user_admin_flag = user_admin_flag;
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
				+ "]";
	}
}