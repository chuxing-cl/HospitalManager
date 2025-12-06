package com.czy.service.impl;

import com.czy.mapper.DoctorScheduleMapper;
import com.czy.pojo.DoctorSchedule;
import com.czy.service.DoctorScheduleService;
import com.czy.util.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/16
 */
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    @Override
    public List<DoctorSchedule> findAll() {
        SqlSession session = null;
        try {
            session = SqlSessionUtil.getSession();
            DoctorScheduleMapper mapper = SqlSessionUtil.getMapper(DoctorScheduleMapper.class);
            return mapper.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public List<DoctorSchedule> findByCondition(Integer doctorId, Integer deptId, Date date) {
        SqlSession session = null;
        try {
            session = SqlSessionUtil.getSession();
            DoctorScheduleMapper mapper = SqlSessionUtil.getMapper(DoctorScheduleMapper.class);
            return mapper.findByCondition(doctorId, deptId, date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public void add(DoctorSchedule schedule) {
        SqlSession session = null;
        try {
            session = SqlSessionUtil.getSession();
            DoctorScheduleMapper mapper = SqlSessionUtil.getMapper(DoctorScheduleMapper.class);
            mapper.add(schedule);
            session.commit(); // 提交事务
        } catch (Exception e) {
            if (session != null) {
                session.rollback(); // 回滚事务
            }
            throw new RuntimeException(e);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public void update(DoctorSchedule schedule) {
        SqlSession session = null;
        try {
            session = SqlSessionUtil.getSession();
            DoctorScheduleMapper mapper = SqlSessionUtil.getMapper(DoctorScheduleMapper.class);
            mapper.update(schedule);
            session.commit(); // 提交事务
        } catch (Exception e) {
            if (session != null) {
                session.rollback(); // 回滚事务
            }
            throw new RuntimeException(e);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public void delete(Integer scheduleId) {
        SqlSession session = null;
        try {
            session = SqlSessionUtil.getSession();
            DoctorScheduleMapper mapper = SqlSessionUtil.getMapper(DoctorScheduleMapper.class);
            mapper.delete(scheduleId);
            session.commit(); // 提交事务
        } catch (Exception e) {
            if (session != null) {
                session.rollback(); // 回滚事务
            }
            throw new RuntimeException(e);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public DoctorSchedule findById(Integer scheduleId) {
        SqlSession session = null;
        try {
            session = SqlSessionUtil.getSession();
            DoctorScheduleMapper mapper = SqlSessionUtil.getMapper(DoctorScheduleMapper.class);
            DoctorSchedule schedule = mapper.Edit_findById(scheduleId);
//            System.out.println("医生姓名（直接访问）：" + schedule.getDoctor().getName());
//            System.out.println("科室名称（直接访问）：" + schedule.getDepartment().getDepartmentName());
            System.out.println("查询到的医生信息：" + (schedule.getDoctor() != null ? schedule.getDoctor().getName() : "null"));
            System.out.println("查询到的科室信息：" + (schedule.getDepartment() != null ? schedule.getDepartment().getDepartmentName() : "null"));
            return schedule;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }

    // 分页查询排班列表（可选，根据需求添加）
    @Override
    public PageInfo<DoctorSchedule> findByPage(Integer pageNum, Integer pageSize, Integer doctorId, Integer deptId, Date date) {
        SqlSession session = null;
        try {
            session = SqlSessionUtil.getSession();
            DoctorScheduleMapper mapper = SqlSessionUtil.getMapper(DoctorScheduleMapper.class);

            // 设置分页参数
            PageHelper.startPage(pageNum, pageSize);

            // 查询数据
            List<DoctorSchedule> list = mapper.findByCondition(doctorId, deptId, date);

            // 封装分页信息
            return new PageInfo<>(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }
    @Override
    public DoctorSchedule getScheduleById(Integer schedule_id) {
        try (SqlSession session = SqlSessionUtil.getSession()) {
            DoctorScheduleMapper mapper = session.getMapper(DoctorScheduleMapper.class);
            return mapper.findById(schedule_id);
        } catch (Exception e) {
            throw new RuntimeException("查询排班详情失败", e);
        }
    }


}