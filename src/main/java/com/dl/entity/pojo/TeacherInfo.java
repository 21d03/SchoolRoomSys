package com.dl.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 老师信息表
 * </p>
 *
 * @author dongliang
 * @since 2024-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("teacher_info")
@ApiModel(value="TeacherInfo对象", description="老师信息表")
public class TeacherInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老师id")
    @TableId
    private String teacherId;

    @ApiModelProperty(value = "老师姓名")
    private String teacherName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "所属学院")
    private String college;


}
