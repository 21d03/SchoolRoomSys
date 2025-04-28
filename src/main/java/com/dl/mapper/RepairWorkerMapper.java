package com.dl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface RepairWorkerMapper {
    
    @Select("SELECT rp_id FROM repair_people WHERE campus = (select campus from room_build where build_id = #{buildId})")
    List<String> selectWorkerIdsByBuildId(String buildId);
    
    @Select("SELECT COUNT(*) FROM repair_approval WHERE rp_id = #{workerId} AND rp_status IN (0, 1)")
    Integer selectWorkloadByWorkerId(String workerId);
    
    default List<Integer> selectWorkloadsByWorkerIds(List<String> workerIds) {
        return workerIds.stream()
                .map(this::selectWorkloadByWorkerId)
                .collect(Collectors.toList());
    }
} 