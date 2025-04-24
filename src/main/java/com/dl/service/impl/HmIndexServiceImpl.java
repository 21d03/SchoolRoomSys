package com.dl.service.impl;

import com.dl.common.Result;
import com.dl.entity.vo.HmIndexVO;
import com.dl.mapper.HmIndexMapper;
import com.dl.service.HmIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 宿管首页数据统计Service实现类
 */
@Service
@Slf4j
public class HmIndexServiceImpl implements HmIndexService {
    
    @Resource
    private HmIndexMapper hmIndexMapper;
    
    @Override
    public Result getIndexData(String hmId, String buildId) {
        log.info("查询宿管[{}]首页数据，宿舍楼ID：{}", hmId, buildId);
        
        // 获取宿管待处理报修数
        Integer pendingRepairCount = hmIndexMapper.getPendingRepairCount(hmId);
        
        // 获取宿舍楼房间总数
        String totalRoomNumStr = hmIndexMapper.getTotalRoomNum(buildId);
        Integer totalRoomCount = Integer.parseInt(totalRoomNumStr);
        
        // 构建返回数据
        return Result.success(HmIndexVO.builder()
                .pendingRepairCount(pendingRepairCount)
                .totalRoomCount(totalRoomCount)
                .build());
    }
}