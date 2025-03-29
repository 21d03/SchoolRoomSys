package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("审批数量统计VO")
public class ApprovalCountVO {

    @ApiModelProperty(value = "审批总数")
    private Integer totalCount;

    @ApiModelProperty(value = "待处理审批数量")
    private Integer pendingCount;

    @ApiModelProperty(value = "已审批数量")
    private Integer approvedCount;

    @ApiModelProperty(value = "已拒绝数量")
    private Integer rejectedCount;
}