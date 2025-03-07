<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UploadForm</title>
<link rel="stylesheet" type="text/css" href="css/MemberListScore.css">

</head>
<body>


<div>
	<h1>파일 입력 폼</h1>
	<hr />
</div>

<div>
	<form action="Receive.jsp" method="post" enctype="multipart/form-data">
		file: 
		<input type="file" name = "file" size="50" />
		
		text:
		<input type="text" name="data" size="30" />
		<br /><br />
		
		<input type="submit" value="upload"/>
	</form>
</div>
</body>
</html>