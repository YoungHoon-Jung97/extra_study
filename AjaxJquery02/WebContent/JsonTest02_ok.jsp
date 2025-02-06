<%@page import="com.sun.javafx.binding.StringFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	String name = request.getParameter("name");
	String content = request.getParameter("content");
	int num = Integer.parseInt(request.getParameter("num"));
	
	StringBuffer sb = new StringBuffer();
	String result = "";
	
	sb.append("[");
	for(int i=1; i<=num; i++)
	{
		sb.append("{\"num\":\"" + i + "\"");				// "{\"num\":\""○"\""
		sb.append(",\"name\":\"" + name+i+ "\"");			// ", \"name\":\""○"\""
		sb.append(",\"content\":\"" + content+i + "\"}");		// ", \"content\":\""○"\"}"
	
		if(i!=num)
		{
			sb.append(",");
		}
	}
	sb.append("]");
	
	
	result = sb.toString();
	
	out.println(result);
	
%>
