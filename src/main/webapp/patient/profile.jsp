<%--
  患者个人信息管理页面
  Created: 2025/07/16
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8" />
    <title>个人信息管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
</head>
<body style="background-color:#f2f9fd; padding:20px;">
<div class="panel">
    <div class="panel-head"><strong>个人信息管理</strong></div>
    <div class="panel-body">
        <form action="${pageContext.request.contextPath}/profile" method="post">
            <input type="hidden" name="patientId" value="${sessionScope.user.patientId}" />

            <div class="form-group">
                <div class="field">
                    <label>身份证号：</label>
                    <input type="text" class="input input-auto" name="idCardNumber"
                           value="${sessionScope.user.idCardNumber}" readonly />
                </div>
            </div>

            <div class="form-group">
                <div class="field">
                    <label>姓名：</label>
                    <input type="text" class="input input-auto" name="pname"
                           value="${sessionScope.user.pname}" required />
                </div>
            </div>

            <div class="form-group">
                <div class="field">
                    <label>密码：</label>
                    <input type="password" class="input input-auto" name="password"
                           placeholder="若不修改，请留空" />
                </div>
            </div>

            <div class="form-group">
                <div class="field">
                    <label>余额 (元)：</label>
                    <input type="number" step="0.01" class="input input-auto" name="balance"
                           value="${sessionScope.user.balance}" required />
                </div>
            </div>

            <div style="padding-top:15px;">
                <input type="submit" class="button bg-main text-big" value="保存修改" />
            </div>
        </form>

        <c:if test="${not empty updateSuccess}">
            <div class="text-success margin-top">更新成功！</div>
        </c:if>
    </div>
</div>
</body>
</html>
