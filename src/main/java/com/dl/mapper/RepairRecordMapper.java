package com.dl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.vo.RepairRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RepairRecordMapper {
    
    /**
     * 分页查询学生报修记录
     * @param page 分页参数
     * @param studentId 学生ID
     * @param repairType 报修类型
     * @param hmStatus 宿管审批状态
     * @param rpStatus 维修人员审批状态
     * @param itemName 报修物品名称
     * @return 分页结果
     */
    IPage<RepairRecordVO> queryRepairRecordPage(
            Page<RepairRecordVO> page,
            @Param("studentId") String studentId,
            @Param("repairType") String repairType,
            @Param("hmStatus") String hmStatus,
            @Param("rpStatus") String rpStatus,
            @Param("itemName") String itemName
    );
} 