package com.czy.controller;

import com.czy.pojo.Doctors;
import com.czy.service.ProfessionalTitlesService;
import com.czy.service.impl.ProfessionalTitlesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/viewDoctorsByTitle.do")
public class ViewDoctorsByTitleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titleIdParam = request.getParameter("titleId");
        Integer titleId = null;
        if (titleIdParam != null && !titleIdParam.isEmpty()) {
            try {
                titleId = Integer.parseInt(titleIdParam);
            } catch (NumberFormatException e) {
                // 处理转换异常，例如返回错误信息给用户
                response.getWriter().println("Invalid titleId parameter");
                return;
            }
        } else {
            // 处理参数为空的情况，例如返回错误信息给用户
            response.getWriter().println("titleId parameter is missing");
            return;
        }

        ProfessionalTitlesService professionalTitlesService = new ProfessionalTitlesServiceImpl();
        List<Doctors> doctorList = professionalTitlesService.selectDoctorsByTitleId(titleId);
        request.setAttribute("doctorList", doctorList);
        request.getRequestDispatcher("/manage/doctorList.jsp").forward(request, response);
    }
}