package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/10 上午9:27
 */

import com.czy.pojo.ProfessionalTitles;
import com.czy.service.ProfessionalTitlesService;
import com.czy.service.impl.ProfessionalTitlesServiceImpl;
import com.github.pagehelper.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/titlesSearch.do")
public class TitlesSearchController extends HttpServlet {
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
        System.out.println("---------------pageNum:"+pageNum);
        //调用ProfessionalTitlesService中分页查询的方法
        ProfessionalTitlesService professionalTitlesService = new ProfessionalTitlesServiceImpl();
        PageInfo<ProfessionalTitles> pageInfo =  professionalTitlesService.selectTitlesAll(pageNum, pageSize);
        //使用request对象,存储pageInfo值
        request.setAttribute("pageInfo", pageInfo);
        //页面转发到科室列表页面
        request.getRequestDispatcher("/manage/professionalTitlesList.jsp").forward(request, response);
    }
}