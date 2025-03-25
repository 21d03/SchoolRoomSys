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
 * 宿管信息表
 * </p>
 *
 * @author dongliang
 * @since 2024-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("house_master")
@ApiModel(value="HouseMaster对象", description="宿管信息表")
public class HouseMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "宿管id")
    @TableId
    private String hmId;

    @ApiModelProperty(value = "宿管名称")
    private String hmName;

    @ApiModelProperty(value = "性别")
    private String hmSex;

    @ApiModelProperty(value = "联系方式")
    private String hmPhone;

    @ApiModelProperty(value = "所处宿舍楼")
    private String buildId;

}
