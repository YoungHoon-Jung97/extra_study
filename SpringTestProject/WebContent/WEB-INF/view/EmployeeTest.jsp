<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/MemberListScore.css">

</head>
<body>

<div>
	<c:forEach  var="emp" items="${positions}">
	<p>사원ID: ${emp.positionId}</p>
	<p>사원ID: ${emp.positionName}</p>
    <hr>
	</c:forEach>

</div>
</body>
</html>