package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/10 上午11:23
 */

import com.czy.pojo.ProfessionalTitles;
import com.czy.service.ProfessionalTitlesService;
import com.czy.service.impl.ProfessionalTitlesServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/updateTitlesById.do")
public class UpdateTitlesByIdController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收请求中的参数值
        Integer professionalTitleId = Integer.parseInt(request.getParameter("id"));
        String professionalTitleName = request.getParameter("titleName");
        String professionalTitleDescription = request.getParameter("description");
        ProfessionalTitles professionalTitle = new ProfessionalTitles();
        professionalTitle.setId(professionalTitleId);
        professionalTitle.setTitleName(professionalTitleName);
        professionalTitle.setDescription(professionalTitleDescription);
        // 调用service中对应的方法
        ProfessionalTitlesService professionalTitlesService = new ProfessionalTitlesServiceImpl();
        professionalTitlesService.updateTitlesById(professionalTitle);
        // 页面跳转到查询职务信息的控制器
        response.sendRedirect(request.getContextPath() + "/manage/titlesSearch.do");
    }
}