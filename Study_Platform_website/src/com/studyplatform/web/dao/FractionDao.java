package com.studyplatform.web.dao;

import java.util.List;

import com.studyplatform.web.bean.FractionBean;

/**
 * FractionDao（成绩数据处理层接口）
 * Title: FractionDao
 * @date 2018年2月27日
 * @author Freedom0013
 */
public interface FractionDao {
    /**
     * 添加成绩
     * @param fractionbean 成绩Bean
     * @return 执行成功标识
     */
    int addFraction(FractionBean fractionbean);
    
    /**
     * 删除成绩
     * @param fraction_id 成绩所属id
     * @return 删除成功标识
     */
    int deleteFraction(int fraction_id);
    
    /**
     * 更新成绩
     * @param fractionbean 成绩Bean
     * @return 更新成功标识
     */
    int updataFraction(FractionBean fractionbean);
    
    /**
     * 根据成绩id获取成绩单次成绩详情
     * @param fraction_id 成绩id
     * @return 成绩封装Bean
     */
    FractionBean getFractionByFractionId(int fraction_id);
    
    /**
     * 根据用户id查找所属用户成绩列表
     * @param fraction_user_id 成绩所属用户id
     * @return 当前用户id成绩列表
     */
    List<FractionBean> getAllFractionByUserId(int fraction_user_id);
}
