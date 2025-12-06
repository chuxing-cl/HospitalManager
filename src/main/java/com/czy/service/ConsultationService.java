package com.czy.service;

import com.czy.pojo.Consultation;

import java.math.BigDecimal;
import java.util.List;

public interface ConsultationService {
    List<Consultation> getByPatient(int patientId);
    int getPrice(int consultationId);
    boolean pay(int consultationId, int patientId, int amount);
}
