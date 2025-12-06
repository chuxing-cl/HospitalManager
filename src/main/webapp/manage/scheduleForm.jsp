<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<body>
<h2>编辑排班</h2>
<form action="${pageContext.request.contextPath}/manage/schedule" method="post">
    <input type="hidden" name="action" value="save">
    <input type="hidden" name="scheduleId" value="${schedule.scheduleId}">

    <!-- 直接访问 doctorName 和 deptName，不依赖关联对象 -->
    <label>医生：</label>
    <input type="text" value="${schedule.doctorName}" readonly><br><br>
    <!-- 添加医生ID隐藏字段 -->
    <input type="hidden" name="doctorId" value="${schedule.doctorId}">

    <label>科室：</label>
    <input type="text" value="${schedule.deptName}" readonly><br><br>
    <!-- 添加科室ID隐藏字段 -->
    <input type="hidden" name="departmentId" value="${schedule.deptId}">
    <label>日期：</label>
    <input type="date" name="date" value="<fmt:formatDate value='${schedule.date}' pattern='yyyy-MM-dd'/>"><br><br>
    <label>班次：</label>
    <select name="shiftTime">
        <option value="上午" ${schedule.shiftTime == '上午' ? 'selected' : ''}>上午</option>
        <option value="下午" ${schedule.shiftTime == '下午' ? 'selected' : ''}>下午</option>
    </select><br><br>

    <button type="submit">保存</button>
</form>
</body>
</html>