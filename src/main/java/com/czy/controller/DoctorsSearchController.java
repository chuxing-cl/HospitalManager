package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/9 下午4:55
 */

import com.czy.pojo.Doctors;
import com.czy.service.DoctorsService;
import com.czy.service.impl.DoctorsServiceImpl;
import com.czy.vo.DoctorSearchVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/doctorsSearch.do")
public class DoctorsSearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 接收网页
        Integer pageSize = 5;
        Integer pageNum = 1;
        String page = request.getParameter("page");
        if (page != null && page.length() > 0) {
            pageNum = Integer.parseInt(page);
        }
        //2 接收查询条件
        // 科室
        Integer departmentId = null;
        String strDepartmentId = request.getParameter("departmentId");
        if (strDepartmentId != null && strDepartmentId.length() > 0) {
            departmentId = Integer.parseInt(strDepartmentId);
        }
        // 职称
        Integer titleId = null;
        String strTitleId = request.getParameter("titleId");
        if (strTitleId != null && strTitleId.length() > 0) {
            titleId = Integer.parseInt(strTitleId);
        }
        // 职工号和姓名
        String jobNumber = request.getParameter("jobNumber");
        String doctorName = request.getParameter("doctorName");
        // 封装到DoctorServiceVo
        DoctorSearchVo searchVo = new DoctorSearchVo(departmentId, titleId, doctorName, jobNumber);
        //3 创建Service对象并调用查询方法
        DoctorsService doctorsService = new DoctorsServiceImpl();
        PageInfo<Doctors> pageInfo = doctorsService.getDoctorByPageAndSearch(searchVo, pageNum, pageSize);
        //4 使用request存储查询结果
        request.setAttribute("pageInfo", pageInfo);
        //5 转到显示所有医生界面
        request.getRequestDispatcher("/manage/doctorList.jsp").forward(request, response);
    }
}