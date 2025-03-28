package com.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("school_user")
public class SchoolUser {
    
    /**
     * 用户ID
     */
    private String userId;
    
    /**
     * 用户名（拼音）
     */
    private String userName;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 密码
     */
    private String passWord;
    
    /**
     * 联系电话
     */
    private String phone;
    
    /**
     * 用户类型 0-学生 1-教师 2-宿管 3-维修人员 4-系统管理员
     */
    private String type;

    /**
     * 用户级别（教师ID前两位）
     */
    private String level;

    /**
     * 是否启用 1-启用 0-禁用
     */
    private String isUsed;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 