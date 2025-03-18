package com.dl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.StudentInfoDTO;
import com.dl.result.Result;

/**
 * @author dongliang
 * @date 2024/09/23 21:37:37
 * @description
 **/
public interface SchoolManageStuService {

    Result<Page> pageQuery(Integer pageIndex, Integer pageSize);

    Result saveOne(StudentInfoDTO studentInfoDto);
}
