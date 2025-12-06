<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 2025/7/10
  Time: 上午10:33
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
    <div class="panel admin-panel margin-top">
        <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加内容</strong></div>
        <div class="body-content">
            <form method="post" class="form-x" action="${pageContext.request.contextPath}/manage/addTitles.do">
                <div class="form-group">
                    <div class="label">
                        <label>职称名称：</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input w50" name="titleName" id="TN" />
                        <div class="tips"><span id="span_no"></span></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label>职称描述：</label>
                    </div>
                    <div class="field">
                        <textarea type="text" class="input" name="description" style="height:100px;" ></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label></label>
                    </div>
                    <div class="field">
                        <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <%--引入jQuery.js文件--%>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <%--编写JS实现学号的验证--%>
    <script>
        // 编写就绪函数(它会确保代码在DOM完全加载后才执行)
        $(document).ready(function(){
            // blur是一个事件，当某个可聚焦的元素（如输入框）失去焦点时触发
            // 编写职称的失去焦点事件，并在此事件中调用jQuery.ajax()实现异步的验证
            $("#TN").blur(function(){
                // 获取职称文本框的值
                var titleName = $(this).val();
                if(titleName.length==0){
                    $("#span_no").text("请输入职称名称");
                    $("#span_no").css("color","red");
                    return;
                }
                // 调用jQuery.ajax()实现异步
                $.ajax({
                    "url"      :  "${pageContext.request.contextPath}/manage/checkTitlesById.do",    // 要提交的URL路径
                    "type"     :  "get",                                                // 发送请求的方式
                    "data"     :  {titleName:titleName,time:Math.random()},             // 要发送到服务器的数据
                    "dataType" :  "text",                                               // 指定响应的数据类型
                    "success"  :  function(result){                                     // 请求成功后要执行的代码
                        console.log("result==>"+result);
                        // 如果为true则提示“此学号已存在”，否则提示“可用”
                        if(result=="true"){
                            $("#span_no").text("职称已存在");
                            $("#span_no").css("color","red");
                        }else{
                            $("#span_no").text("职称可用");
                            $("#span_no").css("color","green");
                        }
                    }
                });
            })
        })
    </script>
</body></html>
