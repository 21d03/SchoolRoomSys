package com.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("class_info")
public class ClassInfo {
    
    /**
     * 学院ID
     */
    private String collegeId;
    
    /**
     * 学院名称
     */
    private String collegeName;
    
    /**
     * 专业名称
     */
    private String profession;
    
    /**
     * 班级名称
     */
    private String className;
    
    /**
     * 分管教师ID
     */
    private String teacherId;
    
    /**
     * 分管教师姓名
     */
    private String teacherName;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 