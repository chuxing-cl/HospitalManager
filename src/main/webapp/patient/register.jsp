<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
    <title>用户注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
    <script>
        function validateForm() {
            var p1 = document.getElementById("password").value;
            var p2 = document.getElementById("confirm").value;
            if (p1 !== p2) {
                alert("两次输入的密码不一致！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body style="background-color:#f2f9fd; padding:20px;">
<div class="panel">
    <div class="panel-head"><strong>注册新账号</strong></div>
    <div class="panel-body">
        <c:if test="${not empty error}">
            <div class="text-red">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/register.do" method="post" accept-charset="UTF-8" onsubmit="return validateForm();">
            <div class="form-group">
                <label>姓名：</label>
                <input type="text" name="pname" class="input" required/>
            </div>
            <div class="form-group">
                <label>用户名(登录用)：</label>
                <input type="text" name="idCardNumber" class="input" required/>
            </div>
            <div class="form-group">
                <label>密码：</label>
                <input type="password" id="password" name="password" class="input" required/>
            </div>
            <div class="form-group">
                <label>确认密码：</label>
                <input type="password" id="confirm" class="input" required/>
            </div>
            <div class="form-group">
                <button type="submit" class="button bg-main">注册</button>
                &nbsp;
                <a href="${pageContext.request.contextPath}/login.jsp" class="button">返回登录</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
