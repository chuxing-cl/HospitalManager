package com.czy.service.impl;

import com.czy.mapper.DoctorsMapper;
import com.czy.pojo.Doctors;
import com.czy.service.DoctorsService;
import com.czy.util.SqlSessionUtil;
import com.czy.vo.DoctorSearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/9 下午4:51
 */
public class DoctorsServiceImpl implements DoctorsService {
    @Override
    public PageInfo<Doctors> getDoctorByPageAndSearch(DoctorSearchVo searchVo, Integer pageNum, Integer pageSize) {
        try {
            // 创建Mapper对象
            DoctorsMapper mapper = SqlSessionUtil.getMapper(DoctorsMapper.class);
            // 调用PageHelper.startPage()设置页码
            PageHelper.startPage(pageNum, pageSize);
            // 调用mapper中的查询方法
            List<Doctors> doctorsList = mapper.selectDoctorAll(searchVo);
            // 创建PageInfo对象
            PageInfo<Doctors> pageInfo = new PageInfo<>(doctorsList);
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
    public List<Doctors> AllDoctorNames() {
        try {

            DoctorsMapper mapper = SqlSessionUtil.getMapper(DoctorsMapper.class);
            // 执行查询
            List<Doctors> doctors = mapper.AllDoctorNames();
            return doctors;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }
}
