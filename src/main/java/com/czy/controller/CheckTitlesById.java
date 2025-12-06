package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/10 下午3:40
 */

import com.czy.pojo.ProfessionalTitles;
import com.czy.service.ProfessionalTitlesService;
import com.czy.service.impl.ProfessionalTitlesServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/manage/checkTitlesById.do")
public class CheckTitlesById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收学号
        String titleName = request.getParameter("titleName");
        // 调用service中根据学号查询的方法
        // 调用service中对应的方法
        ProfessionalTitlesService professionalTitlesService = new ProfessionalTitlesServiceImpl();
        ProfessionalTitles professionalTitle = professionalTitlesService.getTitlesByName(titleName);
        // 设置响应的编码格式
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        // 获取输出流对象,此输出流用于向客户端（浏览器）发送数据
        PrintWriter out = response.getWriter();
        // 调用输出流的print方法向客户端输出student!=null的结果
        // 注意不要使用println(),此方法会多输出一个回车符
        out.print(professionalTitle != null); // true表示编号存在，否则不存在
        // 刷新输出流
        out.flush();
    }
}