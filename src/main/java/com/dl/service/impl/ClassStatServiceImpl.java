package com.dl.service.impl;

import com.dl.entity.vo.ClassStudentDistributionVO;
import com.dl.mapper.ClassStatMapper;
import com.dl.service.ClassStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.List;

@Service
public class ClassStatServiceImpl implements ClassStatService {

    @Autowired
    private ClassStatMapper classStatMapper;

    @Override
    public List<ClassStudentDistributionVO> getClassStudentDistribution(String collegeId) {
        // 获取班级基本信息
        List<ClassStudentDistributionVO> distributionList = classStatMapper.getClassStudentDistribution(collegeId);
        
        // 填充每个班级的学生人数
        for (ClassStudentDistributionVO classInfo : distributionList) {
            String profession = (String) getFieldValue(classInfo, "profession");
            String classNameOnly = (String) getFieldValue(classInfo, "classNameOnly");
            
            // 获取学生人数
            Integer studentCount = classStatMapper.getStudentCountByClass(profession, classNameOnly);
            classInfo.setStudentCount(studentCount);
            
            // 移除临时字段
            setFieldValue(classInfo, "profession", null);
            setFieldValue(classInfo, "classNameOnly", null);
        }
        
        return distributionList;
    }
    
    // 辅助方法：获取对象的字段值
    private Object getFieldValue(Object obj, String fieldName) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            return null;
        }
    }
    
    // 辅助方法：设置对象的字段值
    private void setFieldValue(Object obj, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            // 忽略异常
        }
    }
}
