package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/10 上午10:37
 */

import com.czy.pojo.ProfessionalTitles;
import com.czy.service.ProfessionalTitlesService;
import com.czy.service.impl.ProfessionalTitlesServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/addTitles.do")
public class TitlesAddController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收请求中的职称信息
        String titleName = request.getParameter("titleName");
        String description = request.getParameter("description");
        // 把数据封装到ProfessionalTitles对象中
        ProfessionalTitles professionalTitles = new ProfessionalTitles();
        professionalTitles.setTitleName(titleName);
        professionalTitles.setDescription(description);
        // 调用service中的方法
        ProfessionalTitlesService professionalTitlesService = new ProfessionalTitlesServiceImpl();
        professionalTitlesService.addTitle(professionalTitles);
        // 跳转到查询职称的控制器
        response.sendRedirect(request.getContextPath() + "/manage/titlesSearch.do");
    }
}