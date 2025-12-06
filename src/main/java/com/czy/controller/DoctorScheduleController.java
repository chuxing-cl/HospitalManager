package com.czy.controller;

import com.czy.pojo.Departments;
import com.czy.pojo.DoctorSchedule;
import com.czy.pojo.Doctors;
import com.czy.service.DepartmentsService;
import com.czy.service.DoctorScheduleService;
import com.czy.service.DoctorsService;
import com.czy.service.impl.DepartmentsServiceImpl;
import com.czy.service.impl.DoctorScheduleServiceImpl;
import com.czy.service.impl.DoctorsServiceImpl;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/manage/schedule")
public class DoctorScheduleController extends HttpServlet {

    private DoctorScheduleService scheduleService = new DoctorScheduleServiceImpl();
    private DoctorsService doctorService = new DoctorsServiceImpl();
    private DepartmentsService departmentService = new DepartmentsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        if ("list".equals(action)) {
            // 获取分页参数
            Integer pageNum = getIntParam(req, "pageNum");
            if (pageNum == null) {
                pageNum = 1;
            }
            Integer pageSize = getIntParam(req, "pageSize");
            if (pageSize == null) {
                pageSize = 10;
            }

            Integer doctorId = getIntParam(req, "doctorId");
            Integer deptId = getIntParam(req, "deptId");
            Date date = getDateParam(req, "date");

            // 调用分页查询方法
            PageInfo<DoctorSchedule> pageInfo = scheduleService.findByPage(pageNum, pageSize, doctorId, deptId, date);
            req.setAttribute("pageInfo", pageInfo);
            req.getRequestDispatcher("/manage/scheduleList.jsp").forward(req, resp);
        }
        else if ("form".equals(action)) {
            Integer scheduleId = getIntParam(req, "scheduleId");
            if (scheduleId != null) {
                // 查询完整的排班信息（包括医生和科室）
                DoctorSchedule schedule = scheduleService.findById(scheduleId);
                System.out.println("编辑排班 - ID: " + scheduleId);
                System.out.println("关联医生ID: " + schedule.getDoctorId());
                System.out.println("关联科室ID: " + schedule.getDepartmentId());
                req.setAttribute("schedule", schedule);
            }
            req.getRequestDispatcher("/manage/scheduleForm.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {

            Integer scheduleId = getIntParam(req, "scheduleId");
            if (scheduleId != null) {
                scheduleService.delete(scheduleId);
            }
            resp.sendRedirect(req.getContextPath() + "/manage/schedule?action=list");
        } else if ("addForm".equals(action)) {
            // 查询医生和科室列表（用于下拉框）
            List<Doctors> doctors = doctorService.AllDoctorNames();
            List<Departments> departments = departmentService.AllDepartmentsNames();
            req.setAttribute("doctors", doctors);
            req.setAttribute("departments", departments);

            // 转发到/manage/路径下的新增界面
            req.getRequestDispatcher("/manage/ScheduleAdd.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 解决中文乱码
        String action = req.getParameter("action");
        if ("saveAdd".equals(action)) {
            // 1. 接收表单参数并封装对象
            DoctorSchedule schedule = new DoctorSchedule();
            // 新增时scheduleId为空（数据库自增）
            schedule.setDoctorId(getIntParam(req, "doctorId"));  // 对应表单name="doctorId"
            schedule.setDeptId(getIntParam(req, "deptId"));      // 对应表单name="deptId"（关键修正）
            schedule.setDate(parseDate(req.getParameter("date"))); // 解析日期
            schedule.setShiftTime(req.getParameter("shiftTime"));  // 对应表单name="shiftTime"

            // 2. 调用Service层新增方法（已有实现）
            try {
                scheduleService.add(schedule);
                // 3. 保存成功：重定向到列表页（带成功提示）
                resp.sendRedirect(req.getContextPath() + "/manage/schedule?action=list&msg=addSuccess");
            } catch (Exception e) {
                // 4. 保存失败：跳转回新增页并显示错误
                req.setAttribute("errorMsg", "保存失败：" + e.getMessage());
                // 回显表单数据和下拉框数据
                req.setAttribute("schedule", schedule);
                req.setAttribute("doctors", doctorService.AllDoctorNames());
                req.setAttribute("departments", departmentService.AllDepartmentsNames());
                req.getRequestDispatcher("/manage/ScheduleAdd.jsp").forward(req, resp);
            }
        }

        else if ("save".equals(action)) {
            // 保存排班
            DoctorSchedule schedule = new DoctorSchedule();
            schedule.setScheduleId(getIntParam(req, "scheduleId"));
            schedule.setDoctorId(getIntParam(req, "doctorId"));
            schedule.setDeptId(getIntParam(req, "departmentId"));
            schedule.setDate(parseDate(req.getParameter("date")));
            schedule.setShiftTime(req.getParameter("shiftTime"));

            // 打印接收到的参数，便于调试
            System.out.println("保存排班 - 参数:");
            System.out.println("ID: " + schedule.getScheduleId());
            System.out.println("医生ID: " + schedule.getDoctorId());
            System.out.println("科室ID: " + schedule.getDepartmentId());
            System.out.println("日期: " + schedule.getDate());
            System.out.println("班次: " + schedule.getShiftTime());

            // 保存数据
            if (schedule.getScheduleId() == null) {
                // 新增
                scheduleService.add(schedule);
                System.out.println("新增排班成功");
            } else {
                // 修改
                scheduleService.update(schedule);
                System.out.println("更新排班成功");
            }
            resp.sendRedirect(req.getContextPath() + "/manage/schedule?action=list");
        }
    }

    // 辅助方法：获取整数参数（为空时返回null，避免空指针）
    private Integer getIntParam(HttpServletRequest req, String paramName) {
        String value = req.getParameter(paramName);
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.valueOf(value.trim());
        } catch (NumberFormatException e) {
            System.err.println("参数转换失败：" + paramName + "=" + value);
            return null;
        }
    }

    // 辅助方法：日期转换（兼容空值和格式错误）
    private Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr.trim());
        } catch (ParseException e) {
            System.err.println("日期格式错误：" + dateStr + "（正确格式：yyyy-MM-dd）");
            return null;
        }
    }

    // 辅助方法：获取日期参数
    private Date getDateParam(HttpServletRequest req, String paramName) {
        return parseDate(req.getParameter(paramName));
    }
}