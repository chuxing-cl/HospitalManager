package com.czy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈政缘
 * Date  2025/7/8 下午3:20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Departments {
    private int departmentId;
    private String departmentName;
    private int departmentPid;
    private int departmentLevel;
    private String departmentPath;
    private String departmentDescription;
}
