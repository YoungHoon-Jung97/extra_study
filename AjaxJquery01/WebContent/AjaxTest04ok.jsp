<%@ page contentType="text/html; charset=UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	response.setContentType("text/xml");
	
	
%><?xml version="1.0" encoding="UTF-8"?>
<lists>
	
	<totalData>2</totalData>
	<%
	for(int i=1; i<=2; i++)
	{
	%>
	<comtent num="<%=i %>">
		<c:forEach var="dto" items="${result}">
    		<title>${dto.title}</title>    <!-- title 출력 -->
    		<content>${dto.content }</content>
		</c:forEach>
	</comtent>
	<%
	}
	%>
</lists>