package com.czy.mapper;

import com.czy.pojo.Consultation;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

// ConsultationMapper.java
public interface ConsultationMapper {
    List<Consultation> selectByPatientId(@Param("patientId") int patientId);

    int selectPriceById(@Param("consultationId") int consultationId);

    /** 标记处方已缴费 */
    int markPaid(@Param("consultationId") int consultationId);
}

