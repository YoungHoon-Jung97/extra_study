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

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">


<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- jquery -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


</head>
<body>

	<div>
		<h1>회원 정보</h1>
		<hr />
	</div>

	<div class="container">
		<div id="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading" id="title">회원 정보 입력</div>

				<div class="panel-body">
					<form action="memberinsert.action" method="post" role="form">
						<div id="form-group">
							<label for="name"> NAME : </label>
						</div>
						<input type="text" class="form-control" id="name" name="name" />

						<div id="form-group">
							<label for="telephone"> TELEPHONE : </label>
						</div>
						<input type="tel" class="form-control" id="telephone"
							name="telephone" />

						<button type="submit" class="btn btn-default btn btn-sm">SUBMIT</button>
						<button type="reset" class="btn btn-default btn btn-sm btnCancel">CANCEL</button>
					</form>
				</div>

			</div>

			<div class="panel panel-default">

				<div class="panel-heading">회원 정보 출력</div>

				<div class="panel-body">
					<table class="table">
						<thead>
							<tr>
								<th>MID</th>
								<th>NAME</th>
								<th>TELEPHONE</th>
								<th>삭제 / 수정</th>
							</tr>
						</thead>
						<tbody>

						<!--  <tr>
								<td>1</td>
								<td>정영훈</td>
								<td>010-1234-1234</td>
								<td>
									
									<button type="button" class="btn btn-default btn-xs btnDelete">삭제</button>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td>김영훈</td>
								<td>010-2233-1234</td>
								<td>
								
									<button type="button" class="btn btn-default btn-xs btnDelete">삭제</button>
								</td>
							</tr>-->
							
							<c:forEach var="member" items="${list }">
								<tr>
									<td>${member.mid }</td>
									<td>${member.name }</td>
									<td>${member.telephone }</td>
									<td>
										<button type="button" class="btn btn-default btn-xs btnDelete"
										value="${member.mid }">삭제</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<button type="button" class="btn btn-default btn-sm" role="badgeFrame">
						Count <span class="badge" role="badge">${count }</span>
					</button>

				</div>

			</div>


		</div>
	</div>

</body>
</html>