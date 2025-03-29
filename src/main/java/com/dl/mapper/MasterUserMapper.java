package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dl.entity.pojo.MasterUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MasterUserMapper extends BaseMapper<MasterUser> {
}
