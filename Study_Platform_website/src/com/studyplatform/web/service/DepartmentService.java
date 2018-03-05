package com.studyplatform.web.service;

import java.util.List;

import com.studyplatform.web.bean.DepartmentBean;

/**
 * 业务逻辑封装接口（院系操作）
 * Title: DepartmentService
 * @date 2018年3月2日
 * @author Freedom0013
 */
public interface DepartmentService {
    /**
     * 获取所有院系列表
     * @return 院系列表
     */
    List<DepartmentBean> getallDepartment();
}
