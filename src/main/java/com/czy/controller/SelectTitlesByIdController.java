package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/10 上午11:29
 */

import com.czy.pojo.ProfessionalTitles;
import com.czy.service.ProfessionalTitlesService;
import com.czy.service.impl.ProfessionalTitlesServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/selectTitlesById.do")
public class SelectTitlesByIdController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收请求中的参数值
        Integer titleId = Integer.parseInt(request.getParameter("titleId"));
        // 调用service中对应的方法
        ProfessionalTitlesService professionalTitlesService = new ProfessionalTitlesServiceImpl();
        ProfessionalTitles professionalTitle = professionalTitlesService.getTitlesById(titleId);
        // 转发到updateStudent.jsp界面
        // 使用request存储学生对象
        request.setAttribute("professionalTitle", professionalTitle);
        request.getRequestDispatcher("/manage/updateTitles.jsp").forward(request, response);
    }
}