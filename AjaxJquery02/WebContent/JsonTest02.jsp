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
		$("#sendBtn").click(function()
		{
			
			var params = "name="+ $("#name").val() + "&content=" + $("#content").val()
				+"&num="+$("#num").val();
			
			
			$.ajax(
			{
				
				type:"POST"
				,url:"JsonTest02_ok.jsp"
				,data:params
				,dataType:"json"				//-- check~!!
				, success:function(jsonObj)
				{
					var out = "";
					for(var i=0; i<jsonObj.length; i++)
					{
						
						
						var num = jsonObj[i].num;
						var name = jsonObj[i].name;
						var content = jsonObj[i].content;
						
						out += "<br>=========================";
						out += "<br> 번호 : " + num;
						out += "<br> 이름 : " + name;
						out += "<br> 내용 : " + content;
						out += "<br>=========================<br>";
						
						$("#result").html(out);
						
						$("#name").val("");
						$("#content").val("");
						$("#name").focus();
					}
					
				}
				,beforeSend:function()
				{
					return true;
				}
				,error:function(e)
				{
					alert(e.responseText);
				}
			
				
			
				
			});
			
		});
		
	});

</script>

</head>
<body>

<div>
	<h1>jQuery의 ajax() 활용 실습</h1>
	<p>json control</p>
	<hr>
</div>

<div>
	번호<input type ="text" id="num" class="txt"><br><br>
	이름 <input type="text" id="name" class="txt"><br><br>
	내용 <input type="text" id="content" class="txt"><br><br>
	
	<input type="button" value="등록하기" id="sendBtn" class="btn">
</div>
<br><br>

<div id="result">
</div>



</body>
</html>