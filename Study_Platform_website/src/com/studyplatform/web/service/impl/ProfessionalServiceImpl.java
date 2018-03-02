package com.studyplatform.web.service.impl;

import java.util.List;

import com.studyplatform.web.bean.ProfessionBean;
import com.studyplatform.web.dao.ProfessionalDao;
import com.studyplatform.web.dao.impl.ProfessionalDapImpl;
import com.studyplatform.web.service.ProfessionalService;

public class ProfessionalServiceImpl implements ProfessionalService {
    
    ProfessionalDao dao = new ProfessionalDapImpl();
    @Override
    public List<ProfessionBean> getAllProfessionsByDepId(int department_id) {
        return dao.getAllProfessionByDepartmentId(department_id);
    }
}
