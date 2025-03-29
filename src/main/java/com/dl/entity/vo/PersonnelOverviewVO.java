package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("人员总览数据VO")
public class PersonnelOverviewVO {

    @ApiModelProperty(value = "学生总数")
    private Integer studentCount;

    @ApiModelProperty(value = "教师总数")
    private Integer teacherCount;

    @ApiModelProperty(value = "宿管人数")
    private Integer dormManagerCount;

    @ApiModelProperty(value = "维修人员数")
    private Integer maintenanceCount;
}
