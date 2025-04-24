package com.dl.controller.housemaster;

import com.dl.common.Result;
import com.dl.service.HmIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 宿管首页控制器
 */
@RestController
@RequestMapping("/hm/index")
@Slf4j
public class HmIndexController {

    @Autowired
    private HmIndexService hmIndexService;

    /**
     * 获取宿管首页统计数据
     *
     * @param hmId    宿管ID
     * @param buildId 宿舍楼ID
     * @return 统计数据
     */
    @GetMapping("/data")
    public Result getIndexData(@RequestParam("hmId") String hmId,
                              @RequestParam("buildId") String buildId) {
        log.info("获取宿管首页统计数据，hmId: {}, buildId: {}", hmId, buildId);
        return hmIndexService.getIndexData(hmId, buildId);
    }
}