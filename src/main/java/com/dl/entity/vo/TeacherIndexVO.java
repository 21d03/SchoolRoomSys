package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "教师首页数据")
public class TeacherIndexVO {
    
    @ApiModelProperty("管理的学生总数")
    private Integer studentCount;
    
    @ApiModelProperty("待审批请假数")
    private Integer pendingLeaveCount;
    
    @ApiModelProperty("请假审批总数")
    private Integer totalLeaveCount;
}