package com.studyplatform.web.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.studyplatform.web.system.SystemCommonValue;

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
	
	/**
	 * 设置Servlet字符集
	 * @param request 请求
	 * @param response 响应
	 */
	public static void setCharSet(HttpServletRequest request, HttpServletResponse response){
	    try {
            request.setCharacterEncoding(SystemCommonValue.WEB_REQUEST_ENCODING);
            response.setContentType(SystemCommonValue.WEB_RESPONSE_CONTENT_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 判断一个字符串是否为空
	 * @param string 要判断的字符串
	 * @return 结果
	 */
	public static boolean isNullOrEmpty(final String str) {
        return str == null || "".equals(str);
    }
	
	/**
	 * 判断两个BigDecimal是否相等
	 * @param decimal1  参数1
	 * @param decimal2 参数2
	 * @param scale decaimal保留的小数位数 
	 * @return 是否相等
	 */
    public static boolean isEquals(BigDecimal decimal1, BigDecimal decimal2, int scale) {
        boolean is = false;
        if (decimal1 != null && decimal2 != null) {
            BigDecimal a = decimal1.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
            DebugUtils.showLog("--a =" + a);
            BigDecimal b = decimal1.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
            DebugUtils.showLog("--a =" + a);
            if (a.equals(b)) {
                is = true;
            }
        }
        return is;
    }
}