<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>排班管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
</head>
<body>
<div class="admin">
    <h3>排班管理</h3>

    <!-- 筛选表单 -->
    <form action="${pageContext.request.contextPath}/manage/schedule" method="get" class="form-inline">
        <input type="hidden" name="action" value="list">

        <!-- 科室下拉框（从departments表查询） -->
        <select name="deptId" class="input">
            <option value="">全部科室</option>
            <c:forEach items="${departments}" var="dept">
                <option value="${dept.departmentId}">${dept.departmentName}</option>
            </c:forEach>
        </select>

        <!-- 日期选择 -->
        <input type="date" name="date" class="input" placeholder="选择日期">

        <button type="submit" class="button bg-blue">筛选</button>
        <a href="${pageContext.request.contextPath}/manage/schedule?action=toAdd" class="button bg-green">新增排班</a>
    </form>

    <!-- 排班表格 -->
    <table class="table table-border">
        <thead>
        <tr>
            <th>排班ID</th>
            <th>医生</th>
            <th>科室</th>
            <th>排班日期</th>
            <th>时间段</th>
            <th>可排班状态</th>
            <th>已预约/最大数量</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${schedules}" var="s">
            <tr>
                <td>${s.scheduleId}</td>
                <td>${s.doctor.name}</td> <!-- 关联医生姓名 -->
                <td>${s.department.departmentName}</td> <!-- 关联科室名称 -->
                <td><fmt:formatDate value="${s.date}" pattern="yyyy-MM-dd"/></td>
                <td>${s.shiftTime}</td>
                <td>
                    <c:if test="${s.isAvailable == 0}"><span class="label label-green">可排班</span></c:if>
                    <c:if test="${s.isAvailable == 1}"><span class="label label-red">不可排班</span></c:if>
                </td>
                <td>${s.visitCount}/${s.sumCount}</td>
                <td>
                    <!-- 编辑按钮（跳编辑页） -->
                    <a href="${pageContext.request.contextPath}/manage/schedule?action=toEdit&scheduleId=${s.scheduleId}" class="button button-little">编辑</a>
                    <!-- 删除按钮（调用删除接口） -->
                    <a href="${pageContext.request.contextPath}/manage/schedule?action=delete&scheduleId=${s.scheduleId}"
                       class="button button-little bg-red"
                       onclick="return confirm('确定删除该排班吗？')">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>