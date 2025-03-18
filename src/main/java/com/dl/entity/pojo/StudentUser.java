package com.dl.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生用户表
 * </p>
 *
 * @author dongliang
 * @since 2024-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("student_user")
@ApiModel(value="StudentUser对象", description="学生用户表")
public class StudentUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String userId;

    private String userName;

    private String passWord;

    private String name;

    private String phone;


}
