package com.dl.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongliang
 * @date 2024/11/01 14:58:58
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoDTO {

    private String stuId;
    private String stuName;
    private String sex;
    private String college;
    private String profession;
    private String classRoom;
    private String teacherId;
    private String teacherName;
    private String buildId;
    private String roomId;
    private String bedNo;
    private String phone;
}
