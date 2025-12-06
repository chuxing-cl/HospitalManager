<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="css/pintuer.css">
    <title>排班管理</title>
    <style>
        /* 添加分页样式 */
        .pagination {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }
        .pagination a, .pagination span {
            display: inline-block;
            padding: 5px 10px;
            margin: 0 2px;
            border: 1px solid #ddd;
            text-decoration: none;
            color: #333;
        }
        .pagination .active {
            background-color: #007BFF;
            color: white;
            border-color: #007BFF;
        }

        /* 表格样式优化 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;      /* 水平居中 */
            vertical-align: middle;  /* 垂直居中 */
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        /* 表单和按钮样式优化 */
        .form-inline {
            margin-bottom: 15px;
        }
        .form-inline select, .form-inline input {
            margin-right: 10px;
        }
        .button {
            margin-right: 5px;
        }
    </style>
</head>
<body>
<div class="admin">
    <h3>排班列表</h3>
    <!-- 筛选表单 -->
    <form action="manage/schedule" method="get" class="form-inline">
        <input type="hidden" name="action" value="list">
        <input type="date" name="date" class="input">
        <button type="submit" class="button bg-blue">筛选</button>
        <a href="${pageContext.request.contextPath}/manage/schedule?action=addForm" class="button bg-green">新增排班</a>
    </form>

    <!-- 排班表格 -->
    <table class="table table-bordered">
        <!-- 表头：固定列标题 -->
        <thead>
        <tr>
            <th style="text-align: center;">排班ID</th>
            <th style="text-align: center;">医生</th>
            <th style="text-align: center;">科室</th>
            <th style="text-align: center;">日期</th>
            <th style="text-align: center;">班次</th>
            <th style="text-align: center;">操作</th>
        </tr>
        </thead>
        <!-- 数据行：循环渲染 -->
        <tbody>
        <c:forEach items="${pageInfo.list}" var="s">
            <tr>
                <td>${s.scheduleId}</td>
                <td>${s.doctorName}</td>
                <td>${s.deptName}</td>
                <td><fmt:formatDate value="${s.date}" pattern="yyyy-MM-dd" /></td>
                <td>${s.shiftTime}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/manage/schedule?action=form&scheduleId=${s.scheduleId}" class="button border-main">编辑</a>
                    <a href="${pageContext.request.contextPath}/manage/schedule?action=delete&scheduleId=${s.scheduleId}" onclick="return confirm('确认删除？')" class="button border-red">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 显示总条目数 -->
    <p class="text-center">总条目数: ${pageInfo.total}</p>

    <!-- 分页导航 -->
    <div class="pagination">
        <c:if test="${pageInfo.hasPreviousPage}">
            <a href="${pageContext.request.contextPath}/manage/schedule?action=list&pageNum=${pageInfo.prePage}&doctorId=${param.doctorId}&deptId=${param.deptId}&date=${param.date}">上一页</a>
        </c:if>
        <c:forEach begin="1" end="${pageInfo.pages}" var="i">
            <c:choose>
                <c:when test="${i == pageInfo.pageNum}">
                    <span class="active">${i}</span>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/manage/schedule?action=list&pageNum=${i}&doctorId=${param.doctorId}&deptId=${param.deptId}&date=${param.date}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${pageInfo.hasNextPage}">
            <a href="${pageContext.request.contextPath}/manage/schedule?action=list&pageNum=${pageInfo.nextPage}&doctorId=${param.doctorId}&deptId=${param.deptId}&date=${param.date}">下一页</a>
        </c:if>
    </div>
</div>
</body>
</html>