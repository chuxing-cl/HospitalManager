package com.czy.service;

import com.czy.pojo.Admins;
import com.czy.service.impl.AdminsServiceImpl;
import org.junit.Test;

/**
 * @author 陈政缘
 * Date  2025/7/8 上午10:37
 */
public class AdminsServiceTest {
    @Test
    public void login() {
        // 创建AdminsService对象
        AdminsService adminsService = new AdminsServiceImpl();
        // 调用服务层的方法
        Admins admins = adminsService.login("admin","12345");
        // 显示登录结果
        System.out.println((admins!=null)?"登录成功":"登录失败");
    }
}
