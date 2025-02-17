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
<title>MemberList.jsp</title>
<link rel="stylesheet" type="text/css" href="css/MemberListScore.css">
<style type="text/css">

	*{line-height: 150%;}
	#customers {border: 1px solid gray;}
	#customers td{
	background-color: #eeddee;
	text-align: center;}
	#customers th{background-color: #ffeeff;}
	#submitBtn
	{
		height: 150%;
		width: 250%;
		font-size: 18px;
		font-weight: bold;
		
	}
	#error
	{
		display: none;
		color: red;
		font-size: small;
	}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function()
	{
		
		$("#submitBtn").click(function()
		{
			$("#error").css("display","none");
			
			if($("#name").val()=="" || $("#telephone").val()=="")
			{
				$("#error").css("display","inline");
				return;
			}
			
			$("#memberForm").submit();
			
		});
		
	});

</script>
</head>
<body>

<div>
	<h1>회원 관리</h1>
	<h2>(Spring MVC 버전)</h2>
	<hr />
</div>

<div>
	<form action="memberinsert.do" method="post" id="memberForm">
		이름
		<input type="text" name="name" id="name" class="control" required="required" />
		<br />
		
		전화
		<input type="text" name="telephone" id="telephone" class="control" required="required" />
		<br />
		
		<button type="button" id="submitBtn" > 회원 추가 </button>
		<span id ="error">모든 항목을 입력해야 합니다.</span>
		
	</form>
	<br /><br />
	
	<!-- <p>전체 인원 수: 5</p>
	<table id="customers" style="width: 500px;">
		<tr>
			<th>번호</th><th>이름</th><th>전화번호</th>
		</tr>
		
		EL, JSTL 표현에 의한 회원 명단 출력 부분
		
		<tr>
			<td>1</td>
			<td>정영훈</td>
			<td>010-1111-1111</td>
		</tr>
			<tr>
			<td>2</td>
			<td>박세진</td>
			<td>010-2222-2222</td>
		</tr>
			<tr>
			<td>3</td>
			<td>곽유진</td>
			<td>010-3333-3333</td>
		</tr>
	</table> -->
	
	<p>전체 인원 수: ${count }</p>
	<table id="customers" style="width: 500px;">
		<tr>
			<th>번호</th><th>이름</th><th>전화번호</th>
		</tr>
		
		<!-- EL, JSTL 표현에 의한 회원 명단 출력 부분 -->
		<c:forEach var ="member" items="${memberList}" >
			<tr>
			<td>${member.mid }</td>
			<td>${member.name }</td>
			<td>${member.telephone }</td>
		</tr>
		
		</c:forEach>
		
		
	</table>
</div>

</body>
</html>