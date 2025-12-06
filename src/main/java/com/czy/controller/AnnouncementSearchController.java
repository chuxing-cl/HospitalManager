package com.czy.controller;

import com.czy.pojo.Announcement;
import com.czy.service.AnnouncementService;
import com.czy.service.impl.AnnouncementServiceImpl;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/announcementSearch.do")
public class AnnouncementSearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收请求中的页码
        Integer pageSize = 5;
        Integer pageNum = 1;
        String page = request.getParameter("page");
        if (page != null && page.length() > 0) {
            pageNum = Integer.parseInt(page);
        }

        // 调用AnnouncementService中分页查询的方法
        AnnouncementService announcementService = new AnnouncementServiceImpl();
        PageInfo<Announcement> pageInfo = announcementService.selectAnnouncementsAll(pageNum, pageSize);

        // 使用request对象，存储pageInfo值
        request.setAttribute("pageInfo", pageInfo);

        // 页面转发到公告列表页面
        request.getRequestDispatcher("/manage/announcementList.jsp").forward(request, response);
    }
}