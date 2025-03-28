package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dl.entity.SchoolUser;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author dongliang
 * @date 2025/03/28 17:06:06
 * @description
 **/
@Mapper
@Repository
public interface SchoolUserTeacherMapper extends BaseMapper<SchoolUser> {

    @Select("select * from school_user where user_id = #{teacherId}")
    SchoolUser selectByIdToAdd(String teacherId);

}
