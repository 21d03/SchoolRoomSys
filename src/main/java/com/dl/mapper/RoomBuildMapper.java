package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.pojo.RoomBuild;
import com.dl.entity.vo.RoomBuildVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 宿舍楼信息 Mapper 接口
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Mapper
public interface RoomBuildMapper extends BaseMapper<RoomBuild> {
    
    @Select("<script>" +
            "SELECT " +
            "  rb.build_id as buildId, " +
            "  rb.build_name as buildName, " +
            "  rb.layer_number as layerNumber, " +
            "  rb.total_room_num as totalRoomNum, " +
            "  hm.hm_name as hmName, " +
            "  CONCAT(ROUND(IFNULL((SELECT COUNT(*) FROM room_info ri WHERE ri.build_id = rb.build_id) / rb.total_room_num * 100, 0), 2), '%') as occupancyRate, " +
            "  rb.is_used as isUsed " +
            "FROM room_build rb " +
            "LEFT JOIN house_master hm ON rb.hm_id = hm.hm_id " +
            "WHERE 1=1 " +
            "<if test='buildName != null and buildName != \"\"'>" +
            "  AND rb.build_name LIKE CONCAT('%', #{buildName}, '%') " +
            "</if>" +
            "<if test='isUsed != null and isUsed != \"\"'>" +
            "  AND rb.is_used = #{isUsed} " +
            "</if>" +
            "ORDER BY rb.build_id" +
            "</script>")
    IPage<RoomBuildVO> queryRoomBuildPage(Page<RoomBuildVO> page, @Param("buildName") String buildName, @Param("isUsed") String isUsed);
}
