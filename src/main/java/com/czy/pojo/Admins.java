package com.czy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈政缘
 * Date  2025/7/8 上午10:11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Admins {
    private int adminId;
    private String username;
    private String password;
    private String name;
    private String avatar;
    private String phone;
    private String email;
}
