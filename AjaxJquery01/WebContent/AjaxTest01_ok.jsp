<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
/* 	String name = request.getParameter("name");
	String content = request.getParameter("content"); */

%>
<%-- 이름 : <b><%=name %></b>
<br /><br>
내용<br>
<%=content %> --%>

이름 : ${param.name }
내용 : ${param.content }

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/MemberListScore.css">

</head>
<body>

</body>
</html>