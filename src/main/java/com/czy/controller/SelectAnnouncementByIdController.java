package com.czy.controller;

import com.czy.pojo.Announcement;
import com.czy.service.AnnouncementService;
import com.czy.service.impl.AnnouncementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/selectAnnouncementById.do")
public class SelectAnnouncementByIdController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收请求中的参数值
        Integer announcementId = Integer.parseInt(request.getParameter("announcementId"));

        // 调用service中对应的方法
        AnnouncementService announcementService = new AnnouncementServiceImpl();
        Announcement announcement = announcementService.getAnnouncementById(announcementId);

        // 转发到updateAnnouncement.jsp界面
        // 使用request存储公告对象
        request.setAttribute("announcement", announcement);
        request.getRequestDispatcher("/manage/updateAnnouncement.jsp").forward(request, response);
    }
}