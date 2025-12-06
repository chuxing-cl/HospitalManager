package com.czy.controller;

import com.czy.pojo.Patient;
import com.czy.service.PatientService;
import com.czy.service.impl.PatientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/register.do")
public class RegisterController extends HttpServlet {
    private final PatientService patientService = new PatientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 直接跳转到注册页面
        req.getRequestDispatcher("/patient/register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
      //  req.setCharacterEncoding("utf-8");
        String pname         = req.getParameter("pname");
        String idCardNumber  = req.getParameter("idCardNumber");
        String password      = req.getParameter("password");

        // 简单校验：用户名是否已存在
        if (patientService.login(idCardNumber, password) != null) {
            req.setAttribute("error", "该身份证号已被注册");
            doGet(req, resp);
            return;
        }

        // 构造 Patient 对象
        Patient p = new Patient();
        p.setPname(pname);
        p.setIdCardNumber(idCardNumber);
        p.setPassword(password);
        p.setBalance(BigDecimal.ZERO);  // 初始余额 0

        boolean ok = patientService.register(p);
        if (ok) {
            // 注册成功，跳回登录页
            resp.sendRedirect(req.getContextPath() + "/login.jsp?msg=注册成功，请登录");
        } else {
            req.setAttribute("error", "注册失败，请稍后重试");
            doGet(req, resp);
        }
    }
}
