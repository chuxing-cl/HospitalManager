package com.czy.service.impl;

import com.czy.mapper.JiuZhenJiLuMapper;
import com.czy.mapper.ProfessionalTitlesMapper;
import com.czy.pojo.JiuZhenJiLu;
import com.czy.service.JiuZhenJiLuService;
import com.czy.util.SqlSessionUtil;
import com.czy.vo.JiuZhenSearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/16 下午8:34
 */
public class JiuZhenJiLuServiceImpl implements JiuZhenJiLuService {
    @Override
    public PageInfo<JiuZhenJiLu> getJiuZhenJiLu(JiuZhenSearchVo searchVo, Integer pageNum, Integer pageSize) {
        try {
            // 创建Mapper对象
            JiuZhenJiLuMapper mapper = SqlSessionUtil.getMapper(JiuZhenJiLuMapper.class);
            // 调用PageHelper.startPage()设置页码
            PageHelper.startPage(pageNum, pageSize);
            // 调用mapper中的查询方法
            List<JiuZhenJiLu> List = mapper.selectJiuZhenJiLu(searchVo);
            // 创建PageInfo对象
            PageInfo<JiuZhenJiLu> pageInfo = new PageInfo<>(List);
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
    public void delById(Integer consultationId) {
        try {
            // 创建Mapper对象
            JiuZhenJiLuMapper mapper = SqlSessionUtil.getMapper(JiuZhenJiLuMapper.class);
            // 调用add方法
            mapper.delById(consultationId);
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
