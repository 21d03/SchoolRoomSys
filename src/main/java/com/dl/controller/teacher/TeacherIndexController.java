package com.dl.controller.teacher;

import com.dl.entity.dto.LeaveApprovalQueryDTO;
import com.dl.entity.vo.LeaveApprovalVO;
import com.dl.entity.vo.TeacherIndexVO;
import com.dl.result.PageResult;
import com.dl.result.Result;
import com.dl.service.TeacherIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 教师首页数据统计接口
 */
@RestController
@RequestMapping("/api/teacher")
@Api(tags = "教师首页接口")
@Slf4j
public class TeacherIndexController {
    
    @Resource
    private TeacherIndexService teacherIndexService;
    
    /**
     * 获取教师首页统计数据
     * @return 教师首页统计数据
     */
    @GetMapping("/index")
    @ApiOperation("获取教师首页统计数据")
    public Result<TeacherIndexVO> getIndexData(String teacherId) {
        log.info("获取教师首页统计数据");
        TeacherIndexVO data = teacherIndexService.getIndexData(teacherId);
        return Result.success(data);
    }
    
    /**
     * 获取教师最近请假审批记录
     * @param teacherId 教师ID
     * @param queryDTO 查询参数
     * @return 请假审批记录分页数据
     */
    @PostMapping("/recent-leave-approvals")
    @ApiOperation("获取教师最近请假审批记录")
    public Result<PageResult<LeaveApprovalVO>> getRecentLeaveApprovals(
            @RequestParam String teacherId,
            @RequestBody LeaveApprovalQueryDTO queryDTO) {
        log.info("获取教师最近请假审批记录，教师ID：{}，查询参数：{}", teacherId, queryDTO);
        PageResult<LeaveApprovalVO> data = teacherIndexService.getRecentLeaveApprovals(teacherId, queryDTO);
        return Result.success(data);
    }
}