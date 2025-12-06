package com.czy.service;

import com.czy.pojo.DoctorSchedule;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface DoctorScheduleService {
    List<DoctorSchedule> findAll();
    List<DoctorSchedule> findByCondition(Integer doctorId, Integer deptId, Date date); // 使用Date类
    void add(DoctorSchedule schedule);
    void update(DoctorSchedule schedule);
    void delete(Integer scheduleId);
    DoctorSchedule findById(Integer scheduleId);

    // 分页查询排班列表（可选，根据需求添加）
    PageInfo<DoctorSchedule> findByPage(Integer pageNum, Integer pageSize, Integer doctorId, Integer deptId, Date date);

    DoctorSchedule getScheduleById(Integer schedule_id);
}