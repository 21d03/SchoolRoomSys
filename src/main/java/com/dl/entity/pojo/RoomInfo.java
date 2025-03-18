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
 * 宿舍信息
 * </p>
 *
 * @author dongliang
 * @since 2024-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("room_info")
@ApiModel(value="RoomInfo对象", description="宿舍信息")
public class RoomInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "宿舍号")
    @TableId
    private String roomId;

    @ApiModelProperty(value = "宿舍楼id")
    private String buildId;

    @ApiModelProperty(value = "学生id")
    private String stuId;

    @ApiModelProperty(value = "学生姓名")
    private String stuName;

    @ApiModelProperty(value = "床位号")
    private String bedNo;


}
