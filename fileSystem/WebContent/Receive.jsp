<%@page import="java.io.DataInputStream"%>
<%@page import="java.util.Enumeration"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	//Receive.jsp
	
	String contentType = request.getContentType();
	Enumeration e =  request.getHeaderNames();
	
	out.println("[[ 브라우저로부터 수신한 헤더 정보들]]<br>");
	
	while(e.hasMoreElements())
	{
		
		String key = (String)e.nextElement();
		String value = request.getHeader(key);
		
		out.println(key +" " +value +" " +"<br>");
	}
	
	out.println("<br>===================================================<br>");
	out.println("[[ requset 로 넘어오는 데이터들]]<br>");
	
	DataInputStream dis = new DataInputStream(request.getInputStream());
	String line = null;
	
	while((line=dis.readLine()) !=null){
		
		//out.println(new String(바이트코드,인코딩방식));
		out.println(new String(line.getBytes("ISO-8859-1"), "UTF-8") + "<br>");
	}

%>

