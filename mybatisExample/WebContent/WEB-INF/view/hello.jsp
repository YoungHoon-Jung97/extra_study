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
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">

</head>
<body>

<div>
	<h1>mybatis 환경 구성 테스트</h1>
	<hr />
</div>

<div>
	<!-- <h2>테스트</h2> -->
	<h2>${message }</h2>
</div>

</body>
</html>