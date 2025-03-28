package com.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("teacher_info")
public class TeacherInfo {
    
    /**
     * 教师ID
     */
    private String teacherId;
    
    /**
     * 教师姓名
     */
    private String name;

    /**
     * 教师姓名拼音
     */
    private String teacherName;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 所属学院
     */
    private String college;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 