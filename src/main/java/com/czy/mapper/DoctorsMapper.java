package com.czy.mapper;

import com.czy.pojo.Doctors;
import com.czy.vo.DoctorSearchVo;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/9 下午3:41
 */
public interface DoctorsMapper {
    /**
     * 按条件查询医生的信息
     * @param searchVo
     * @return
     */
    List<Doctors> selectDoctorAll(DoctorSearchVo searchVo);

    List<Doctors> AllDoctorNames();
}
