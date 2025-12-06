<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 2025/7/16
  Time: 下午8:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入JSTL标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%
        String contextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
    %>
    <base href="<%=basePath%>">

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>网站信息</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 就诊记录</strong></div>

    <form method="post" action="${pageContext.request.contextPath}/manage/JiuZhenJiLuSearch.do" id="listForm">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li><input type="text" placeholder="请输入患者姓名" value="${param.patientName}" name="patientName" class="input" style="width:150px; line-height:17px;display:inline-block" /></li>
                <li><input type="text" placeholder="请输入医生姓名" value="${param.doctorName}" name="doctorName" class="input" style="width:150px; line-height:17px;display:inline-block" /></li>
                <li><a href="javascript:submitFormData()" class="button border-main icon-search" > 搜索</a></li>
            </ul>
        </div>
    </form>
    <script>
        // 提交表单的事件，表单验证
        function submitFormData(){
            // 调用表单对象的提交方法：submit()
            document.getElementById("listForm").submit()
        }
    </script>

    <table class="table table-hover text-center">
        <tr>
            <th >编号</th>
            <th >患者姓名</th>
            <th >医生姓名</th>
            <th >就诊时间</th>
            <th >是否住院</th>
            <th >住院是否结束</th>
            <th width="30%">处方信息</th>
            <th >操作</th>
        </tr>

        <c:forEach items="${pageInfo.list}" var="JZJL">
            <tr>
                <td>${JZJL.consultationId}</td>
                <td>${JZJL.patient.pname}</td>
                <td>${JZJL.doctor.name}</td>
                <td>${JZJL.consultationTime}</td>
                <td>${JZJL.isHospitalRegistered == 1 ? '是' : '否'}</td>
                <td>${JZJL.isHospitalized == 1 ? '是' : '否'}</td>
                <td>${JZJL.medicalAdviceCase}</td>
                <td>
                    <div class="button-group">
                        <a class="button border-red" href="${pageContext.request.contextPath}/manage/delJZById.do?JZId=${JZJL.consultationId}"
                           onclick="javascript:return window.confirm('确定删除吗？')"><span class="icon-trash-o"></span>删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="4"><div class="pagelist">
                <span class="current">总记录数${pageInfo.total}</span>
                <a href="${pageContext.request.contextPath}/manage/JiuZhenJiLuSearch.do?page=1">首页</a>
                <c:choose>
                    <c:when test="${1 == pageInfo.pageNum}">
                        <a href="javascript:void(0);" class="disabled">上一页</a>
                    </c:when>
                    <c:otherwise>
                        <c:set var="i" value="${pageInfo.pageNum - 1}"/>
                        <a href="${pageContext.request.contextPath}/manage/JiuZhenJiLuSearch.do?page=${i}">上一页</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                    <c:choose>
                        <c:when test="i == pageInfo.pageNum">
                            <span class="current">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/manage/JiuZhenJiLuSearch.do?page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${pageInfo.pages == pageInfo.pageNum}">
                        <a href="javascript:void(0);" class="disabled">下一页</a>
                    </c:when>
                    <c:otherwise>
                        <c:set var="i" value="${pageInfo.pageNum + 1}"/>
                        <a href="${pageContext.request.contextPath}/manage/JiuZhenJiLuSearch.do?page=${i}">下一页</a>
                    </c:otherwise>
                </c:choose>
                <a href="${pageContext.request.contextPath}/manage/JiuZhenJiLuSearch.do?page=${pageInfo.pages}">尾页</a>
            </div></td>
        </tr>
    </table>
</div>
</body></html>
