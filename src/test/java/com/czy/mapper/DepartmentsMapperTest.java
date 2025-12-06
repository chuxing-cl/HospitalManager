package com.czy.mapper;

import com.czy.pojo.Admins;
import com.czy.pojo.Departments;
import com.czy.util.SqlSessionUtil;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/8 下午3:28
 */
public class DepartmentsMapperTest {
    @Test
    public void getDepartmentsTest() {
        // 获取mapper对象
        DepartmentsMapper mapper = SqlSessionUtil.getMapper(DepartmentsMapper.class);
        // 调用查询对象
        List<Departments> departments = mapper.selectDepartmentsByLevel(1);
        // 显示所有科室信息
        for(Departments department:departments){
            System.out.println(department);
        }
    }

    @Test
    public void addDepartment() {
        // 获取mapper对象
        DepartmentsMapper mapper = SqlSessionUtil.getMapper(DepartmentsMapper.class);
        // 调用添加方法
        Departments departments = new Departments();
        departments.setDepartmentName("测试7-0");
        departments.setDepartmentPid(45);
        departments.setDepartmentLevel(2);
        departments.setDepartmentDescription("7-0测试科室");
        System.out.println("添加前的科室信息"+departments);
        mapper.addDepartment(departments);
        System.out.println("添加后的科室信息---->"+departments);
        //判断pid值是否不为0
        Departments depart=null;    //父科室信息
        if (departments.getDepartmentPid()!=0){
            //查询PID对应的科室信息
            depart =mapper.selectById(departments.getDepartmentPid());
        }
        //调用修改方法
        //设置当前科室的路径=父科室路径+当前科室ID
        String departmentPath=(depart!=null?depart.getDepartmentPath():"")+"|"+departments.getDepartmentId()+"|";
        mapper.changePath(departmentPath,departments.getDepartmentId());
        //事务提交
        SqlSessionUtil.commit();
        //释放资源
        SqlSessionUtil.closeSession();
    }
}
