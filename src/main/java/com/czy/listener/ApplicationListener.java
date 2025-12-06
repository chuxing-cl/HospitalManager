package com.czy.listener;/**
 * @author 陈政缘
 * Date  2025/7/9 下午2:43
 */

import com.czy.pojo.Departments;
import com.czy.pojo.ProfessionalTitles;
import com.czy.service.DepartmentsService;
import com.czy.service.ProfessionalTitlesService;
import com.czy.service.impl.DepartmentsServiceImpl;
import com.czy.service.impl.ProfessionalTitlesServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.util.List;

/**
 * 监听作用域生命周期的监听器：Servlet上下文监听、Http会话监听、Servlet请求监听
 * ServletContextListener, HttpSessionListener,ServletRequestListener,
 * 监听作用域属性变化的监听器：
 *  ServletContextAttributeListener, HttpSessionAttributeListener,ServletRequestAttributeListener
 *  --监听作用域存值setAttribute(String name,Object obj),取值getAttribute(String name),改变值setAttribute(String name,Object obj)
 *  删除值:remove(String name)
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    public ApplicationListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {   // 创建Application时的监听方法
        // 获取application对象
        ServletContext application = sce.getServletContext();
        // 创建科室Service和职称Service对象
        DepartmentsService departmentsService = new DepartmentsServiceImpl();
        ProfessionalTitlesService titlesService = new ProfessionalTitlesServiceImpl();
        // 分别调用查询方法
        List<Departments> departmentsList = departmentsService.getDepartmentBySecond();
        List<ProfessionalTitles> titlesList = titlesService.selectAll();
        // 使用application存储科室与职称的信息
        application.setAttribute("departmentsList", departmentsList);
        application.setAttribute("titlesList", titlesList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { // 销毁Application时的监听方法
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

}