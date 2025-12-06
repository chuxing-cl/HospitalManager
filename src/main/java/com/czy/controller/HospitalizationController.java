// src/main/java/com/czy/controller/HospitalizationController.java
package com.czy.controller;

import com.czy.pojo.Hospitalization;
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
import java.util.List;

@WebServlet("/hospitalizations")
public class HospitalizationController extends HttpServlet {
    private final HospitalizationService service = new HospitalizationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user")==null) {
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
            return;
        }
        Patient p = (Patient)session.getAttribute("user");
        List<Hospitalization> list = service.getByPatient(p.getPatientId());
        req.setAttribute("hospitalizations", list);
        req.getRequestDispatcher("/patient/hospitalizations.jsp")
                .forward(req, resp);
    }
}
