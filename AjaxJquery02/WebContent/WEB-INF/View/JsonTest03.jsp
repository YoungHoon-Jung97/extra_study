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
		$("#sendButton").click(function()
		{
			var params="su1="+$("#su1").val()+ "&su2="+$("#su2").val()+"&oper="+$("#oper").val();
			
			
			$.ajax(
			{
				
				
				type:"POST"
				,url:"jsontest03ok.do"
				,data:params
				,dataType:"json"
				,success:function(jsonObj)
				{
					alert(jsonObj);
					var out = "";
					
					var su1 = jsonObj.su1;
					var su2 = jsonObj.su2;
					var oper = jsonObj.oper;
					var result = jsonObj.result;
					
					out = su1 + oper + su2 +" = " +result;
					
					$("#result").html(out);
					
	
					
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
	<h1>jQuery의 load()활용 실습</h1>
</div>

<div><input type="text" id="su1" class="txt txtNum" />

	<select name="" id="oper">
		<option value="add">더셈</option>
		<option value="sub">뺄셈</option>
		<option value="mul">곱셈</option>
		<option value="div">나눗셈</option>
	</select>
	
	<input type="text" id="su2" class="txt txtNum" />
	<input type="button" value=" = "  id="sendButton" class="btn" />
	
	
	
</div>




<!--
	jsontest03.do → com.test.ajax.JsonTest03Controller.java →WEB-INF/View/JsonTest03.jsp
																	↓
															jsontest03ok.do → com.test.ajax.JsonTest03okController.java 
																							↓연산 수행 후 내용 전달
																					→ JsonTest03ok.jsp
																						(JSON 객체 전달: su1,su2,oper,result)

  -->

<!--결과 갱신  -->
<div id="result">
</div>
</html>