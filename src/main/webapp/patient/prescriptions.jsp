<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8" />
    <title>我的处方信息及缴费</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
    <style>
        /* 列宽和居中 */
        .presc-table { width: 100%; border-collapse: collapse; }
        .presc-table th, .presc-table td {
            text-align: center;
            vertical-align: middle;
            padding: 8px;
        }
        .presc-table col:nth-child(1) { width: 10%; }
        .presc-table col:nth-child(2) { width: 15%; }
        .presc-table col:nth-child(3) { width: 30%; }
        .presc-table col:nth-child(4) { width: 15%; }
        .presc-table col:nth-child(5) { width: 15%; }
        .presc-table col:nth-child(6) { width: 15%; }
    </style>
</head>
<body style="background-color:#f2f9fd; padding:20px;">
<div class="panel">
    <div class="panel-head"><strong>我的处方信息及缴费</strong></div>
    <div class="panel-body">
        <table class="table table-hover table-bordered table-striped presc-table">
            <colgroup>
                <col /><col /><col /><col /><col /><col />
            </colgroup>
            <thead>
            <tr>
                <th>处方ID</th>
                <th>就诊时间</th>
                <th>医嘱内容</th>
                <th>处方价格(元)</th>
                <th>缴费状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${consultations}">
                <tr>
                    <td><c:out value="${c.consultationId}" /></td>
                    <td><fmt:formatDate value="${c.consultationTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><c:out value="${c.medicalAdviceCase}" /></td>
                    <td><fmt:formatNumber value="${c.price}" pattern="#,##0.00"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${c.hospitalized}">已缴费</c:when>
                            <c:otherwise>未缴费</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>

                            <c:when test="${c.hospitalized}">
                                无
                            </c:when>

                            <c:otherwise>
                                <a href="javascript:void(0)"
                                   class="button button-mini bg-main"
                                   onclick="payConfirm(${c.consultationId}, '${c.price}');">
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
    // 用单引号包 EL，避免 IntelliJ 报错
    const currentBalance = '${sessionScope.user.balance}';
    const payUrl = '${pageContext.request.contextPath}/consultation/pay';

    function payConfirm(id, price) {
        const msg =
            '当前余额：' + currentBalance + ' 元\n' +
            '应付金额：' + price + ' 元\n' +
            '确认要支付吗？';
        if (!confirm(msg)) return;
        $.post(payUrl, { consultationId: id }, function(res) {
            if (res.success) {
                alert('支付成功！\n剩余余额：' + res.newBalance + ' 元');
                location.reload();
            } else {
                alert('支付失败：' + res.message);
            }
        }, 'json').fail(function(xhr, status, err){
            console.error('pay AJAX failed:', status, err, xhr.responseText);
            alert('网络异常，请查看控制台');
        });
    }
</script>

</body>
</html>
<!-- prescriptions.jsp 表格后： -->
<tr>
    <td colspan="6">
        <div class="pagelist">
            <!-- 总记录数 -->
            <span class="current">总记录数 ${pageInfo.total}</span>
            <!-- 首页 -->
            <a href="${pageContext.request.contextPath}/prescriptions?page=1">首页</a>
            <!-- 中间页码 -->
            <c:forEach var="i" begin="1" end="${pageInfo.pages}">
                <c:choose>
                    <c:when test="${i == pageInfo.pageNum}">
                        <span class="current">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/prescriptions?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <!-- 尾页 -->
            <a href="${pageContext.request.contextPath}/prescriptions?page=${pageInfo.pages}">尾页</a>
        </div>
    </td>
</tr>
