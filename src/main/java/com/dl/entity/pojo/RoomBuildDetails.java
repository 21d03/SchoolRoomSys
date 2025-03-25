package com.dl.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 宿舍信息详情表
 * </p>
 *
 * @author dongliang
 * @since 2024-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("room_build_details")
@ApiModel(value="RoomBuildDetails对象", description="宿舍信息详情表")
public class RoomBuildDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "宿舍楼id")
    private String buildId;

    @ApiModelProperty(value = "宿舍楼姓名")
    private String buildName;

    @ApiModelProperty(value = "宿舍楼层数")
    private String layerNumber;

    @ApiModelProperty(value = "房间号id")
    private String roomId;

    @ApiModelProperty(value = "是否混寝")
    private String isMixed;

    @ApiModelProperty(value = "入住学生的学院id，如果是混寝，用英文逗号分隔")
    private String collegeIds;

    @ApiModelProperty(value = "所属老师id")
    private String manageTeacherId;
    
    @ApiModelProperty(value = "几人寝")
    private String roomType;

    @ApiModelProperty(value = "使用状态 1正常使用 0暂停使用")
    private String status;
}
