// src/main/java/com/czy/controller/HospitalizationPayController.java
package com.czy.controller;

import com.czy.pojo.Patient;
import com.czy.service.HospitalizationService;
import com.czy.service.impl.HospitalizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/hospitalization/pay")
public class HospitalizationPayController extends HttpServlet {
    private final HospitalizationService service = new HospitalizationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (session==null || session.getAttribute("user")==null) {
            out.write("{\"success\":false,\"message\":\"请先登录\"}");
            return;
        }
        Patient p = (Patient)session.getAttribute("user");
        int hospId = Integer.parseInt(req.getParameter("hospitalizationId"));

        Map<String,Object> result = service.pay(hospId, p.getPatientId());
        // 简单用 JSON.stringify 的格式输出
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"success\":").append(result.get("success"));
        if ((Boolean)result.get("success")) {
            sb.append(",\"newBalance\":").append(result.get("newBalance"));
        } else {
            sb.append(",\"message\":\"").append(result.get("message")).append("\"");
        }
        sb.append("}");
        out.write(sb.toString());
    }
}
