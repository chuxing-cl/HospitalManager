package com.czy.service.impl;

import com.czy.mapper.PatientMapper;
import com.czy.pojo.Patient;
import com.czy.service.PatientService;
import com.czy.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class PatientServiceImpl implements PatientService {

    @Override
    public Patient login(String username, String password) {
        SqlSession session = SqlSessionUtil.getSession();
        try {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            Patient patient = mapper.selectByUsername(username);
            if (patient != null && patient.getPassword().equals(password)) {
                return patient;
            }
            return null;
        } finally {
            // 关闭并从 ThreadLocal 清理
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public Patient getById(int patientId) {
        SqlSession session = SqlSessionUtil.getSession();
        try {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.selectById(patientId);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public boolean update(Patient patient) {
        SqlSession session = SqlSessionUtil.getSession();
        try {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            int rows = mapper.update(patient);
            session.commit();
            return rows > 0;
        } catch (Exception e) {
            session.rollback();
            throw new RuntimeException("更新患者信息失败", e);
        } finally {
            SqlSessionUtil.closeSession();
        }

    }
    @Override
    public boolean register(Patient patient) {
        try (SqlSession session = SqlSessionUtil.getSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            int rows = mapper.insert(patient);
            session.commit();
            return rows > 0;
        }
    }

}
