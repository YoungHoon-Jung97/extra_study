<%@ page contentType="text/html; charset=UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%><%
 	String name = request.getParameter("name");
	String content = request.getParameter("content"); 

	response.setContentType("text/xml");
	
%><?xml version="1.0" encoding="UTF-8"?>
<lists>
	<totalDataCount>5</totalDataCount>
	<%
	for(int i = 1; i<=5; i++)
	{
	%>
	<comment num="<%=i %>">
		<name><%=name + i  %></name>
		<content><%=content + i  %></content>
	</comment>
	<%
	}
	%>
</lists>

