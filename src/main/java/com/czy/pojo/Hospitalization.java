package com.czy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Stack;

/**
 * @author 陈政缘
 * Date  2025/7/16 下午10:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Hospitalization {
    private int hospitalizationId;
    private int patientId;
    private String roomNumber;
    private int cost;
    private String paymentStatus;
    private int isInsured;
    private String hospitalizationStatus;

    private Patient patient;
}
