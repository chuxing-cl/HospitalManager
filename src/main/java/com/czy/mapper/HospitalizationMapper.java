package com.czy.mapper;

import com.czy.pojo.Hospitalization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HospitalizationMapper {
    /** 查询指定患者的住院记录 */
    List<Hospitalization> selectByPatientId(@Param("patientId") int patientId);

    /** 根据住院ID查询单条记录，用于获取费用 */
    Hospitalization selectById(@Param("hospitalizationId") int hospitalizationId);

    /** 标记已缴费 */
    int markPaid(@Param("hospitalizationId") int hospitalizationId);
}
