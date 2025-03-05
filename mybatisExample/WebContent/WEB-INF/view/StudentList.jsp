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
					학생 성적 리스트 출력
				</span>
				<span class="col-md-9">
					<a href="studentinsertform.action" role="button" class="btn btn-success btn-xs"
					id="btnAdd" style="vertical-align: bottom;">학생 성적 추가</a>
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
							<th>전화번호</th>
							<th>성정 입력 여부</th>
							<th>수정 및 삭제</th>
						</tr>
					</thead>
					
					<tbody>
						<!--  
						<tr>
							<td>1</td>
							<td>곽유진</td>
							<td>010-9999-8888</td>
							<td>0</td>
							<td>
								<button type="button" class="btn btn-success">수정</button>
								<button type="button" class="btn btn-danger">삭제</button>
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>윤영식</td>
							<td>010-7777-6666</td>
							<td>1</td>
							<td>
								<button type="button" class="btn btn-success">수정</button>
								<button type="button" class="btn btn-danger">삭제</button>
							</td>
						</tr>
						-->
						
						<c:forEach var="student" items="${list }">
						<tr>
							<td>${student.sid}</td>
							<td>${student.name}</td>
							<td>${student.tel}</td>
							
							
							<!--  <td>1</td> -->
							<c:if test="${student.sub == 0 }">
								<td>X</td>
							</c:if>
							<c:if test="${student.sub == 1 }">
								<td>O</td>
							</c:if>
							
							<td>
								<button type="button" class="btn btn-success" value="${student.sid}">수정</button>
								<button type="button" class="btn btn-danger" value="${student.sid}">삭제</button>
							</td>
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