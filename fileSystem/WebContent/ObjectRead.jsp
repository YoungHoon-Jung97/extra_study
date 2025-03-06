<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.io.ObjectInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	String appRoot="/";
	appRoot = pageContext.getServletContext().getRealPath(appRoot);
	
	File newFile = new File(appRoot,"objdata/data.ser");
	
	if(newFile.exists())
	{
		FileInputStream fis = new FileInputStream(newFile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Hashtable h = (Hashtable)ois.readObject();

		ois.close();
		fis.close();
		
		Enumeration e = h.keys();
		
		while (e.hasMoreElements())
		{
			String key = (String)e.nextElement();
			String value = (String)h.get(key);
%>
			<%=key %>의 값은 <%=value %>입니다.<br>
<%
		}
	}
	
%>
