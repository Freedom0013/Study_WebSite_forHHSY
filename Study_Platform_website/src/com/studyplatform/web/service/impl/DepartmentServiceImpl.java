package com.studyplatform.web.service.impl;

import java.util.List;

import com.studyplatform.web.bean.DepartmentBean;
import com.studyplatform.web.dao.DepartmentDao;
import com.studyplatform.web.dao.impl.DepartmentDaoImpl;
import com.studyplatform.web.service.DepartmentService;

/**
 * 业务逻辑封装接口实现（院系操作） 
 * Title: DepartmentServiceImpl
 * @date 2018年3月3日
 * @author Freedom0013
 */
public class DepartmentServiceImpl implements DepartmentService {
    /** 初始化dao */
    DepartmentDao dao = new DepartmentDaoImpl();
    
    @Override
    public List<DepartmentBean> getallDepartment() {
        return dao.getAllDepartment();
    }
}
