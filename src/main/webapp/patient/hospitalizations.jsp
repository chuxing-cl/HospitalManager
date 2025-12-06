<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
    <title>我的住院信息及缴费</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
    <style>
        .hosp-table { width:100%; border-collapse:collapse; }
        .hosp-table th, .hosp-table td {
            text-align:center; vertical-align:middle; padding:8px;
        }
        .hosp-table col:nth-child(1){width:10%;}
        .hosp-table col:nth-child(2){width:15%;}
        .hosp-table col:nth-child(3){width:15%;}
        .hosp-table col:nth-child(4){width:15%;}
        .hosp-table col:nth-child(5){width:15%;}
        .hosp-table col:nth-child(6){width:15%;}
        .hosp-table col:nth-child(7){width:15%;}
    </style>
</head>
<body style="background-color:#f2f9fd; padding:20px;">
<div class="panel">
    <div class="panel-head"><strong>我的住院信息及缴费</strong></div>
    <div class="panel-body">
        <table class="table table-hover table-bordered table-striped hosp-table">
            <colgroup>
                <col/><col/><col/><col/><col/><col/><col/>
            </colgroup>
            <thead>
            <tr>
                <th>住院ID</th>
                <th>病房号</th>
                <th>住院状态</th>
                <th>是否医保</th>
                <th>支付状态</th>
                <th>费用(元)</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="h" items="${hospitalizations}">
                <tr>
                    <td><c:out value="${h.hospitalizationId}"/></td>
                    <td><c:out value="${h.roomNumber}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${h.hospitalizationStatus == 'ongoing'}">住院中</c:when>
                            <c:when test="${h.hospitalizationStatus == 'discharged'}">已出院</c:when>
                            <c:otherwise>未知</c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${h.isInsured==1 ? '是' : '否'}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${h.paymentStatus == 'paid'}">已缴费</c:when>
                            <c:otherwise>未缴费</c:otherwise>
                        </c:choose>
                    </td>

                    <td><fmt:formatNumber value="${h.cost}" pattern="#,##0.00"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${h.paymentStatus == 'paid'}">
                                无
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)"
                                   class="button button-mini bg-main"
                                   onclick="hospPay(${h.hospitalizationId}, '${h.cost}');">
                                    缴费
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
    const balance = '${sessionScope.user.balance}';
    const urlPay = '${pageContext.request.contextPath}/hospitalization/pay';

    function hospPay(id, cost){
        const msg = '当前余额：' + balance + ' 元\n' +
            '住院费用：' + cost + ' 元\n' +
            '确认支付？';
        if (!confirm(msg)) return;

        $.post(urlPay, {hospitalizationId:id}, function(res){
            if(res.success){
                alert('支付成功！\n剩余余额：'+res.newBalance+' 元');
                location.reload();
            } else {
                alert('支付失败：'+res.message);
            }
        }, 'json').fail(function(){
            alert('网络异常，请稍后重试');
        });
    }
</script>
</body>
</html>
<!-- hospitalizations.jsp 表格后： -->
<tr>
    <td colspan="7">
        <div class="pagelist">
            <!-- 总记录数 -->
            <span class="current">总记录数 ${pageInfo.total}</span>
            <!-- 首页 -->
            <a href="${pageContext.request.contextPath}/hospitalizations?page=1">首页</a>
            <!-- 中间页码 -->
            <c:forEach var="i" begin="1" end="${pageInfo.pages}">
                <c:choose>
                    <c:when test="${i == pageInfo.pageNum}">
                        <span class="current">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/hospitalizations?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <!-- 尾页 -->
            <a href="${pageContext.request.contextPath}/hospitalizations?page=${pageInfo.pages}">尾页</a>
        </div>
    </td>
</tr>
