<%@page import="com.test.BoardDAO"%>
<%@page import="com.util.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
   request.setCharacterEncoding("UTF-8");
   String cp = request.getContextPath();
%>

<jsp:useBean id="dto" class="com.test.BoardDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%
   // Created_ok.jsp
   
   Connection conn = DBConn.getConnection();
   BoardDAO dao = new BoardDAO(conn);
   String secret = request.getParameter("secret");
   
   int maxNum = dao.getMaxNum();
   

   dto.setNum(maxNum+1);
   dto.setIPAddr(request.getRemoteAddr());

   
   dao.insertData(dto);
   
   DBConn.close();
   
   response.sendRedirect("List.jsp?secret="+secret);
   
%>