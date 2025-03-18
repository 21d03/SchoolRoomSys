package com.dl.service;

import com.dl.entity.dto.SchoolLoginDTO;
import com.dl.entity.vo.SchoolLoginVO;
import com.dl.result.Result;

/**
 * @author dongliang
 * @date 2024/09/22 21:18:18
 * @description
 **/
public interface SchoolLoginService {

    Result<SchoolLoginVO> login(SchoolLoginDTO schoolLoginDTO);
}
