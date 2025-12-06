package com.czy.service.impl;

import com.czy.mapper.DepartmentsMapper;
import com.czy.pojo.Departments;
import com.czy.service.DepartmentsService;
import com.czy.util.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/8 下午4:27
 */
public class DepartmentsServiceImpl implements DepartmentsService {
    @Override
    public PageInfo<Departments> getDepartmentsByFirst(Integer pageNum, Integer pageSize) {
        try {
            // 创建DepartmentsMapper对象
            DepartmentsMapper mapper = SqlSessionUtil.getMapper(DepartmentsMapper.class);
            // 调用PageHelper.startPage()设置页码
            PageHelper.startPage(pageNum, pageSize);
            // 调用mapper中的查询方法
            List<Departments> departmentsList = mapper.selectDepartmentsByLevel(1);
            // 创建PageInfo对象
            PageInfo<Departments> pageInfo = new PageInfo<>(departmentsList);
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
    public void addDepartment(Departments departments) {
        try {
            // 创建DepartmentsMapper对象
            DepartmentsMapper mapper = SqlSessionUtil.getMapper(DepartmentsMapper.class);
            // 调用mapper中的添加方法
            mapper.addDepartment(departments);
            // 判断是否为父科室，有则获取父科室信息
            Departments parentDepartment = null;
            if(departments.getDepartmentPid()!=0){
                parentDepartment = mapper.selectById(departments.getDepartmentPid());
            }
            // 修改当前科室的path,设置path=父路径+"|"+当前科室ID+"|"
            String departmentPath=(parentDepartment!=null?parentDepartment.getDepartmentPath():"")
                    +"|"+departments.getDepartmentId()+"|";
            mapper.changePath(departmentPath,departments.getDepartmentId());
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

    @Override
    public List<Departments> getDepartmentBySecond() {
        try {
            // 创建DepartmentsMapper对象
            DepartmentsMapper mapper = SqlSessionUtil.getMapper(DepartmentsMapper.class);
            // 调用根据等级查询的方法
            List<Departments> departmentsList = mapper.selectDepartmentsByLevel(2);
            // 返回查询结果
            return departmentsList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public List<Departments> AllDepartmentsNames() {
        try {
            DepartmentsMapper mapper = SqlSessionUtil.getMapper(DepartmentsMapper.class);
            // 调用修改后的方法名
            List<Departments> departments= mapper.AllDepartmentsNames();
            return departments;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }
}
