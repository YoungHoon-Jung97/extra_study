<%@page import="com.util.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.test.BoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	int num = Integer.parseInt(request.getParameter("num"));	
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	
	Connection conn = DBConn.getConnection();
	BoardDAO dao = new BoardDAO(conn);
	
	int result = dao.deleteData(num);
	
	//result 결과값에 따른 분기 처리 기능
	
	DBConn.close();
	
	response.sendRedirect(cp+"/List.jsp?pageNum="+ pageNum);

%>
