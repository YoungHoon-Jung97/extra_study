<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	//TestOk.jsp
	
	String root="/";
	root= pageContext.getServletContext().getRealPath(root);
	
	String savePath = root +"pds" + File.separator + "saveFile";
	
	System.out.println(savePath);
	
	File dir = new  File(savePath);
	if(!dir.exists())
		dir.mkdirs();
	
	MultipartRequest req = null;
	String encType = "UTF-8";
	int maxFileSize = 4*1024*1024;
	try
	{
		req = new MultipartRequest(request,savePath, maxFileSize,encType,new DefaultFileRenamePolicy());
		out.println("작성자 : " + req.getParameter("userName") +"<br>");
		out.println("제목 : " + req.getParameter("subject") +"<br>");
		out.println("==================================================<br>");
		
		out.println("업로드된 파일<br>");
		
		Enumeration files = req.getFileNames();
		while(files.hasMoreElements())
		{
			String name = (String)files.nextElement();
			if(req.getFilesystemName(name)!=null)
			{
				out.println("서버에 저장된 파일명 : " + req.getFilesystemName(name) +"<br>");
				out.println("사용자 업로드 파일명 : " + req.getOriginalFileName(name)+"<br>");
				out.println("업로드 파일 타입 : " + req.getContentType(name) +"<br>");
				
				File f = req.getFile(name);
				if(f !=null)
					out.println("업로드된 파일 크기 : " + f.length() +"Byte<br>");
			}
			
		}
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
		
	}
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