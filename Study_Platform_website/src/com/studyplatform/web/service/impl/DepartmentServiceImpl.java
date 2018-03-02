package com.studyplatform.web.service.impl;

import java.util.List;

import com.studyplatform.web.bean.DepartmentBean;
import com.studyplatform.web.dao.DepartmentDao;
import com.studyplatform.web.dao.impl.DepartmentDaoImpl;
import com.studyplatform.web.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {
    /** 初始化dao */
    DepartmentDao dao = new DepartmentDaoImpl();
    
    @Override
    public List<DepartmentBean> getallDepartment() {
        return dao.getAllDepartment();
    }
}
