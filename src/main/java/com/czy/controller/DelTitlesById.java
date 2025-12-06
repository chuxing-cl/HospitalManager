package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/10 下午2:45
 */

import com.czy.service.ProfessionalTitlesService;
import com.czy.service.impl.ProfessionalTitlesServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/delTitlesById.do")
public class DelTitlesById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求中的学号
        Integer titlesId = Integer.parseInt(request.getParameter("titlesId"));
        // 调用service中的删除方法
        ProfessionalTitlesService professionalTitlesService = new ProfessionalTitlesServiceImpl();
        professionalTitlesService.delTitlesById(titlesId);
        // 跳转到查询职称的控制器
        response.sendRedirect(request.getContextPath() + "/manage/titlesSearch.do");
    }
}