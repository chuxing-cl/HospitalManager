// src/main/java/com/czy/service/impl/ConsultationServiceImpl.java
package com.czy.service.impl;

import com.czy.mapper.ConsultationMapper;
import com.czy.mapper.PatientMapper;
import com.czy.pojo.Consultation;
import com.czy.service.ConsultationService;
import com.czy.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.List;

// ConsultationServiceImpl.java
public class ConsultationServiceImpl implements ConsultationService {
    @Override
    public List<Consultation> getByPatient(int patientId) {
        try (SqlSession session = SqlSessionUtil.getSession()) {
            return session.getMapper(ConsultationMapper.class)
                    .selectByPatientId(patientId);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public int getPrice(int consultationId) {
        try (SqlSession session = SqlSessionUtil.getSession()) {
            return session.getMapper(ConsultationMapper.class)
                    .selectPriceById(consultationId);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public boolean pay(int consultationId, int patientId, int amount) {
        SqlSession session = SqlSessionUtil.getSession();
        try {
            // 先扣患者余额
            int r1 = session.getMapper(PatientMapper.class)
                    .deductBalance(patientId, amount);
            if (r1 <= 0) {
                session.rollback();
                return false;
            }
            // 再标记已缴费
            int r2 = session.getMapper(ConsultationMapper.class)
                    .markPaid(consultationId);
            if (r2 <= 0) {
                session.rollback();
                return false;
            }
            session.commit();
            return true;
        } finally {
            SqlSessionUtil.closeSession();
        }
    }
}


