package com.czy.controller;

import com.czy.pojo.Patient;
import com.czy.service.PatientService;
import com.czy.service.impl.PatientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
    private final PatientService patientService = new PatientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Patient sessionUser = (Patient) req.getSession().getAttribute("user");
        // 每次都重新查一次
        Patient fresh = patientService.getById(sessionUser.getPatientId());
        // 更新 Session（可选）
        req.getSession().setAttribute("user", fresh);
        // 传给 JSP
        req.setAttribute("user", fresh);
        req.getRequestDispatcher("/patient/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        Patient user = (Patient) session.getAttribute("user");

        // 读取表单参数
        String pname  = request.getParameter("pname");
        String pwd    = request.getParameter("password");
        String balStr = request.getParameter("balance");

        // 构造待更新的 Patient 对象
        Patient toUpdate = new Patient();
        toUpdate.setPatientId(user.getPatientId());
        toUpdate.setPname(pname);
        toUpdate.setPassword((pwd != null && !pwd.isEmpty()) ? pwd : user.getPassword());
        toUpdate.setBalance(new java.math.BigDecimal(balStr));
        toUpdate.setAvatar(user.getAvatar());
        toUpdate.setEmail(user.getEmail());
        toUpdate.setPhone(user.getPhone());
        toUpdate.setIdCardNumber(user.getIdCardNumber());

        // 调用 Service 更新
        boolean ok = patientService.update(toUpdate);

        if (ok) {
            // 更新 session 中的 user 对象
            user.setPname(toUpdate.getPname());
            user.setPassword(toUpdate.getPassword());
            user.setBalance(toUpdate.getBalance());
            session.setAttribute("user", user);
            request.setAttribute("updateSuccess", true);
        } else {
            request.setAttribute("updateSuccess", false);
        }

        // 始终 forward 回 profile.jsp 并显示提示
        request.getRequestDispatcher("/patient/profile.jsp")
                .forward(request, response);
    }
}
