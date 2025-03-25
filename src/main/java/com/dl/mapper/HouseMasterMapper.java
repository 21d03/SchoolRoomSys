package com.dl.mapper;

import com.dl.entity.pojo.HouseMaster;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dl.entity.vo.UnassignedHouseMasterVO;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 宿管信息表 Mapper 接口
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Mapper
public interface HouseMasterMapper extends BaseMapper<HouseMaster> {

    /**
     * 查询未分配宿舍楼的宿管列表
     */
    @Select("SELECT hm_id as hmId, hm_name as hmName, hm_sex as hmSex, hm_phone as hmPhone " +
           "FROM house_master WHERE build_id IS NULL OR build_id = ''")
    List<UnassignedHouseMasterVO> selectUnassignedHouseMasters();

    /**
     * 清空指定宿管的宿舍楼ID
     * @param hmId 宿管ID
     * @return 影响的行数
     */
    @Update("UPDATE house_master SET build_id = NULL WHERE hm_id = #{hmId}")
    int clearBuildId(@Param("hmId") String hmId);
}
