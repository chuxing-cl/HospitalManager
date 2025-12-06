<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>患者中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<!-- Header -->
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1>
            <img src="${pageContext.request.contextPath}/images/patient.png" class="radius-circle rotate-hover" height="50" alt="患者图标"/>
            患者中心
        </h1>
    </div>
    <div class="head-l">
      <span class="button button-little bg-green">
        <span class="icon-user"></span>
        欢迎，<c:out value="${sessionScope.user.pname}"/>
      </span>
        &nbsp;&nbsp;
        <a class="button button-little bg-red" href="${pageContext.request.contextPath}/logout.do">
            <span class="icon-power-off"></span> 退出登录
        </a>
    </div>
</div>

<!-- Left navigation -->
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list"></span> 菜单</strong></div>

    <h2><span class="icon-user"></span> 个人中心</h2>
    <ul style="display:block">
        <li><a href="${pageContext.request.contextPath}/profile" target="adminFrame">
            <span class="icon-caret-right"></span> 我的信息
        </a></li>
    </ul>

    <!-- 就诊管理 -->
    <h2><span class="icon-calendar"></span> 就诊管理</h2>
    <ul>
        <!-- 直接指向 presciptions.jsp -->
        <!-- 假设这是左侧菜单 -->
        <li>
            <a href="${pageContext.request.contextPath}/prescriptions" target="adminFrame"
            >
                <span class="icon-caret-right"></span> 我的处方信息及缴费
            </a>
        </li>

        <!-- 直接指向 hospitalizations.jsp -->
        <li>
            <a href="${pageContext.request.contextPath}/hospitalizations" target="adminFrame">
                <span class="icon-caret-right"></span> 我的住院信息及缴费
            </a>
        </li>
    </ul>

</div>

<script type="text/javascript">
    $(function() {
        $(".leftnav h2").click(function() {
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        });
        $(".leftnav ul li a").click(function() {
            $("#breadcrumbText").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        });
    });
</script>

<!-- Breadcrumb -->
<ul class="bread">
    <li><a href="#" class="icon-home"> 首页</a></li>
    <li><span id="breadcrumbText">我的信息</span></li>
</ul>

<!-- Main content -->
<div class="admin">
    <iframe name="adminFrame" frameborder="0" scrolling="auto"
            src="${pageContext.request.contextPath}/profile"
            width="100%" height="100%"></iframe>
</div>

<!-- Footer -->
<div style="text-align:center; padding:10px 0; color:#999;">
    <p>Powered by Your Hospital System</p>
</div>
</body>
</html>
