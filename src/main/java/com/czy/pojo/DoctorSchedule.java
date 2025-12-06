package com.czy.pojo;

import java.util.Date;

public class DoctorSchedule {
    // 1. 核心字段：确保 departmentId 存在且类型正确
    private Integer scheduleId;
    private Integer doctorId;
    private Integer departmentId;
    private Date date; //
    private String shiftTime;
    private Integer isAvailable;

    // 2. 关联对象（用于回显姓名）
    private Doctors doctor;
    private Departments department;

    private String doctorName;
    private String deptName;
    private String edit_date;

    private Integer deptTD;
    public DoctorSchedule() {
        edit_date = edit_date;
    }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    public Integer getScheduleId() { return scheduleId; }
    public void setScheduleId(Integer scheduleId) { this.scheduleId = scheduleId; }

    public Integer getDoctorId() { return doctorId; }
    public void setDoctorId(Integer doctorId) { this.doctorId = doctorId; }

    // 核心：departmentId 的 Getter（MyBatis 必须通过它获取值）
    public Integer getDepartmentId() { return departmentId; }
    public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getShiftTime() { return shiftTime; }
    public void setShiftTime(String shiftTime) { this.shiftTime = shiftTime; }

    public Integer getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Integer isAvailable) { this.isAvailable = isAvailable; }

    // 关联对象的 Getter & Setter（用于回显医生/科室名称）
    public Doctors getDoctor() { return doctor; }
    public void setDoctor(Doctors doctor) { this.doctor = doctor; }

    public Departments getDepartment() { return department; }
    public void setDepartment(Departments department) { this.department = department; }

    public void setDeptId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    public Integer getDeptId() {
        return departmentId;
    }


}