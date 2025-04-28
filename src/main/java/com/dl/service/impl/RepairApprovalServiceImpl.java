package com.dl.service.impl;

import com.dl.entity.RepairApproval;
import com.dl.mapper.RepairApprovalMapper;
import com.dl.mapper.RepairWorkerMapper;
import com.dl.result.Result;
import com.dl.service.RepairApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class RepairApprovalServiceImpl implements RepairApprovalService {

    @Autowired
    private RepairApprovalMapper repairApprovalMapper;

    @Autowired
    private RepairWorkerMapper repairWorkerMapper;

    @Override
    @Transactional
    public Result handleApproval(String repairId, Integer status, String opinion, String buildId) {
        log.info("处理报修审批, repairId={}, status={}, opinion={}, buildId={}", repairId, status, opinion, buildId);

        // 参数校验
        if (status == 2 && (opinion == null || opinion.trim().isEmpty())) {
            return Result.error("拒绝时必须填写审批意见");
        }

        // 查询报修单是否存在
        RepairApproval approval = repairApprovalMapper.selectById(repairId);
        if (approval == null) {
            return Result.error("报修单不存在");
        }

        // 更新报修单状态
        approval.setHmStatus(status);
        approval.setHmOpinion(opinion);
        approval.setHmTime(LocalDateTime.now());
        approval.setUpdateTime(LocalDateTime.now());

        // 如果是通过，需要分配维修工
        if (status == 1) {
            // 根据宿舍楼ID获取校区内的维修工列表
            List<String> workerIds = repairWorkerMapper.selectWorkerIdsByBuildId(buildId);
            if (workerIds == null || workerIds.isEmpty()) {
                return Result.error("该校区暂无可用维修工");
            }

            // 获取各维修工当前未完成的工单数
            List<Integer> workloads = repairWorkerMapper.selectWorkloadsByWorkerIds(workerIds);

            // 找出工作量最少的维修工
            int minIndex = 0;
            for (int i = 1; i < workloads.size(); i++) {
                if (workloads.get(i) < workloads.get(minIndex)) {
                    minIndex = i;
                }
            }

            // 分配维修工
            approval.setWorkerId(workerIds.get(minIndex));
        }

        // 更新数据库
        int rows = repairApprovalMapper.updateById(approval);
        if (rows > 0) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }
} 