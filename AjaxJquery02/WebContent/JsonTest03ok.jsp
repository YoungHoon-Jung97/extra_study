<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	int su1= Integer.parseInt(request.getParameter("su1"));
	int su2= Integer.parseInt(request.getParameter("su2"));
	String oper = request.getParameter("oper");
	String result = (String)request.getAttribute("result");
	String send = "";
	
	
	if(oper.equals("add"))
	{
		oper = "+";
	}
	else if(oper.equals("sub"))
	{
		oper = "-";
	}
	else if(oper.equals("mul"))
	{
		oper = "*";
	}
	else if(oper.equals("div"))
	{
		oper = "/";
	}
	
	
	StringBuffer sb = new StringBuffer();
	
	
	/* sb.append("{\"su1\":\""+su1+"\"");
	sb.append(",\"su2\":\""+su2+"\"");
	sb.append(",\"oper\":\""+oper+"\"");
	sb.append(",\"result\":\""+result+"\"}"); */
	
	sb.append("{\"su1\":\"" + su1 + "\"");				// "{\"num\":\""○"\""
	sb.append(",\"su2\":\"" + su2+ "\"");			// ", \"name\":\""○"\""
	sb.append(",\"oper\":\"" + oper+ "\"");
	sb.append(",\"result\":\"" + result + "\"}");	
	
	send = sb.toString();
	out.println(send);
%>
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