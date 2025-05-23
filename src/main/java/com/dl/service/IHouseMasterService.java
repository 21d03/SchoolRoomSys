package com.dl.service;

import com.dl.entity.pojo.HouseMaster;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dl.entity.vo.UnassignedHouseMasterVO;
import java.util.List;

/**
 * <p>
 * 宿管信息表 服务类
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
public interface IHouseMasterService extends IService<HouseMaster> {
    /**
     * 获取未分配宿舍楼的宿管列表
     * @return 未分配宿舍楼的宿管列表
     */
    List<UnassignedHouseMasterVO> getUnassignedHouseMasters();
}
