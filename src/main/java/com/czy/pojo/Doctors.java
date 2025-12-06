package com.czy.pojo;

/**
 * @author 陈政缘
 * Date  2025/7/9 下午3:15
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 关联科室表和职称表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Doctors {
    private Integer doctorId;
    private String jobNumber;
    private String password;
    private String name;
    private String avatar;
    private String phone;
    private String email;
    private String introduction;
    private Double registrationFee;
    private Date entryDate;
    private Integer departmentId;
    private Integer professionTitleId;
    private Integer state;

    // 创建关系表的属性
    private Departments departments;
    private ProfessionalTitles titles;
}
