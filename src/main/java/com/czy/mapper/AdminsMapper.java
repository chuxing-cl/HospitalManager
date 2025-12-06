package com.czy.mapper;

import com.czy.pojo.Admins;

/**
 * @author 陈政缘
 * Date  2025/7/8 上午10:15
 */
public interface AdminsMapper {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 用户对象信息
     */
    Admins selectAdminByName(String username);
}
