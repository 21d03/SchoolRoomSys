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
 * 学院信息表
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("college_info")
@ApiModel(value="CollegeInfo对象", description="学院信息表")
public class CollegeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学院id")
    @TableId
    private String collegeId;

    @ApiModelProperty(value = "学院名字")
    private String collegeName;


}
