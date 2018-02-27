package com.studyplatform.web.dao;

import java.util.List;

import com.studyplatform.web.bean.ProfessionBean;

/**
 * ProfessionalDao（专业数据处理层接口）
 * Title: ProfessionalDao
 * @date 2018年2月27日
 * @author Freedom0013
 */
public interface ProfessionalDao {
    /**
     * 添加专业
     * @param professionbean 专业封装Bean
     * @return 添加成功标识
     */
    int addProfession(ProfessionBean professionbean);
    
    /**
     * 删除专业
     * @param profession_id 专业id
     * @return 删除成功标识
     */
    int deleteProfession(int profession_id);
    
    /**
     * 修改专业信息
     * @param departmentbean 专业Bean
     * @return 修改成功标识
     */
    int updataProfession(ProfessionBean professionbean);
    
    /**
     * 获取所有专业列表
     * @return 专业List
     */
    List<ProfessionBean> getAllProfession();
    
    /**
     * 通过院系id获取所有专业列表
     * @param department_id 院系id
     * @return 院系所辖专业List
     */
    List<ProfessionBean> getAllProfessionByDepartmentId(int department_id);
    
    /**
     * 通过专业id获取单个专业详情信息
     * @param profession_id 专业id
     * @return 专业详情Bean
     */
    ProfessionBean getProfessionById(int profession_id);
}
