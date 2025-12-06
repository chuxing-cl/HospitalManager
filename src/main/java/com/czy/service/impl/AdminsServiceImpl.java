package com.czy.service.impl;

import com.czy.mapper.AdminsMapper;
import com.czy.pojo.Admins;
import com.czy.service.AdminsService;
import com.czy.util.SqlSessionUtil;

/**
 * @author 陈政缘
 * Date  2025/7/8 上午10:32
 */
public class AdminsServiceImpl implements AdminsService {
    @Override
    public Admins login(String username, String password) {
        try {
            // 创建AdminsMapper的对象
            AdminsMapper adminsMapper = SqlSessionUtil.getMapper(AdminsMapper.class);
            // 调用查询方法
            Admins admin = adminsMapper.selectAdminByName(username);
            // 如果查询的Admins对象不为null，则判断密码是否相等，相等则返回Admins对象
            if (admin != null && admin.getPassword().equals(password)) {
                return admin;
            }
            // 否则返回null
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }
}
