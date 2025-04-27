package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "宿舍详细信息")
public class RoomDetailVO {
    
    @ApiModelProperty("宿舍号")
    private String roomId;
    
    @ApiModelProperty("宿舍楼ID")
    private String buildId;
    
    @ApiModelProperty("几人寝")
    private String roomType;
    
    @ApiModelProperty("已入住人数")
    private Integer occupiedCount;
    
    @ApiModelProperty("学生列表")
    private List<StudentRoomDetailVO> students;
}

@Data
class StudentInfoVO {
    @ApiModelProperty(value = "学生ID")
    private String studentId;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "学生性别")
    private String studentSex;

    @ApiModelProperty(value = "联系电话")
    private String studentPhone;

    @ApiModelProperty(value = "床位号")
    private String bedNo;

    @ApiModelProperty(value = "所属学院")
    private String collegeName;

    @ApiModelProperty(value = "辅导员ID")
    private String teacherId;

    @ApiModelProperty(value = "辅导员姓名")
    private String teacherName;
    
    @ApiModelProperty(value = "辅导员电话")
    private String teacherPhone;
} 