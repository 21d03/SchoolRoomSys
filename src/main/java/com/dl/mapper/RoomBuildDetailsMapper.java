package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dl.entity.pojo.RoomBuildDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 宿舍信息详情表 Mapper 接口
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Mapper
public interface RoomBuildDetailsMapper extends BaseMapper<RoomBuildDetails> {

    /**
     * 批量插入房间详情
     * @param detailsList 房间详情列表
     * @return 是否成功
     */
    default boolean insertBatch(@Param("list") List<RoomBuildDetails> detailsList) {
        try {
            for (RoomBuildDetails details : detailsList) {
                this.insert(details);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
