<%@page import="java.io.ObjectOutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	String appRoot = "/";
	appRoot = pageContext.getServletContext().getRealPath(appRoot);
	
	System.out.println(appRoot);
	
	File newFile = new File(appRoot, "objData/data.ser");
	
	if(!newFile.getParentFile().exists())
		newFile.getParentFile().mkdirs();
	
	Hashtable<String, String> h = new Hashtable<String,String>();
	
	h.put("key1","수박");
	h.put("key2","딸기");
	
	FileOutputStream fos = new FileOutputStream(newFile);
	
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	
	oos.writeObject(h);
	
	oos.close();
	fos.close();

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