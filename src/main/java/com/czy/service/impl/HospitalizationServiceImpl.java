// src/main/java/com/czy/service/impl/HospitalizationServiceImpl.java
package com.czy.service.impl;

import com.czy.mapper.HospitalizationMapper;
import com.czy.mapper.PatientMapper;
import com.czy.pojo.Hospitalization;
import com.czy.service.HospitalizationService;
import com.czy.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalizationServiceImpl implements HospitalizationService {
    @Override
    public List<Hospitalization> getByPatient(int patientId) {
        SqlSession session = SqlSessionUtil.getSession();
        try  {
            return session.getMapper(HospitalizationMapper.class)
                    .selectByPatientId(patientId);
        }finally {
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public Map<String, Object> pay(int hospitalizationId, int patientId) {
        SqlSession session = SqlSessionUtil.getSession();
        try  {
            HospitalizationMapper hm = session.getMapper(HospitalizationMapper.class);
            PatientMapper pm = session.getMapper(PatientMapper.class);

            // 1) 查出这次住院的 cost
            int cost = hm.selectById(hospitalizationId).getCost();

            // 2) 扣余额
            int updated = pm.deductBalance(patientId, cost);
            if (updated == 0) {
                // 余额不足
                session.rollback();
                Map<String,Object> r = new HashMap<>();
                r.put("success", false);
                r.put("message", "余额不足");
                return r;
            }

            // 3) 标记已缴费
            hm.markPaid(hospitalizationId);

            // 4) 提交事务
            session.commit();

            // 5) 再查一次最新余额
            BigDecimal newBal = pm.selectById(patientId).getBalance();

            Map<String,Object> r = new HashMap<>();
            r.put("success", true);
            r.put("newBalance", newBal);
            return r;
        }finally {
            SqlSessionUtil.closeSession();
        }
    }
}
