package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/16 下午10:17
 */

import com.czy.pojo.Hospitalization;
import com.czy.service.ZhuYuanJiLuService;
import com.czy.service.impl.ZhuYuanJiLuServiceImpl;
import com.github.pagehelper.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/ZhuYuanJiLuSearch.do")
public class ZhuYuanJiLuSearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处于manage目录下，已经由过滤器统一设置请求的编码格式
        // 接收请求中的页码
        Integer pageSize = 10;
        Integer pageNum = 1;
        String page =request.getParameter("page");
        if(page!=null && page.length()>0) {
            pageNum = Integer.parseInt(page);
        }
        //调用Service中分页查询的方法
        ZhuYuanJiLuService Service = new ZhuYuanJiLuServiceImpl();
        PageInfo<Hospitalization> pageInfo = Service.getZhuYuanJiLu(pageNum, pageSize);
        //使用request对象,存储pageInfo值
        request.setAttribute("pageInfo", pageInfo);
        //页面转发到科室列表页面
        request.getRequestDispatcher("/manage/zhuyuanjiluList.jsp").forward(request, response);
    }
}