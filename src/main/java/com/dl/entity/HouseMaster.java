package com.dl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("house_master")
public class HouseMaster {

    @TableId
    private String masterId;
    
    private String masterName;
    
    private String sex;
    
    private String phone;
    
    private String isUsed;
    
    private Date createTime;
    
    private Date updateTime;
}
