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
			var params = "su1=" +$.trim($("#su1").val()) + "&su2=" +$.trim($("#su2").val())
						+"&oper=" + $.trim($("#oper").val());
			
			$.ajax(
			{
				type:"POST"
				, url:"ajaxtest03ok.do"
				,data:params 
				,success :function(result)
				{
					
					$("#result").html(result);
					
				}
				,beforeSend:function()
				{
					return true;
				} 
				,error:function(e)
				{
					alert(e.responseTest);
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
	ajaxtest03.do → com.test.ajax.AjaxTest03Controller.java →WEB-INF/View/AjaxTest03.jsp
																	↓
															ajaxtest03ok.do → com.test.ajax.AjaxTest03okController.java → AjaxTest03ok.jsp

  -->

<!--결과 갱신  -->
<div id="result">
</div>
<br /><br /><br /><br />

<div>
	<input type="radio" name="rdo" />rdo1
	<input type="radio" name="rdo" />rdo2
</div>

<div>
	<input type="checkbox" name="chk" />chk1
	<input type="checkbox" name="chk" />chk2
</div>

</body>
</html>