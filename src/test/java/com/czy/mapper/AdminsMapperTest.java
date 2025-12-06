package com.czy.mapper;

import com.czy.pojo.Admins;
import com.czy.util.SqlSessionUtil;
import org.junit.Test;

/**
 * @author 陈政缘
 * Date  2025/7/8 上午10:18
 */
public class AdminsMapperTest {
    @Test
    public void selectAdminByName() {
        // 获取mapper对象
        AdminsMapper mapper = SqlSessionUtil.getMapper(AdminsMapper.class);
        // 调用查询对象
        Admins admins = mapper.selectAdminByName("admin");
        // 显示管理员信息
        System.out.println(admins);
    }
}
