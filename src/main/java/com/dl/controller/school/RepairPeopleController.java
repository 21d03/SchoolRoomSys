package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RepairPeopleQueryDTO;
import com.dl.entity.dto.RepairPeopleAddDTO;
import com.dl.entity.dto.RepairPeopleUpdateDTO;
import com.dl.entity.vo.RepairPeopleVO;
import com.dl.result.Result;
import com.dl.service.RepairPeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/repair/people")
@Api(tags = "维修人员管理")
public class RepairPeopleController {

    @Autowired
    private RepairPeopleService repairPeopleService;

    @PostMapping("/page")
    @ApiOperation("分页查询维修人员信息")
    public Result<IPage<RepairPeopleVO>> queryRepairPeoplePage(@RequestBody RepairPeopleQueryDTO queryDTO) {
        return Result.success(repairPeopleService.queryRepairPeoplePage(queryDTO));
    }

    @PostMapping("/add")
    @ApiOperation("新增维修人员")
    public Result<Boolean> addRepairPeople(@RequestBody RepairPeopleAddDTO addDTO) {
        boolean result = repairPeopleService.addRepairPeople(addDTO);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("新增维修人员失败");
        }
    }

    @PutMapping("/update")
    @ApiOperation("修改维修人员信息")
    public Result<Boolean> updateRepairPeople(@RequestBody RepairPeopleUpdateDTO updateDTO) {
        boolean result = repairPeopleService.updateRepairPeople(updateDTO);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("修改失败，维修人员不存在");
        }
    }

    @DeleteMapping("/{rpId}")
    @ApiOperation("删除维修人员")
    public Result<Boolean> deleteRepairPeople(@PathVariable String rpId) {
        int result = repairPeopleService.deleteRepairPeople(rpId);
        switch (result) {
            case 0:
                return Result.success(true);
            case 1:
                return Result.error("删除失败");
            case 2:
                return Result.error("维修人员不存在");
            default:
                return Result.error("删除失败");
        }
    }
}
