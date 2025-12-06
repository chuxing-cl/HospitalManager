package com.czy.controller;

import com.czy.pojo.Patient;
import com.czy.service.ConsultationService;
import com.czy.service.PatientService;
import com.czy.service.impl.ConsultationServiceImpl;
import com.czy.service.impl.PatientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet("/consultation/pay")
public class ConsultationPayController extends HttpServlet {
    private final ConsultationService consultationService = new ConsultationServiceImpl();
    private final PatientService patientService = new PatientServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 未登录
            out.write("{\"success\":false,\"message\":\"未登录，请先登录\"}");
            return;
        }

        Patient user = (Patient) session.getAttribute("user");
        int consulId;
        try {
            consulId = Integer.parseInt(request.getParameter("consultationId"));
        } catch (NumberFormatException e) {
            out.write("{\"success\":false,\"message\":\"参数错误\"}");
            return;
        }

        // 1) 从数据库读出处方价格
        int amount = consultationService.getPrice(consulId);

        // 2) 扣款并标记已缴费
        boolean ok = consultationService.pay(consulId, user.getPatientId(), amount);
        if (ok) {
            // 扣款成功，重新读取最新余额
            BigDecimal newBal = patientService.getById(user.getPatientId()).getBalance();
            out.write("{\"success\":true,\"newBalance\":" + newBal + "}");
        } else {
            out.write("{\"success\":false,\"message\":\"余额不足或已缴费\"}");
        }
    }
}
