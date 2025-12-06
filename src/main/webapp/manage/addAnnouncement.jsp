<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>添加公告</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 添加公告</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/manage/addAnnouncement.do">
            <div class="form-group">
                <div class="label">
                    <label for="title">标题：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="title" name="title" placeholder="请输入公告标题" data-validate="required:请输入公告标题">
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="content">内容：</label>
                </div>
                <div class="field">
                    <textarea class="input w50" id="content" name="content" rows="5" placeholder="请输入公告内容" data-validate="required:请输入公告内容"></textarea>
                </div>
            </div>

            <!-- 隐藏不需要用户填写的字段 -->
            <input type="hidden" id="imgurl" name="imgurl" value="">
            <input type="hidden" id="creationTime" name="creationTime" value="<%=new java.util.Date()%>">
            <input type="hidden" id="creator" name="creator" value="1">

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                    <button class="button border-main" type="reset"> 重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>