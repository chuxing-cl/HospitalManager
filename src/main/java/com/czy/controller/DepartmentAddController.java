package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/9 上午10:45
 */

import com.czy.pojo.Departments;
import com.czy.service.DepartmentsService;
import com.czy.service.impl.DepartmentsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/addDepartment.do")
public class DepartmentAddController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收请求中的科室信息
        String departmentName = request.getParameter("departmentName");
        Integer departmentPid = Integer.parseInt(request.getParameter("departmentPid"));
        Integer departmentLevel = Integer.parseInt(request.getParameter("departmentLevel"));
        String departmenDescription = request.getParameter("departmentDescription");
        // 把数据封装到Department对象中
        Departments departments = new Departments();
        departments.setDepartmentName(departmentName);
        departments.setDepartmentPid(departmentPid);
        departments.setDepartmentLevel(departmentLevel);
        departments.setDepartmentDescription(departmenDescription);
        // 调用service中的方法
        DepartmentsService departmentsService = new DepartmentsServiceImpl();
        departmentsService.addDepartment(departments);
        // 跳转到查询科室的控制器
        response.sendRedirect(request.getContextPath() + "/manage/departmentByFirst.do");
    }
}