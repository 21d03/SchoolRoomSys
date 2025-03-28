package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dl.entity.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author dongliang
 * @date 2025/03/28 17:07:07
 * @description
 **/
@Mapper
@Repository
public interface TeacherInfoManageMapper extends BaseMapper<TeacherInfo> {


    @Select("SELECT * FROM teacher_info WHERE teacher_id = #{teaccherId}")
    TeacherInfo selectByIdtoAdd(String teacherId);

}
