package com.dl.service.impl;

import com.dl.entity.vo.PersonnelOverviewVO;
import com.dl.mapper.PersonnelStatMapper;
import com.dl.service.PersonnelStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonnelStatServiceImpl implements PersonnelStatService {

    @Autowired
    private PersonnelStatMapper personnelStatMapper;

    @Override
    public PersonnelOverviewVO getPersonnelOverview() {
        PersonnelOverviewVO overviewVO = new PersonnelOverviewVO();
        
        // 获取学生总数
        overviewVO.setStudentCount(personnelStatMapper.getStudentCount());
        
        // 获取教师总数
        overviewVO.setTeacherCount(personnelStatMapper.getTeacherCount());
        
        // 获取宿管人数
        overviewVO.setDormManagerCount(personnelStatMapper.getDormManagerCount());
        
        // 获取维修人员数
        overviewVO.setMaintenanceCount(personnelStatMapper.getMaintenanceCount());
        
        return overviewVO;
    }
}
