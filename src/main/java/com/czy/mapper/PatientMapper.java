// src/main/java/com/czy/mapper/PatientMapper.java
package com.czy.mapper;

import com.czy.pojo.Patient;
import org.apache.ibatis.annotations.Param;

public interface PatientMapper {
    Patient selectByUsername(@Param("username") String username);
    Patient selectById(@Param("patientId") int patientId);
    int update(Patient patient);

    /** 扣减余额，只有余额足够时才更新成功 */
    int deductBalance(@Param("patientId") int patientId,
                      @Param("amount") int amount);
    int insert(Patient patient);
}
