<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/MemberListScore.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
	
	$(function()
	{
		$("#loadBtn").click(function()
		{
			var nickName = $("#nickName").val();
			
			// $.get(url, data, callBack);
			//매개변수로 전달받은 url 을 사용하도록 한다.
			//서버에 대한 GET 요청을 전송하도록 한다.
			
			//	$.get(url, {속성1:값1, 속성2:값2, ...}, callBack);
			// 전달 과정에서 data 는 JSON형태로 처리할 수 있다.
			
			//	$.get(url, {속성1:값1, 속성2:값2, ...}, function(넘겨받은 값)
			//{
			//		$(선택자).html(넘겨받은 값 처리);	
			//});	
			
			$.get("GetTest01ok.jsp",{nickName : nickName}, function(data)	
			{
				$("#resultDiv").html(data);
				
			});
				
			
			
		});
		
	});

</script>

</head>
<body>

<div>
	<h1>jQuery 의 get() 활용 실습</h1>
	<hr>
</div>

<div>
	<input type="text" id="nickName" class="txt">
	
	<input type="button" value="클릭 확인" id="loadBtn" class="btn">
</div>
<br>

<div id="resultDiv">
</div>

</body>
</html>