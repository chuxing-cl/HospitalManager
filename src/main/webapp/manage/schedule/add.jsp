<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增排班</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
</head>
<body>
<div class="admin">
    <h3>新增排班</h3>
    <form action="${pageContext.request.contextPath}/manage/schedule" method="post" class="form">
        <input type="hidden" name="action" value="add">

        <!-- 医生选择（下拉框） -->
        <div class="form-group">
            <label>医生</label>
            <select name="doctorId" class="input" required>
                <option value="">请选择医生</option>
                <c:forEach items="${doctors}" var="doc">
                    <option value="${doc.doctorId}">${doc.name}（${doc.jobNumber}）</option>
                </c:forEach>
            </select>
        </div>

        <!-- 科室选择（下拉框） -->
        <div class="form-group">
            <label>科室</label>
            <select name="deptId" class="input" required>
                <option value="">请选择科室</option>
                <c:forEach items="${departments}" var="dept">
                    <option value="${dept.departmentId}">${dept.departmentName}</option>
                </c:forEach>
            </select>
        </div>

        <!-- 排班日期 -->
        <div class="form-group">
            <label>排班日期</label>
            <input type="date" name="date" class="input" required>
        </div>

        <!-- 时间段 -->
        <div class="form-group">
            <label>时间段</label>
            <select name="shiftTime" class="input" required>
                <option value="上午">上午</option>
                <option value="下午">下午</option>
            </select>
        </div>

        <!-- 可排班状态 -->
        <div class="form-group">
            <label>可排班状态</label>
            <select name="isAvailable" class="input" required>
                <option value="0">可排班</option>
                <option value="1">不可排班</option>
            </select>
        </div>

        <!-- 最大可预约数量 -->
        <div class="form-group">
            <label>最大就诊数量</label>
            <input type="number" name="sumCount" class="input" min="1" required placeholder="如：50">
        </div>

        <div class="form-group">
            <button type="submit" class="button bg-blue">提交</button>
            <a href="${pageContext.request.contextPath}/manage/schedule?action=list" class="button">取消</a>
        </div>
    </form>

    <!-- 错误提示（如有） -->
    <c:if test="${not empty errorMsg}">
        <div class="alert alert-red">${errorMsg}</div>
    </c:if>
</div>
</body>
</html>