package com.czy.service;

import com.czy.pojo.Departments;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/8 下午3:53
 */
public interface DepartmentsService {
    /**
     * 分页查询一级科室的信息
     * @param pageNum 当前页码
     * @param pageSize 每页显示的数量
     * @return 当前的PageInfo对象
     */
    PageInfo<Departments> getDepartmentsByFirst(Integer pageNum, Integer pageSize);

    /**
     * 添加新科室
     * @param departments
     */
    void addDepartment(Departments departments);

    /**
     * 查询所有二级科室的信息
     * @return
     */
    List<Departments> getDepartmentBySecond();

    List<Departments> AllDepartmentsNames();
}
