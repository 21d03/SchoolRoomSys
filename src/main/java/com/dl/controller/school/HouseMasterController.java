package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.HouseMasterQueryDTO;
import com.dl.entity.dto.HouseMasterAddDTO;
import com.dl.entity.dto.HouseMasterUpdateDTO;
import com.dl.entity.vo.HouseMasterVO;
import com.dl.result.Result;
import com.dl.service.HouseMasterService;
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
@RequestMapping("/school/house/master")
@Api(tags = "宿管管理")
public class HouseMasterController {

    @Autowired
    private HouseMasterService houseMasterService;

    @PostMapping("/page")
    @ApiOperation("分页查询宿管信息")
    public Result<IPage<HouseMasterVO>> queryHouseMasterPage(@RequestBody HouseMasterQueryDTO queryDTO) {
        return Result.success(houseMasterService.queryHouseMasterPage(queryDTO));
    }

    @PostMapping("/add")
    @ApiOperation("新增宿管")
    public Result<Boolean> addHouseMaster(@RequestBody HouseMasterAddDTO addDTO) {
        boolean result = houseMasterService.addHouseMaster(addDTO);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("新增宿管失败");
        }
    }

    @PutMapping("/update")
    @ApiOperation("修改宿管信息")
    public Result<Boolean> updateHouseMaster(@RequestBody HouseMasterUpdateDTO updateDTO) {
        boolean result = houseMasterService.updateHouseMaster(updateDTO);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("修改失败，宿管不存在");
        }
    }

    @DeleteMapping("/{hmId}")
    @ApiOperation("删除宿管")
    public Result<Boolean> deleteHouseMaster(@PathVariable String hmId) {
        int result = houseMasterService.deleteHouseMaster(hmId);
        switch (result) {
            case 0:
                return Result.success(true);
            case 1:
                return Result.error("该宿管分管有宿舍楼，不可删除");
            case 2:
                return Result.error("宿管不存在");
            default:
                return Result.error("删除失败");
        }
    }
} 