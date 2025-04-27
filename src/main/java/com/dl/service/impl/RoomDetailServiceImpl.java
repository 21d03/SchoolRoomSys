package com.dl.service.impl;

import com.dl.entity.dto.RoomDetailQueryDTO;
import com.dl.entity.vo.RoomDetailVO;
import com.dl.entity.vo.StudentRoomDetailVO;
import com.dl.mapper.RoomDetailMapper;
import com.dl.result.Result;
import com.dl.service.RoomDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 宿舍详情查询服务实现类
 */
@Service
@Slf4j
public class RoomDetailServiceImpl implements RoomDetailService {
    
    @Autowired
    private RoomDetailMapper roomDetailMapper;
    
    @Override
    public Result getRoomDetail(RoomDetailQueryDTO dto) {
        log.info("查询宿舍详细信息，buildId: {}, roomId: {}", dto.getBuildId(), dto.getRoomId());
        
        try {
            // 查询宿舍基本信息
            RoomDetailVO roomDetail = roomDetailMapper.getRoomDetail(dto.getBuildId(), dto.getRoomId());
            if (roomDetail == null) {
                return Result.error("宿舍不存在");
            }
            
            // 查询宿舍学生信息
            List<StudentRoomDetailVO> students = roomDetailMapper.getStudentsInRoom(dto.getBuildId(), dto.getRoomId());
            roomDetail.setStudents(students);
            
            return Result.success(roomDetail);
        } catch (Exception e) {
            log.error("查询宿舍详细信息失败", e);
            return Result.error("查询宿舍详细信息失败");
        }
    }
} 