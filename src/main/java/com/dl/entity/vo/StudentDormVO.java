package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
@ApiModel("学生宿舍信息VO")
public class StudentDormVO {
    
    @ApiModelProperty(value = "宿舍楼ID", notes = "学生所在宿舍楼ID")
    private String buildId;
    
    @ApiModelProperty(value = "房间号", notes = "学生所在房间号")
    private String roomId;
    
    @ApiModelProperty(value = "床位号", notes = "学生所在床位号")
    private String bedNo;
    
    @ApiModelProperty(value = "宿管姓名", notes = "宿舍楼管理员姓名")
    private String hmName;
    
    @ApiModelProperty(value = "宿管电话", notes = "宿舍楼管理员联系电话")
    private String hmPhone;
    
    @ApiModelProperty(value = "室友信息", notes = "同宿舍的其他学生信息")
    private List<RoommateVO> roommates;
    
    @Data
    @ApiModel("室友信息VO")
    public static class RoommateVO {
        
        @ApiModelProperty(value = "学生ID", notes = "室友的学生ID")
        private String studentId;
        
        @ApiModelProperty(value = "学生姓名", notes = "室友姓名")
        private String stuName;
        
        @ApiModelProperty(value = "床位号", notes = "室友的床位号")
        private String bedNo;
        
        @ApiModelProperty(value = "班级", notes = "室友所在专业和班级，格式：专业+班级")
        private String className;
        
        @ApiModelProperty(value = "手机号", notes = "室友的联系电话")
        private String phone;
    }
} 