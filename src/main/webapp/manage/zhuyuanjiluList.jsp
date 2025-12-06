<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 2025/7/16
  Time: 下午10:21
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
    <div class="panel-head"><strong class="icon-reorder"> 住院记录</strong></div>
    <div class="padding border-bottom">
        <ul class="search" style="padding-left:10px;">
            <button onclick="exportToCSV()" class="button border-green icon-file-excel"> 导出当前页</button>
            <li> <a class="button border-red" href="${pageContext.request.contextPath}/manage/delZY.do"
                    onclick="javascript:return window.confirm('确定全部删除吗？')"><span class="icon-trash-o"></span>全部删除
            </a></li>
        </ul>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="10%">编号</th>
            <th width="10%">患者姓名</th>
            <th width="10%">病房号</th>
            <th width="10%">住院费用</th>
            <th width="10%">支付状态</th>
            <th width="10%">有无保险</th>
            <th width="10%">住院状态</th>
        </tr>

        <c:forEach items="${pageInfo.list}" var="ZYJL">
            <tr>
                <td>${ZYJL.hospitalizationId}</td>
                <td>${ZYJL.patient.pname}</td>
                <td>${ZYJL.roomNumber}</td>
                <td>${ZYJL.cost}</td>
                <td>${ZYJL.paymentStatus}</td>
                <td>${ZYJL.isInsured == 1 ? '有' : '无'}</td>
                <td>${ZYJL.hospitalizationStatus}</td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="4"><div class="pagelist">
                <span class="current">总记录数${pageInfo.total}</span>
                <a href="${pageContext.request.contextPath}/manage/ZhuYuanJiLuSearch.do?page=1">首页</a>
                <c:choose>
                    <c:when test="${1 == pageInfo.pageNum}">
                        <a href="javascript:void(0);" class="disabled">上一页</a>
                    </c:when>
                    <c:otherwise>
                        <c:set var="i" value="${pageInfo.pageNum - 1}"/>
                        <a href="${pageContext.request.contextPath}/manage/ZhuYuanJiLuSearch.do?page=${i}">上一页</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                    <c:choose>
                        <c:when test="i == pageInfo.pageNum">
                            <span class="current">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/manage/ZhuYuanJiLuSearch.do?page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${pageInfo.pages == pageInfo.pageNum}">
                        <a href="javascript:void(0);" class="disabled">下一页</a>
                    </c:when>
                    <c:otherwise>
                        <c:set var="i" value="${pageInfo.pageNum + 1}"/>
                        <a href="${pageContext.request.contextPath}/manage/ZhuYuanJiLuSearch.do?page=${i}">下一页</a>
                    </c:otherwise>
                </c:choose>
                <a href="${pageContext.request.contextPath}/manage/ZhuYuanJiLuSearch.do?page=${pageInfo.pages}">尾页</a>
            </div></td>
        </tr>
    </table>
</div>
<script>
    function exportToCSV() {
        // 获取表格数据
        const rows = document.querySelectorAll("table tr");
        let csvContent = "数据编号,患者姓名,病房号,住院费用,支付状态,有无保险,住院状态\n";

        // 跳过表头行和分页行
        for (let i = 1; i < rows.length - 1; i++) {
            const cells = rows[i].querySelectorAll("td");
            let row = [];
            cells.forEach(cell => {
                row.push(cell.textContent.trim());
            });
            csvContent += row.join(",") + "\n";
        }

        // 创建下载链接
        const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
        const url = URL.createObjectURL(blob);
        const link = document.createElement("a");
        link.setAttribute("href", url);
        link.setAttribute("download", "住院记录.csv");
        link.style.visibility = 'hidden';
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    }
</script>
</body></html>