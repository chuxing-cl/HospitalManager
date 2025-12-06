package com.czy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 患者（用户）实体类
 * Date  2025/7/8 上午10:11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Patient {
    private int patientId;
    private String idCardNumber;
    private String password;
    private String pname;
    private String avatar;
    private String phone;
    private String email;
    private BigDecimal balance;
}