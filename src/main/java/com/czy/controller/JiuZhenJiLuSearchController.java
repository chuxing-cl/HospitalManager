package com.czy.controller;/**
 * @author 陈政缘
 * Date  2025/7/16 下午8:18
 */

import com.czy.pojo.JiuZhenJiLu;
import com.czy.service.JiuZhenJiLuService;
import com.czy.service.impl.JiuZhenJiLuServiceImpl;
import com.czy.vo.DoctorSearchVo;
import com.czy.vo.JiuZhenSearchVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/manage/JiuZhenJiLuSearch.do")
public class JiuZhenJiLuSearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处于manage目录下，已经由过滤器统一设置请求的编码格式
        // 接收请求中的页码
        Integer pageSize = 10;
        Integer pageNum = 1;
        String page =request.getParameter("page");
        if(page!=null && page.length()>0) {
            pageNum = Integer.parseInt(page);
        }
        // 患者和医生姓名
        String patientName = request.getParameter("patientName");
        String doctorName = request.getParameter("doctorName");
        JiuZhenSearchVo searchVo = new JiuZhenSearchVo(patientName, doctorName);
        //调用Service中分页查询的方法
        JiuZhenJiLuService jiLuService = new JiuZhenJiLuServiceImpl();
        PageInfo<JiuZhenJiLu> pageInfo = jiLuService.getJiuZhenJiLu(searchVo, pageNum, pageSize);
        //使用request对象,存储pageInfo值
        request.setAttribute("pageInfo", pageInfo);
        //页面转发到科室列表页面
        request.getRequestDispatcher("/manage/jiuzhenjiluList.jsp").forward(request, response);
    }
}