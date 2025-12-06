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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/manage/updateAnnouncementById.do")
public class UpdateAnnouncementByIdController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收请求中的参数值
        Integer announcementId = Integer.parseInt(request.getParameter("announcementId"));
        String title = request.getParameter("title");
        String imgurl = request.getParameter("imgurl");
        String content = request.getParameter("content");
        String creationTimeStr = request.getParameter("creationTime");
        String creator = request.getParameter("creator");

        Announcement announcement = new Announcement();
        announcement.setAnnouncementId(announcementId);
        announcement.setTitle(title);
        announcement.setImgurl(imgurl);
        announcement.setContent(content);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date creationTime = sdf.parse(creationTimeStr);
            announcement.setCreationTime(creationTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        announcement.setCreator(creator);

        // 调用service中对应的方法
        AnnouncementService announcementService = new AnnouncementServiceImpl();
        announcementService.updateAnnouncementById(announcement);

        // 页面跳转到查询公告信息的控制器
        response.sendRedirect(request.getContextPath() + "/manage/announcementSearch.do");
    }
}