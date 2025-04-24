package com.dl.service.impl;

import com.dl.entity.dto.RoomQueryDTO;
import com.dl.entity.vo.RoomQueryVO;
import com.dl.mapper.RoomQueryMapper;
import com.dl.result.Result;
import com.dl.service.RoomQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 宿舍查询服务实现类
 */
@Service
@Slf4j
public class RoomQueryServiceImpl implements RoomQueryService {
    
    @Autowired
    private RoomQueryMapper roomQueryMapper;
    
    @Override
    public Result queryRooms(RoomQueryDTO dto) {
        log.info("查询宿舍信息: {}", dto);
        
        try {
            // 查询宿舍信息
            List<RoomQueryVO> rooms = roomQueryMapper.queryRooms(dto.getBuildId(), dto.getRoomId());
            
            // 计算空余床位
            List<RoomQueryVO> result = rooms.stream().map(room -> {
                // 如果room_type为空或不是数字，默认为4人寝
                int roomTypeInt = 4;
                try {
                    if (room.getRoomType() != null && !room.getRoomType().isEmpty()) {
                        roomTypeInt = Integer.parseInt(room.getRoomType());
                    }
                } catch (NumberFormatException e) {
                    log.warn("宿舍类型格式错误: {}, 设置默认值4", room.getRoomType());
                }
                
                // 计算空余床位
                int availableBeds = roomTypeInt - (room.getOccupiedCount() == null ? 0 : room.getOccupiedCount());
                if (availableBeds < 0) {
                    availableBeds = 0;
                }
                
                room.setAvailableBeds(availableBeds);
                return room;
            }).collect(Collectors.toList());
            
            return Result.success(result);
        } catch (Exception e) {
            log.error("查询宿舍信息失败", e);
            return Result.error("查询宿舍信息失败");
        }
    }
} 