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
	//WriteOk.jsp
	/*
	C:\SpringMVC\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\fileSystemApp06\pds\saveFile
	C:\SpringMVC\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\fileSystemApp07\
	*/
	
	//① 기능 구현에 필요한 주요 속성값들 준비
	String root = "/";
	root = pageContext.getServletContext().getRealPath(root);
	
	
	//- 파일이 업로드되는 실제 물리적인 경로 속성 준비
	String savePath = root + "pds" + File.separator + "saveFile";
	//확인
	System.out.println(savePath);
	
	//-② 경로상 물리적인 디렉터리 구조가 존재하지 않을 경우 생성할 수 있도록 처리
	File dir = new File(savePath);
	
	if(!dir.exists())
		dir.mkdirs();
	
	//-인코딩 방식 속성 준비
	String enCoding = "UTF-8";
	int maxFileSize = 5*1024*1024;
	
	//③MultipartRequest구성
	MultipartRequest req = null;
	String urlFile = "";
	try{
		//request, 파일저장경로,파일최대크기, 인코딩방식,중복파일명처리정책
		req = new MultipartRequest(request,savePath,maxFileSize,enCoding,new DefaultFileRenamePolicy());
		
		//④ 구성된 MultipartRequest 로 부터 필요한 값 얻어내기
		out.println("사용자 : " + req.getParameter("userName")+"<br>" );
		out.println("제목 : " + req.getParameter("subject")+"<br>" );
		out.println("서버에 저장된 파일명 : " + req.getFilesystemName("uploadFile")+"<br>" );
		out.println("사용자 업로드 파일명 : " + req.getOriginalFileName("uploadFile")+"<br>" );
		out.println("업로드 파일 타입 : " + req.getContentType("uploadFile")+"<br>" );
		
		File file = req.getFile("uploadFile");
		
		if(file != null)
			out.println("파일 크기 :" + file.length() + "Bytes <br>");
		
		//check~~
		//⑤ 다운로드 기능을 수행하기 위한 속성을 get방식으로 처리
		urlFile = "Download.jsp?saveFileName=" + req.getFilesystemName("uploadFile");
		urlFile += "&originalFileName=" +req.getOriginalFileName("uploadFile");
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


<div>
	<h2>
		<a href="<%=urlFile%>">파일 다운로드</a>
	</h2>
</div>
</body>
</html>