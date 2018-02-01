package com.studyplatform.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

/**
 * web工具
 * Title: WebUtils
 * @date 2018年1月21日
 * @author Freedom0013
 */
public class WebUtils {
	/**
	 * 自动封装form数据到Bean中
	 * Title: fillFormBean
	 * @param clazz bean.class
	 * @param request 请求
	 * @return 封装后的bean对象
	 */
	public static <T> T fillFormBean(Class<T> clazz,HttpServletRequest request){
		T t = null ;
		try {
			t = clazz.newInstance() ;
			BeanUtils.populate(t, request.getParameterMap()) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t ;
	}
	
	/**
	 * long型数值转化为日期格式
	 * @param dateFormat 日期格式
	 * @param millSec 毫秒long
	 * @return 日期
	 */
	public static String transferLongToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}
}