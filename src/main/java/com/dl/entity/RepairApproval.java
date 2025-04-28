package com.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("repair_approval")
public class RepairApproval {
    
    /**
     * 主键ID
     */
    private String id;
    
    /**
     * 申请学生ID
     */
    private String studentId;
    
    /**
     * 报修类型 1-宿舍物品 2-公共物品
     */
    private String repairType;
    
    /**
     * 宿舍ID（宿舍物品报修必填）
     */
    private String roomId;
    
    /**
     * 公共区域（公共物品报修必填）
     */
    private String publicArea;
    
    /**
     * 物品名称
     */
    private String itemName;
    
    /**
     * 问题描述
     */
    private String description;
    
    /**
     * 图片地址，多个用逗号分隔
     */
    private String images;
    
    /**
     * 宿管ID
     */
    private String hmId;
    
    /**
     * 宿管审批状态 0-待审批 1-已通过 2-已驳回
     */
    private Integer hmStatus;
    
    /**
     * 宿管审批意见
     */
    private String hmOpinion;
    
    /**
     * 宿管审批时间
     */
    private LocalDateTime hmTime;
    
    /**
     * 维修人员ID
     */
    private String rpId;
    
    /**
     * 维修状态 0-待维修 1-已完成 2-已驳回
     */
    private String rpStatus;
    
    /**
     * 维修意见
     */
    private String rpOpinion;
    
    /**
     * 维修完成时间
     */
    private LocalDateTime rpTime;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public void setHmStatus(Integer hmStatus) {
        this.hmStatus = hmStatus;
    }

    public void setWorkerId(String workerId) {
        this.rpId = workerId;
    }
} 