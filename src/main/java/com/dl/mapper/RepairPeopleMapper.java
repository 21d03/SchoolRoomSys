package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.pojo.RepairPeople;
import com.dl.entity.vo.RepairPeopleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 维修人员信息表 Mapper 接口
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Mapper
public interface RepairPeopleMapper extends BaseMapper<RepairPeople> {

    /**
     * 分页查询维修人员信息
     */
    IPage<RepairPeopleVO> queryRepairPeoplePage(Page<RepairPeopleVO> page,
                                                @Param("rpId") String rpId,
                                                @Param("rpName") String rpName,
                                                @Param("rpSex") String rpSex,
                                                @Param("rpPhone") String rpPhone,
                                                @Param("campus") String campus);

    @Select("select * from repair_people where rp_id = #{rpId}")
    RepairPeople selectByIdToAdd(String rpId);
}
