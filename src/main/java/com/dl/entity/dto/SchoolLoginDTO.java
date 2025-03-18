package com.dl.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongliang
 * @date 2024/09/22 20:43:43
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolLoginDTO {

    private String userId;

    private String passWord;
}
