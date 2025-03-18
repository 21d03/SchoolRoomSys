package com.dl.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 老师用户信息表
 * </p>
 *
 * @author dongliang
 * @since 2024-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("school_user")
@ApiModel(value="SchoolUser对象", description="老师用户信息表")
public class SchoolUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id，学校老师由00开头，学院老师由xx开头，xx为所属学院的id")
    @TableId
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String passWord;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "老师级别，用于区分权限等级，如学校和学院老师 0学校  其他为学院")
    private String level;

    @ApiModelProperty(value = "性别")
    @TableField(exist = false)
    private String teacherSex;

    @ApiModelProperty(value = "所属学院名称")
    @TableField(exist = false)
    private String collegeName;
}
