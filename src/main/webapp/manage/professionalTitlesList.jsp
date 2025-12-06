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
    <title>网站信息</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong></div>
    <div class="padding border-bottom">
        <!-- 修改搜索按钮的链接 -->
        <a class="button border-blue" href="${pageContext.request.contextPath}/manage/viewDoctorsByTitle.do?titleId=1"><span class="icon-search"></span> 搜索</a>
        <a class="button border-blue" href="${pageContext.request.contextPath}/manage/addTitles.jsp">
            <span class="icon-plus-square-o"></span> 添加内容</a>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">编号</th>
            <th width="20%">职称名称</th>
            <th width="40%">职称描述</th>
            <th width="35%">操作</th>
        </tr>

        <c:forEach items="${pageInfo.list}" var="professionalTitles">
            <tr>
                <td>${professionalTitles.id}</td>
                <td>${professionalTitles.titleName}</td>
                <td>${professionalTitles.description}</td>
                <td>
                    <div class="button-group">
                        <!-- 修改查看按钮的链接 -->
                        <a type="button" class="button border-green" href="${pageContext.request.contextPath}/manage/viewDoctorsByTitle.do?titleId=${professionalTitles.id}">
                            <span class="icon-eye"></span>查看</a>
                        <a type="button" class="button border-main" href="${pageContext.request.contextPath}/manage/selectTitlesById.do?titleId=${professionalTitles.id}">
                            <span class="icon-edit"></span>修改</a>
                        <a class="button border-red" href="${pageContext.request.contextPath}/manage/delTitlesById.do?titlesId=${professionalTitles.id}"
                           onclick="javascript:return window.confirm('确定删除吗？')"><span class="icon-trash-o"></span>删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="4"><div class="pagelist">
                <span class="current">总记录数${pageInfo.total}</span>
                <a href="${pageContext.request.contextPath}/manage/titlesSearch.do?page=1">首页</a>
                <c:choose>
                    <c:when test="${1 == pageInfo.pageNum}">
                        <a href="javascript:void(0);" class="disabled">上一页</a>
                    </c:when>
                    <c:otherwise>
                        <c:set var="i" value="${pageInfo.pageNum - 1}"/>
                        <a href="${pageContext.request.contextPath}/manage/titlesSearch.do?page=${i}">上一页</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                    <c:choose>
                        <c:when test="i == pageInfo.pageNum">
                            <span class="current">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/manage/titlesSearch.do?page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${pageInfo.pages == pageInfo.pageNum}">
                        <a href="javascript:void(0);" class="disabled">下一页</a>
                    </c:when>
                    <c:otherwise>
                        <c:set var="i" value="${pageInfo.pageNum + 1}"/>
                        <a href="${pageContext.request.contextPath}/manage/titlesSearch.do?page=${i}">下一页</a>
                    </c:otherwise>
                </c:choose>
                <a href="${pageContext.request.contextPath}/manage/titlesSearch.do?page=${pageInfo.pages}">尾页</a>
            </div></td>
        </tr>
    </table>
</div>
</body>
</html>