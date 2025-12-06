package com.czy.mapper;

import com.czy.pojo.Departments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/8 下午3:23
 */
public interface DepartmentsMapper {
    /**
     *  查询指定级别科室的信息
     * @return
     */
    List<Departments> selectDepartmentsByLevel(Integer departmentLevel);

    /**
     * 添加新科室
     * 方法参数为科室对象类型，因此SQL语句的参数占位符名必须与对象的属性名相同
     * @param departments
     */
    void addDepartment(Departments departments);

    /**
     * 修改指定科室的路径
     * 参数多于1时使用@Param("名"),要求必须与SQL语句的参数占位符名一致
     * @param departmentPath
     * @param departmentId
     */
    void changePath(@Param("departmentPath") String departmentPath, @Param("departmentId") Integer departmentId);

    /**
     * 查询指定编号的科室信息
     * @param departmentId 科室编号
     * @return
     */
    Departments selectById(Integer departmentId);

    List<Departments> AllDepartmentsNames();
}
