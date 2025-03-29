package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.pojo.HouseMaster;
import com.dl.entity.vo.HouseMasterVO;
import com.dl.entity.vo.UnassignedHouseMasterVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    @Update("UPDATE house_master SET build_id = null WHERE hm_id = #{hmId}")
    int clearBuildId(@Param("hmId") String hmId);

    @Select("SELECT hm_id FROM house_master WHERE build_id = #{buildId}")
    String getHmId(String buildId);

    /**
     * 分页查询宿管信息
     */
    IPage<HouseMasterVO> queryHouseMasterPage(Page<HouseMasterVO> page,
                                             @Param("masterId") String masterId,
                                             @Param("masterName") String masterName,
                                             @Param("phone") String phone,
                                             @Param("sex") String sex);

    /**
     * 更新宿舍楼的宿管ID
     * @param hmId 宿管ID
     * @param buildId 宿舍楼ID
     * @return 更新行数
     */
    int updateRoomBuildHmId(@Param("hmId") String hmId, @Param("buildId") String buildId);

    @Insert("insert into master_user(user_id,name,password,phone,type,is_used)  " +
            "values (#{hmId},#{hmName},#{password},#{hmPhone},#{type},#{isUsed})")
    void saveMasterUser(String hmId, String hmName, String password, String hmPhone, String type, String isUsed);
}
