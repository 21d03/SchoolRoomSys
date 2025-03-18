package com.dl.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 宿舍楼信息
 * </p>
 *
 * @author dongliang
 * @since 2024-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("room_build")
@ApiModel(value="RoomBuild对象", description="宿舍楼信息")
public class RoomBuild implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "宿舍楼id")
    @TableId
    private String buildId;

    @ApiModelProperty(value = "宿舍楼名称")
    private String buildName;

    @ApiModelProperty(value = "house_master 宿管id")
    private String hmId;

    @ApiModelProperty(value = "所处校区 1梁园校区 2睢阳校区")
    private String campus;

    @ApiModelProperty(value = "宿舍楼详细信息表")
    private String detailsTable;

    @ApiModelProperty(value = "宿舍楼层数")
    private String layerNumber;

    @ApiModelProperty(value = "宿舍间总数")
    private String totalRoomNum;

    @ApiModelProperty(value = "可用宿舍间数目")
    private String usableRoomNum;

    @ApiModelProperty(value = "宿舍楼男女寝 1男寝 2女寝 3混合")
    private String buildType;

    @ApiModelProperty(value = "是否处于正常使用 1使用 0暂停使用")
    private String isUsed;

    @ApiModelProperty(value = "备注信息")
    private String remark;


}
