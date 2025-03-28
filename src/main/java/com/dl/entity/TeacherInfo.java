package com.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    private String teacherName;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 电话
     */
    private String phone;
    
    /**
     * 年龄
     */
    private Integer age;
    
    /**
     * 所属学院
     */
    private String college;
} 