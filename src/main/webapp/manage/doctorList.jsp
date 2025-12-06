<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 2025/7/9
  Time: 下午4:23
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
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
    <div class="panel admin-panel">

        <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
        <form method="post" action="${pageContext.request.contextPath}/manage/doctorsSearch.do" id="listForm">
            <div class="padding border-bottom">
                <ul class="search" style="padding-left:10px;">

                    <li>
                        <%--设置用户请求的当前页--%>
                        <input type="hidden" name="page" id="page" value="1">
                        <%--    设置科室--%>
                        <select name="departmentId" class="input" style="width:100px; line-height:17px;">
                            <option value="">请选择科室</option>
                            <c:forEach items="${departmentsSecondList}" var="d">
                                <option value="${d.departmentId}" ${param.departmentId==d.departmentId?"selected":""}>${d.departmentName}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li>
                        <%--    设置职称--%>
                        <select name="titleId" class="input" style="width:100px; line-height:17px;">
                            <option value="">请选择职称</option>
                            <c:forEach items="${titlesList}" var="pt">
                                <option value="${pt.id}" ${param.titleId==pt.id?"selected":""}>${pt.titleName}</option>
                            </c:forEach>
                        </select>
                    </li>

                    <li><input type="text" placeholder="请输入医生姓名" value="${param.doctorName}" name="doctorName" class="input" style="width:150px; line-height:17px;display:inline-block" /></li>
                    <li><input type="text" placeholder="请输入医生工号" value="${param.jobNumber}" name="jobNumber" class="input" style="width:150px; line-height:17px;display:inline-block" /></li>
                    <li><a href="javascript:submitFormData()" class="button border-main icon-search" > 搜索</a></li>
<%--                    <li> <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/manage/doctor/addDoctor.jsp"> 添加内容</a> </li>--%>
<%--                    <li> <a class="button border-red icon-plus-square-o" href="${pageContext.request.contextPath}/manage/announce/addAnnouncement.jsp"> 批量删除</a> </li>--%>
                </ul>
            </div>
        </form>
        <script>
            // 提交表单的事件，表单验证
            function submitFormData(){
                // 调用表单对象的提交方法：submit()
                document.getElementById("listForm").submit()
            }
            // 定义页码的点击事件，在此事件中，同时带查询条件一起执行
            function changePage(page){
                // 把page的值赋值给表单的隐藏域page
                document.getElementById("page").value=page;
                // 调用表单对象的提交方法:submit()
                document.getElementById("listForm").submit()
            }
        </script>

        <table class="table table-hover text-center">
            <tr>
                <th width="10%">编号</th>
                <th width="10%">工号</th>
                <th width="25%">图片</th>
                <th width="15%">姓名</th>
                <th width="10%">科室</th>
                <th width="15%">职称</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pageInfo.list}" var="doctor">
                <tr>
                    <td><input type="checkbox" name="doctorId" value="${doctor.doctorId}"/>
                    </td>
                    <td>${doctor.jobNumber}</td>
                    <td><img src="${pageContext.request.contextPath}/${doctor.avatar}" style="width: 80px"></td>
                    <td>${doctor.name}</td>
                    <td>${doctor.departments.departmentName}</td>
                    <td>${doctor.titles.titleName}</td>
                    <td>
                        <div class="button-group"><a class="button border-red" href="javascript:void(0)"
                                                     onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="8">
                    <div class="pagelist">
                        <span class="current">总记录数${pageInfo.total}</span>
                        <a href="javascript:changePage(1)">首页</a>
                        <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                            <c:choose>
                                <c:when test="${i==pageInfo.pageNum}">
                                    <span class="current">${i}</span>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:changePage(${i})">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <a href="javascript:changePage(${pageInfo.pages})">尾页</a>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</body></html>
