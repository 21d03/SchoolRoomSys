package com.dl.controller.housemaster;

import com.dl.entity.dto.UrgeRecordDTO;
import com.dl.result.Result;
import com.dl.service.UrgeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/hm/repair")
@Api(tags = "宿管催办维修接口")
public class RepairUrgeController {

    private final UrgeRecordService urgeRecordService;

    public RepairUrgeController(UrgeRecordService urgeRecordService) {
        this.urgeRecordService = urgeRecordService;
    }

    @PostMapping("/urge")
    @ApiOperation("宿管催办维修人员")
    public Result urgeRepairWorker(@RequestBody RepairUrgeRequest request) {
        log.info("宿管催办维修人员，参数：{}", request);
        
        UrgeRecordDTO urgeRecordDTO = new UrgeRecordDTO();
        urgeRecordDTO.setApprovalId(request.getRepairId());
        urgeRecordDTO.setApprovalType("1"); // 1表示报修审批
        urgeRecordDTO.setUrgeType("3");     // 2表示宿管催办维修人员
        urgeRecordDTO.setUrgeContent(request.getUrgeContent());
        urgeRecordDTO.setFromId(request.getHmId());
        urgeRecordDTO.setToId(request.getWorkerId());

        boolean success = urgeRecordService.createUrgeRecord(urgeRecordDTO);
        return success ? Result.success() : Result.error("催办失败");
    }
}

class RepairUrgeRequest {
    private String repairId;    // 报修单ID
    private String hmId;        // 宿管ID
    private String workerId;    // 维修工ID
    private String urgeContent; // 催办内容

    // Getters and Setters
    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getHmId() {
        return hmId;
    }

    public void setHmId(String hmId) {
        this.hmId = hmId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getUrgeContent() {
        return urgeContent;
    }

    public void setUrgeContent(String urgeContent) {
        this.urgeContent = urgeContent;
    }

    @Override
    public String toString() {
        return "RepairUrgeRequest{" +
                "repairId='" + repairId + '\'' +
                ", hmId='" + hmId + '\'' +
                ", workerId='" + workerId + '\'' +
                ", urgeContent='" + urgeContent + '\'' +
                '}';
    }
} 