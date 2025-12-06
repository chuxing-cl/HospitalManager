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
    <title>公告列表</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 公告列表</strong></div>
    <div class="padding border-bottom">
        <a class="button border-blue" href="${pageContext.request.contextPath}/manage/addAnnouncement.jsp">
            <span class="icon-plus-square-o"></span> 添加公告</a>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="10%">编号</th>
            <th width="30%">标题</th>
            <th width="40%">内容</th>
            <th width="20%">操作</th>
        </tr>

        <c:forEach items="${pageInfo.list}" var="announcement">
            <tr>
                <td>${announcement.announcementId}</td>
                <td>${announcement.title}</td>
                <td>${announcement.content}</td>
                <td>
                    <div class="button-group">
                        <!-- 修改按钮和删除按钮改为相同宽度 -->
                        <a type="button" class="button border-main button-small" href="${pageContext.request.contextPath}/manage/selectAnnouncementById.do?announcementId=${announcement.announcementId}">
                            <span class="icon-edit"></span>修改</a>
                        <a class="button border-red button-small" href="${pageContext.request.contextPath}/manage/delAnnouncementById.do?announcementId=${announcement.announcementId}"
                           onclick="javascript:return window.confirm('确定删除吗？')"><span class="icon-trash-o"></span>删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="4"><div class="pagelist">
                <span class="current">总记录数${pageInfo.total}</span>
                <a href="${pageContext.request.contextPath}/manage/announcementSearch.do?page=1">首页</a>
                <c:choose>
                    <c:when test="${1 == pageInfo.pageNum}">
                        <a href="javascript:void(0);" class="disabled">上一页</a>
                    </c:when>
                    <c:otherwise>
                        <c:set var="i" value="${pageInfo.pageNum - 1}"/>
                        <a href="${pageContext.request.contextPath}/manage/announcementSearch.do?page=${i}">上一页</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                    <c:choose>
                        <c:when test="i == pageInfo.pageNum">
                            <span class="current">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/manage/announcementSearch.do?page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${pageInfo.pages == pageInfo.pageNum}">
                        <a href="javascript:void(0);" class="disabled">下一页</a>
                    </c:when>
                    <c:otherwise>
                        <c:set var="i" value="${pageInfo.pageNum + 1}"/>
                        <a href="${pageContext.request.contextPath}/manage/announcementSearch.do?page=${i}">下一页</a>
                    </c:otherwise>
                </c:choose>
                <a href="${pageContext.request.contextPath}/manage/announcementSearch.do?page=${pageInfo.pages}">尾页</a>
            </div></td>
        </tr>
    </table>
</div>
</body>
</html>