package com.czy.mapper;

import com.czy.pojo.DoctorSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface DoctorScheduleMapper {
    // 查询所有排班
    List<DoctorSchedule> findAll();

    // 按条件查询（医生ID、科室ID、日期）
    List<DoctorSchedule> findByCondition(
            @Param("doctorId") Integer doctorId,
            @Param("deptId") Integer deptId,
            @Param("date") Date date
    );

    // 新增排班
    int add(DoctorSchedule schedule);

    // 修改排班
    int update(DoctorSchedule schedule);

    // 删除排班
    int delete(Integer scheduleId);

    DoctorSchedule findById(@Param("scheduleId") Integer scheduleId);

    DoctorSchedule Edit_findById(Integer schedule_id);
}