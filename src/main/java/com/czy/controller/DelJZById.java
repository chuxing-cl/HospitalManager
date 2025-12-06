package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/17 下午4:17
 */

import com.czy.service.JiuZhenJiLuService;
import com.czy.service.impl.JiuZhenJiLuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/delJZById.do")
public class DelJZById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求中的学号
        Integer consultationId = Integer.parseInt(request.getParameter("JZId"));
        // 调用service中的删除方法
        JiuZhenJiLuService Service = new JiuZhenJiLuServiceImpl();
        Service.delById(consultationId);
        // 跳转到查询职称的控制器
        response.sendRedirect(request.getContextPath() + "/manage/JiuZhenJiLuSearch.do");
    }
}