package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/8 上午11:05
 */

import com.czy.pojo.Admins;
import com.czy.pojo.Patient;
import com.czy.service.AdminsService;
import com.czy.service.PatientService;
import com.czy.service.impl.AdminsServiceImpl;
import com.czy.service.impl.PatientServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/login.do")
public class AdminsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求的编码格式
        request.setCharacterEncoding("utf-8");
        // 接收请求中的相应数据
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        Integer rid = Integer.parseInt(request.getParameter("rid"));
        // 创建session对象
        HttpSession session = request.getSession();
        // 判断用户角色
        if(rid == 1){
            // 如果为管理员，则创建AdminService对象并调用登录验证方法
            AdminsService adminsService = new AdminsServiceImpl();
            Admins admin  = adminsService.login(username,password);
            System.out.println("admin---->"+admin);
            // 成功则转到管理界面
            if(admin != null){
                // 使用session对象存储管理员信息
                session.setAttribute("user",admin);
                // 跳转到index.html界面
                response.sendRedirect(request.getContextPath()+"/manage/index.jsp");
            }else{
                // 失败则转回登录界面
                session.setAttribute("loginErr","用户名或密码错误");
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }else if (rid == 3) {
            // 患者登录
            PatientService patientService = new PatientServiceImpl();
            Patient patient = patientService.login(username, password);
            if (patient != null) {
                session.setAttribute("user", patient);
                response.sendRedirect(request.getContextPath() + "/patient/index.jsp");
            } else {
                session.setAttribute("loginErr", "用户名或密码错误");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }else{

        }
    }
}