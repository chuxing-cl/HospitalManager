package com.czy.service;

import com.czy.pojo.Doctors;
import com.czy.vo.DoctorSearchVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/9 下午4:49
 */
public interface DoctorsService {
    /**
     * 按条件分页查询学生信息
     * @param searchVo 条件对象
     * @param pageNum 当前页码
     * @param pageSize 每页显示的数量
     * @return pageInfo对象
     */
    PageInfo<Doctors> getDoctorByPageAndSearch(DoctorSearchVo searchVo, Integer pageNum, Integer pageSize);

    List<Doctors> AllDoctorNames();
}
