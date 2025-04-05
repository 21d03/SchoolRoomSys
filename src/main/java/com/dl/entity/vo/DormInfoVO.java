package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "宿舍详细信息")
public class DormInfoVO {

    @ApiModelProperty(value = "宿舍类型（几人间）")
    private String roomType;
    
    @ApiModelProperty(value = "学生列表")
    private List<DormStudentVO> students;
} 