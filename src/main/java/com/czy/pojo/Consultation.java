package com.czy.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Consultation {
    private int consultationId;
    private int patientId;
    private Date consultationTime;
    private String medicalAdviceCase;    // 医嘱内容
    private boolean hospitalized;        // is_hospital_registered
    private BigDecimal price;            // 处方价格

    // --- getters & setters ---

    public int getConsultationId() {
        return consultationId;
    }
    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getConsultationTime() {
        return consultationTime;
    }
    public void setConsultationTime(Date consultationTime) {
        this.consultationTime = consultationTime;
    }

    public String getMedicalAdviceCase() {
        return medicalAdviceCase;
    }
    public void setMedicalAdviceCase(String medicalAdviceCase) {
        this.medicalAdviceCase = medicalAdviceCase;
    }

    public boolean isHospitalized() {
        return hospitalized;
    }
    public void setHospitalized(boolean hospitalized) {
        this.hospitalized = hospitalized;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
