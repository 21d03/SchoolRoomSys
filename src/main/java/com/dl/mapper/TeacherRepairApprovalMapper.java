package com.dl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.vo.TeacherRepairApprovalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeacherRepairApprovalMapper {

    /**
     * 查询教师管理的学生报修记录
     * @param page 分页对象
     * @param teacherId 教师ID
     * @param stuName 学生姓名
     * @param repairType 报修类型
     * @param roomId 宿舍ID
     * @param itemName 物品名称
     * @return 分页结果
     */
    IPage<TeacherRepairApprovalVO> queryTeacherRepairApprovalPage(
            Page<TeacherRepairApprovalVO> page,
            @Param("teacherId") String teacherId,
            @Param("stuName") String stuName,
            @Param("repairType") String repairType,
            @Param("roomId") String roomId,
            @Param("itemName") String itemName
    );
} 