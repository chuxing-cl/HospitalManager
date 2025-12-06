package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/8 下午3:56
 */

import com.czy.pojo.Departments;
import com.czy.service.DepartmentsService;
import com.czy.service.impl.DepartmentsServiceImpl;
import com.github.pagehelper.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/departmentByFirst.do")
public class DepartmentsByFirstLevelController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处于manage目录下，已经由过滤器统一设置请求的编码格式
        // 接收请求中的页码
        Integer pageSize = 5;
        Integer pageNum = 1;
        String page =request.getParameter("page");
        if(page!=null && page.length()>0) {
            pageNum = Integer.parseInt(page);
        }
        //调用DepartmentsService中分页查询的方法
        DepartmentsService departmentsService = new DepartmentsServiceImpl();
        PageInfo<Departments> pageInfo = departmentsService.getDepartmentsByFirst(pageNum, pageSize);
        //使用request对象,存储pageInfo值
        request.setAttribute("pageInfo", pageInfo);
        //页面转发到科室列表页面
        request.getRequestDispatcher("/manage/departmentList.jsp").forward(request, response);
    }
}