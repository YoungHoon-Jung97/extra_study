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
<title>StudentList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/mian.css">
<!-- jquery -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<script type="text/javascript">
	
	$(function(){
		

		
		$(".btn-success").click(function(){
		
			$(location).attr("href","gradeupdateform.action?sid="+$(this).val());
		});
		
		$(".btn-danger").click(function(){
			
			if (confirm("정말 삭제 하시겠습니까?"))
			{
				$(location).attr("href","gradedelete.action?sid="+$(this).val());
			}
		});
		
	});
	
</script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">


<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


</head>
<body>


<div>
	<h1>학생 성적 관리</h1>
	<p>(SpringMVC + Annotation + mybatis 버전)</p>
	<hr />
</div>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		
		<div class="navbar-header">
			<a class="navbar-brand" >
				Home
			</a>
		</div>
		
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active">
					<a href="studentlist.action">
						학생 관리
						<span class="sr-only">
							(current)
						</span>
					</a>
				</li>
				<li>
					<a href="gradelist.action">
						학생 성적관리
					</a>
				</li>
				
			</ul>
		</div>
	</div>
</nav>

<div class="container">
	<div class="panel-group">
		<div class="panel panel-default">
			
			<div class="panel-heading row">
				<span style="font-size: 17pt; font-weight: bold; " class="col-md-3">
					학생 리스트 출력
				</span>
			</div>
			
			<div class="panel-body">
				전체 학생 수
				<span class="badge">
					${count}
				</span>
			</div>
			
			<div class="panel-body">
				<table class="table table-hover table-striped">
					<thead>
						<tr class="trTitle">
							<th>번호</th>
							<th>이름</th>
							<th>국어 점수</th>
							<th>수학 점수</th>
							<th>영어 점수</th>
							<th>총 점수</th>
							<th>평균 점수</th>
							<th>평가 점수</th>
							<th>수정 / 삭제</th>
						</tr>
					</thead>
					
					<tbody>
					
						
						<c:forEach var="grade" items="${lists }">
						<tr>
							<td>${grade.sid}</td>
							<td>${grade.name}</td>
							<td>${grade.sub1}</td>
							<td>${grade.sub2}</td>
							<td>${grade.sub3}</td>
							<td>${grade.tot}</td>
							<td>${grade.avg}</td>
							
							<c:choose>
								<c:when test="${grade.ch =='확인필요' }">
									<td style="font-size: x-large; color: black">${grade.ch}</td>
								</c:when>
								<c:when test="${grade.ch == '합격' }">
									<td style="font-size: x-large; color: blue;">${grade.ch}</td>
								</c:when>
								<c:otherwise>
									<td style="font-size: x-large; color: red">${grade.ch}</td>
								</c:otherwise>
							</c:choose>
						
							
							<c:choose>
								<c:when test="${grade.ch=='확인필요' }">
									<td colspan="2">
										<a href="gradeinsertform.action?sid=${grade.sid}" class="btn btn-success">점수 추가</a>
									</td>
								</c:when>
								<c:otherwise>
									<td>
										<button type="button" class="btn btn-success" value="${grade.sid}">수정</button>
										<button type="button" class="btn btn-danger" value="${grade.sid}">삭제</button>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
						</c:forEach>
					</tbody>
					
				</table>
		
			</div>
		</div>
	</div>
</div>

</body>
</html>