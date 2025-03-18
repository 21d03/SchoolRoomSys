package com.dl.service.impl;

import com.dl.entity.pojo.StudentUser;
import com.dl.mapper.StudentUserMapper;
import com.dl.service.IStudentUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生用户表 服务实现类
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Service
public class StudentUserServiceImpl extends ServiceImpl<StudentUserMapper, StudentUser> implements IStudentUserService {

}
