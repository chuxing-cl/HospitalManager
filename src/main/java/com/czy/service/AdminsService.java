package com.czy.service;

import com.czy.pojo.Admins;

/**
 * @author 陈政缘
 * Date  2025/7/8 上午10:31
 */
public interface AdminsService {
    /**
     * 用户登录验证
     * @param username
     * @param password
     * @return 登录成功返回Admins对象，失败返回null
     */
    Admins login(String username, String password);
}
