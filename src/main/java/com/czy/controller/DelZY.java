package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/17 下午6:19
 */

import com.czy.service.ZhuYuanJiLuService;
import com.czy.service.impl.ZhuYuanJiLuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/delZY.do")
public class DelZY extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用service中的删除方法
        ZhuYuanJiLuService Service = new ZhuYuanJiLuServiceImpl();
        Service.delZY();
        // 跳转到查询职称的控制器
        response.sendRedirect(request.getContextPath() + "/manage/ZhuYuanJiLuSearch.do");
    }
}