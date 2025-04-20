package com.dl.service;

import com.dl.entity.dto.UrgeRecordDTO;

public interface UrgeRecordService {
    
    /**
     * 创建催促记录
     * @param urgeRecordDTO 催促记录信息
     * @return 是否成功
     */
    boolean createUrgeRecord(UrgeRecordDTO urgeRecordDTO);
} 