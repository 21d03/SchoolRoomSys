package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@ApiModel(description = "宿舍楼新增参数")
public class RoomBuildAddDTO {
    
    @ApiModelProperty(value = "宿舍楼id", required = true)
    @NotBlank(message = "宿舍楼id不能为空")
    private String buildId;
    
    @ApiModelProperty(value = "宿舍楼名称", required = true)
    @NotBlank(message = "宿舍楼名称不能为空")
    private String buildName;
    
    @ApiModelProperty(value = "宿管id", required = true)
    @NotBlank(message = "宿管id不能为空")
    private String hmId;
    
    @ApiModelProperty(value = "所处校区 1梁园校区 2睢阳校区", required = true)
    @NotBlank(message = "校区不能为空")
    @Pattern(regexp = "^[12]$", message = "校区只能是1或2")
    private String campus;
    
    @ApiModelProperty(value = "宿舍楼层数", required = true)
    @NotBlank(message = "楼层数不能为空")
    private String layerNumber;
    
    @ApiModelProperty(value = "宿舍间总数", required = true)
    @NotBlank(message = "宿舍间总数不能为空")
    private String totalRoomNum;
    
    @ApiModelProperty(value = "宿舍楼男女寝 1男寝 2女寝 3混合", required = true)
    @NotBlank(message = "宿舍类型不能为空")
    @Pattern(regexp = "^[123]$", message = "宿舍类型只能是1、2或3")
    private String buildType;
    
    @ApiModelProperty(value = "是否处于正常使用 1使用 0暂停使用", required = true)
    @NotBlank(message = "使用状态不能为空")
    @Pattern(regexp = "^[01]$", message = "使用状态只能是0或1")
    private String isUsed;
    
    @ApiModelProperty(value = "备注信息")
    private String remark;
    
    @ApiModelProperty(value = "房间信息", required = true)
    private List<RoomDetailDTO> roomDetails;
    
    @Data
    @ApiModel(description = "房间详情")
    public static class RoomDetailDTO {
        
        @ApiModelProperty(value = "房间号id", required = true)
        @NotBlank(message = "房间号不能为空")
        private String roomId;
        
        @ApiModelProperty(value = "是否混寝 1混寝 2不混", required = true)
        @NotBlank(message = "是否混寝不能为空")
        @Pattern(regexp = "^[12]$", message = "混寝状态只能是1或2")
        private String isMixed;
        
        @ApiModelProperty(value = "入住学生的学院id，如果是混寝，用英文逗号分隔", required = true)
        @NotBlank(message = "学院id不能为空")
        private String collegeIds;
        
        @ApiModelProperty(value = "所属老师id", required = true)
        @NotBlank(message = "所属老师id不能为空")
        private String manageTeacherId;
        
        @ApiModelProperty(value = "几人寝", required = true)
        @NotBlank(message = "几人寝不能为空")
        private String roomType;
    }
} 