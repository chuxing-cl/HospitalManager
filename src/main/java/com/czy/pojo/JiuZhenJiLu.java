package com.czy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author 陈政缘
 * Date  2025/7/16 下午8:38
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JiuZhenJiLu {
    private int consultationId;
    private int patientId;
    private int doctorId;
    private String consultationTime;
    private int isHospitalRegistered;
    private int isHospitalized;
    private String medicalAdviceCase;

    private Doctors doctor;
    private Patient patient;
}
