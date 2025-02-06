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

	$(document).ready(function() {
	    $("#sendButton").click(function() {
	        // 값 가져오기
	        var su1 = $("#su1").val();  // #su1 요소의 값
	        var su2 = $("#su2").val();  // #su2 요소의 값
	        var oper = $("#oper").val();  // #oper 요소의 값
	
	        // 값을 URL 파라미터로 전달
	        $("#result").load("loadtest03.do?su1=" + su1 + "&su2=" + su2 + "&oper=" + oper);
	        
	    
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
	loadtest03send.do → Test03send.java → LoadTest03.jsp
												↓
											loadtest03.do → LoadTest03.java → LoadTest03ok.jsp

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