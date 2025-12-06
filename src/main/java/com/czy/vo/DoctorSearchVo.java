package com.czy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈政缘
 * Date  2025/7/9 下午3:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorSearchVo {
    private Integer departmentId;
    private Integer titleId;
    private String doctorName;
    private String jobNumber;
}
