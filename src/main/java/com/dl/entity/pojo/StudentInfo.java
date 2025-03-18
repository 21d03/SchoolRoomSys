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
 * 学生信息表
 * </p>
 *
 * @author dongliang
 * @since 2024-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("student_info")
@ApiModel(value="StudentInfo对象", description="学生信息表")
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生id")
    @TableId
    private String stuId;

    @ApiModelProperty(value = "学生姓名")
    private String stuName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "所属学院")
    private String college;

    @ApiModelProperty(value = "专业")
    private String profession;

    @ApiModelProperty(value = "班级")
    private String classRoom;

    @ApiModelProperty(value = "所属老师id")
    private String teacherId;

    @ApiModelProperty(value = "所属老师名字")
    private String teacherName;

    @ApiModelProperty(value = "所属宿舍楼id")
    private String buildId;

    @ApiModelProperty(value = "所属宿舍号id")
    private String roomId;


}
