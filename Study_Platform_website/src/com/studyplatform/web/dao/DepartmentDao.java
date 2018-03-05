package com.studyplatform.web.dao;

import java.util.List;

import com.studyplatform.web.bean.DepartmentBean;

/**
 * DepartmentDao（院系数据处理层接口）
 * Title: DepartmentDao
 * @date 2018年2月27日
 * @author Freedom0013
 */
public interface DepartmentDao {
    /**
     * 添加院系
     * @param departmentbean 院系封装Bean
     * @return 添加成功标识
     */
    int addDepartment(DepartmentBean departmentbean);
    
    /**
     * 删除院系
     * @param department_id 院系id
     * @return 删除成功标识
     */
    int deleteDepartment(int department_id);
    
    /**
     * 修改院系信息
     * @param departmentbean 院系Bean
     * @return 修改成功标识
     */
    int updataDepartment(DepartmentBean departmentbean);
    
    /**
     * 获取所有院系列表
     * @return 院系List
     */
    List<DepartmentBean> getAllDepartment();
    
    /**
     * 获取单个院系详情信息
     * @param department_id 院系id
     * @return 院系详情Bean
     */
    DepartmentBean getDepartmentById(int department_id);
}
