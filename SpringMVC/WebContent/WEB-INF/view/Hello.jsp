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
<title>Hello.jsp</title>
<link rel="stylesheet" type="text/css" href="css/MemberListScore.css">

</head>
<body>

<div>
	<h1>요청 내용 확인</h1>
</div>

<div>
	<h2>${message }</h2>
</div>


</body>
</html>