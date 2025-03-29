package com.dl.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("master_user")
public class MasterUser {
    
    @TableId
    private String userId;
    
    private String name;
    
    private String password;
    
    private String phone;
    
    private String type;  // 1-宿管，2-维修人员
    
    private String isUsed;  // 1-启用，0-禁用
}
