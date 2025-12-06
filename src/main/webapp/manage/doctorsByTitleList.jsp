<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>拥有该职称的医生列表</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 拥有该职称的医生列表</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">编号</th>
            <th width="20%">医生姓名</th>
            <th width="20%">工号</th>
            <th width="20%">电话</th>
            <th width="20%">邮箱</th>
            <th width="15%">操作</th>
        </tr>

        <c:forEach items="${doctorList}" var="doctor">
            <tr>
                <td>${doctor.doctorId}</td>
                <td>${doctor.name}</td>
                <td>${doctor.jobNumber}</td>
                <td>${doctor.phone}</td>
                <td>${doctor.email}</td>
                <td>
                    <div class="button-group">
                        <!-- 可以添加更多操作按钮 -->
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>