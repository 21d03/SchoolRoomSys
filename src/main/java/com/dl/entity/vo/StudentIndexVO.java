package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("学生首页数据统计VO")
public class StudentIndexVO {

    @ApiModelProperty(value = "宿舍楼", notes = "学生所在宿舍楼")
    private String buildName;
    
    @ApiModelProperty(value = "房间号", notes = "学生所在房间号")
    private String roomId;
    
    @ApiModelProperty(value = "床位号", notes = "学生所在床位号")
    private String bedId;
    
    @ApiModelProperty(value = "请假待审批数量", notes = "status=0的请假数量")
    private Integer leavePendingCount;
    
    @ApiModelProperty(value = "请假已处理数量", notes = "status!=0的请假数量")
    private Integer leaveProcessedCount;
    
    @ApiModelProperty(value = "报修待审批数量", notes = "hm_status=0或(hm_status=1且rp_status=0)的报修数量")
    private Integer repairPendingCount;
    
    @ApiModelProperty(value = "报修已完成数量", notes = "hm_status=2或(hm_status=1且rp_status!=0)的报修数量")
    private Integer repairProcessedCount;
} 