package com.czy.controller;

import com.czy.pojo.Consultation;
import com.czy.pojo.Patient;
import com.czy.service.ConsultationService;
import com.czy.service.impl.ConsultationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/prescriptions")
public class ConsultationController extends HttpServlet {
    private final ConsultationService service = new ConsultationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        Patient user = (Patient) session.getAttribute("user");
        List<Consultation> list = service.getByPatient(user.getPatientId());
        request.setAttribute("consultations", list);
        request.getRequestDispatcher("/patient/prescriptions.jsp")
                .forward(request, response);
    }
}
