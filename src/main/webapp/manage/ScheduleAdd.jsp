<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>新增排班</title>
    <style>
        .error { color: red; }
        label { display: inline-block; width: 80px; }
    </style>
</head>
<body>
<h2>新增排班</h2>

<!-- 显示错误消息（如果有） -->
<c:if test="${not empty errorMsg}">
    <div class="error">${errorMsg}</div>
</c:if>

<form action="manage/schedule" method="post" onsubmit="return validateForm()">
    <input type="hidden" name="action" value="saveAdd">

    <!-- 医生选择下拉框 -->
    <label>医生：</label>
    <select name="doctorId" required>
        <option value="">请选择医生</option>
        <c:forEach items="${doctors}" var="doctor">
            <option value="${doctor.doctorId}">${doctor.name}</option>
        </c:forEach>
    </select><br><br>

    <!-- 科室选择下拉框 -->
    <label>科室：</label>
    <select name="deptId" required>
        <option value="">请选择科室</option>
        <c:forEach items="${departments}" var="dept">
            <option value="${dept.departmentId}">${dept.departmentName}</option>
        </c:forEach>
    </select><br><br>

    <!-- 日期选择 -->
    <label>日期：</label>
    <input type="date" name="date" required><br><br>

    <!-- 班次选择 -->
    <label>班次：</label>
    <select name="shiftTime" required>
        <option value="上午">上午</option>
        <option value="下午">下午</option>
    </select><br><br>

    <button type="submit">保存</button>
    <a href="manage/schedule?action=list">取消</a>
</form>

<script>
    // 表单验证函数
    function validateForm() {
        const doctorSelect = document.querySelector('select[name="doctorId"]');
        const doctorId = doctorSelect.value;

        console.log("选中的医生ID：", doctorId);  // 在浏览器控制台查看输出

        if (doctorId === "") {
            alert("请选择医生");
            return false;
        }
        // 获取表单元素
        const deptSelect = document.querySelector('select[name="deptId"]');
        const dateInput = document.querySelector('input[name="date"]');

        // 获取选中的值
        const deptId = deptSelect.value;
        const date = dateInput.value;

        // 打印调试信息（在浏览器控制台查看）
        console.log("验证表单: doctorId=" + doctorId + ", deptId=" + deptId + ", date=" + date);

        // 验证逻辑
        if (doctorId === "") {
            alert("请选择医生");
            doctorSelect.focus();
            return false;
        }
        if (deptId === "") {
            alert("请选择科室");
            deptSelect.focus();
            return false;
        }
        if (date === "") {
            alert("请选择日期");
            dateInput.focus();
            return false;
        }

        // 所有验证通过
        return true;
    }
</script>
</body>
</html>