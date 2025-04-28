package com.dl.controller.housemaster;

import com.dl.result.Result;
import com.dl.service.RepairApprovalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/hm/repair")
@Api(tags = "宿管报修审批接口")
public class RepairApprovalController {

    private final RepairApprovalService repairApprovalService;

    public RepairApprovalController(RepairApprovalService repairApprovalService) {
        this.repairApprovalService = repairApprovalService;
    }

    @PutMapping("/approve")
    @ApiOperation("处理报修审批")
    public Result handleApproval(@RequestBody RepairApprovalRequest request) {
        log.info("处理报修审批，参数：{}", request);
        return repairApprovalService.handleApproval(
            request.getRepairId(),
            request.getStatus(),
            request.getOpinion(),
            request.getBuildId()
        );
    }
}

// 请求参数封装类
class RepairApprovalRequest {
    private String repairId;
    private Integer status;
    private String opinion;
    private String buildId;

    // Getters and Setters
    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    @Override
    public String toString() {
        return "RepairApprovalRequest{" +
                "repairId='" + repairId + '\'' +
                ", status=" + status +
                ", opinion='" + opinion + '\'' +
                ", buildId='" + buildId + '\'' +
                '}';
    }
} 