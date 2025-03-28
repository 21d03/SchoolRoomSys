package com.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("room_info")
public class RoomInfo {
    
    /**
     * 宿舍楼ID
     */
    private String buildId;
    
    /**
     * 房间号
     */
    private String roomId;
    
    /**
     * 学生ID
     */
    private String stuId;
    
    /**
     * 学生姓名
     */
    private String stuName;
    
    /**
     * 是否使用 0-未使用 1-已使用
     */
    private String isUsed;
} 