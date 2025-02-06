<%@page import="com.util.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.test.BoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<jsp:useBean id="dto" class="com.test.BoardDTO"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>
<%
	//이전 페이지 Updated.jsp
	//넘어온 변수 name, subject, content, email, pwd, num;
	int num = Integer.parseInt(request.getParameter("num"));
	Connection conn = DBConn.getConnection();
	BoardDAO dao = new BoardDAO(conn);
	
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	
	dto.setNum(num);
	
	dao.updateData(dto);
	
	response.sendRedirect(cp+"/List.jsp?pageNum="+pageNum+"&num="+num);

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