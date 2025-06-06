package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.TeacherAddDTO;
import com.dl.entity.dto.TeacherManageQueryDTO;
import com.dl.entity.dto.TeacherUpdateDTO;
import com.dl.entity.vo.TeacherManageVO;

/**
 * 教师管理服务接口
 */
public interface TeacherManageService {

    /**
     * 分页查询教师管理信息
     * @param queryDTO 查询条件
     * @return 教师管理信息分页数据
     */
    IPage<TeacherManageVO> queryTeacherManagePage(TeacherManageQueryDTO queryDTO);

    /**
     * 新增教师
     * @param addDTO 教师信息
     * @return 是否成功
     */
    boolean addTeacher(TeacherAddDTO addDTO);

    /**
     * 根据教师ID查询所属学院
     * @param teacherId 教师ID
     * @return 学院名称
     */
    String getCollegeNameByLevel(String teacherId);

    /**
     * 修改教师信息
     * @param updateDTO 修改信息DTO
     * @return 是否修改成功
     */
    boolean updateTeacher(TeacherUpdateDTO updateDTO);

    /**
     * 删除教师
     * @param teacherId 教师ID
     * @return 删除结果：0-成功，1-失败(有关联班级)，2-教师不存在
     */
    int deleteTeacher(String teacherId);
} 