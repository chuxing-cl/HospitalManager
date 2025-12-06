package com.czy.service.impl;

import com.czy.mapper.JiuZhenJiLuMapper;
import com.czy.mapper.ZhuYuanJiLuMapper;
import com.czy.pojo.Hospitalization;
import com.czy.pojo.JiuZhenJiLu;
import com.czy.service.ZhuYuanJiLuService;
import com.czy.util.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/16 下午10:24
 */
public class ZhuYuanJiLuServiceImpl implements ZhuYuanJiLuService {
    @Override
    public PageInfo<Hospitalization> getZhuYuanJiLu(Integer pageNum, Integer pageSize) {
        try {
            // 创建Mapper对象
            ZhuYuanJiLuMapper mapper = SqlSessionUtil.getMapper(ZhuYuanJiLuMapper.class);
            // 调用PageHelper.startPage()设置页码
            PageHelper.startPage(pageNum, pageSize);
            // 调用mapper中的查询方法
            List<Hospitalization> List = mapper.selectZhuYuanJiLu();
            // 创建PageInfo对象
            PageInfo<Hospitalization> pageInfo = new PageInfo<>(List);
            // 返回PageInfo对象
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public void delZY() {
        try {
            // 创建Mapper对象
            ZhuYuanJiLuMapper mapper = SqlSessionUtil.getMapper(ZhuYuanJiLuMapper.class);
            // 调用删除方法
            mapper.delZY();
            // 提交事务
            SqlSessionUtil.commit();
        } catch (Exception e) {
            SqlSessionUtil.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }
}
