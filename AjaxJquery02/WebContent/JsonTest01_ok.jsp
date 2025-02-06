<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	//JsonTest01_ok.jsp
	
	String name = request.getParameter("name");
	String content = request.getParameter("content");
	
	String result="";
	
	//JSON 데이터
	//{"이름1": "값1", "이름2": "값2", .... }
	
	StringBuffer sb = new StringBuffer();
	
	/* 따옴표 구성에 유의할 것~!!! */
	sb.append("{\"num\":\"" + 1 + "\"");				// "{\"num\":\""○"\""
	sb.append(",\"name\":\"" + name + "\"");			// ", \"name\":\""○"\""
	sb.append(",\"content\":\"" + content + "\"}");		// ", \"content\":\""○"\"}"
	
	result = sb.toString();
	
	out.println(result);
	
%>
