package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dl.entity.pojo.RoomInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 宿舍信息 Mapper 接口
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Mapper
@Repository
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {
    
    /**
     * 删除学生的房间信息
     * @param stuId 学生ID
     */
    void deleteRoomInfoByStuId(String stuId);
}
