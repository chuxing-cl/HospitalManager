// src/main/java/com/czy/service/HospitalizationService.java
package com.czy.service;

import com.czy.pojo.Hospitalization;

import java.util.List;
import java.util.Map;

public interface HospitalizationService {
    List<Hospitalization> getByPatient(int patientId);

    /**
     * 给定住院ID和患者ID执行扣费并标记已缴费，
     * 返回 Map 包含：
     *  success(boolean)、newBalance(BigDecimal) 或 message(String)
     */
    Map<String, Object> pay(int hospitalizationId, int patientId);
}
