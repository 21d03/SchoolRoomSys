package com.dl.service.impl;

import com.dl.entity.pojo.StudentInfo;
import com.dl.mapper.StudentInfoMapper;
import com.dl.service.IStudentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生信息表 服务实现类
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements IStudentInfoService {

}
