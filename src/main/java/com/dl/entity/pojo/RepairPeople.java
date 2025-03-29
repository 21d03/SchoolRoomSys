package com.dl.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 维修人员信息表
 * </p>
 *
 * @author dongliang
 * @since 2024-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("repair_people")
@ApiModel(value="RepairPeople对象", description="维修人员信息表")
public class RepairPeople implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "维修人员id")
    private String rpId;

    @ApiModelProperty(value = "维修人员姓名")
    private String rpName;

    @ApiModelProperty(value = "性别")
    private String rpSex;

    @ApiModelProperty(value = "联系方式")
    private String rpPhone;

    @ApiModelProperty(value = "所在校区")
    private String campus;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
