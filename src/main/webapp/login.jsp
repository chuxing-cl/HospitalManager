<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 2025/7/8
  Time: 上午11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入 JSTL 标签库 --%>
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
    <title>登录</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <form action="${pageContext.request.contextPath}/login.do" method="post">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top">
                        <h1>后台管理中心</h1>
                    </div>

                    <c:if test="${not empty loginErr}">
                        <div><span style="color: red">${loginErr}</span></div>
                    </c:if>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <!-- 账号输入 -->
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text"
                                       class="input input-big"
                                       name="name"
                                       placeholder="登录账号"
                                       data-validate="required:请填写账号" />
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <!-- 密码输入 -->
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password"
                                       class="input input-big"
                                       name="password"
                                       placeholder="登录密码"
                                       data-validate="required:请填写密码" />
                                <span class="icon icon-key margin-small"></span>
                            </div>
                        </div>
                        <!-- 角色选择 -->
                        <div class="form-group">
                            <div class="field">
                                <select name="rid"
                                        class="input"
                                        style="width:332px; line-height:20px;">
                                    <option value="1">管理员</option>
                                    <option value="2">医生</option>
                                    <option value="3">患者</option>  <!-- 新增患者选项 -->
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- 提交按钮 -->
                    <div style="padding:30px;">
                        <input type="submit"
                               class="button button-block bg-main text-big input-big"
                               value="登录" />
                    </div>

                </div>
            </form>
            <!-- 新增：注册按钮 -->
            <p style="margin-top:10px;">
                还没有账号？
                <a href="${pageContext.request.contextPath}/register.do" class="button bg-green">
                    用户注册
                </a>
            </p>
        </div>
    </div>
</div>
</body>
</html>
