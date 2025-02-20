<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">

</head>
<body>

<!--
	#15
	-ElployeeList.jsp
	-직원 리스트 출력 페이지
	-관리자가 접근하는 직원 데이터 출력 페이지
		→ 관리 기능 포함
		→ 일반 직원이 접근하는 직원 데이터 출력 페이지
			EmpList.jsp 로 구성할 예정
  -->

<div>
	<!--메뉴 영역  -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	<!--콘텐츠 영역  -->
	<div>
		<h1>
			[직원 관리 (관리자 전용)]
		</h1>
		<hr />
		<div>
			<form action="">
				<input type="button" class="btn"  value="직원 추가"
				onclick="location.href='employeeinsertform.action'"/>
			</form>
		</div>
		<br /><br />
		<!--

EMPLOYEEID NAME SSN BIRTHDAY LUNAR LUNARNAME TELEPHONE DEPARTMENTID  
DEPARTMENTNAME POSITIONID POSITIONNAME REGIONID REGIONNAME BASICPAY                    
EXTRAPAY PAY GRADE                 
		
		  -->
		  <table id="customers" class="table">
		  	<tr>
		  		<th>번호</th>
		  		<th>이름</th>
		  		<th>주민번호</th>
		  		<th>생년월일</th>
		  		<th>양음력</th>
		  		<th>전화번호</th>
		  		<th>지역</th>
		  		<th>부서</th>
		  		<th>직위</th>
		  		<th>기본급</th>
		  		<th>수당</th>
		  		<th>급여</th>
		  		<th>등급</th>

				<th>수정</th>
				<th>삭제</th>
				
			</tr>
				<c:forEach var="employee" items="${employeeList }">
				<tr>
					<td>${employee.employeeId }</td>
		  			<td>${employee.name }</td>
		  			<td>${employee.ssn }-*******</td>
			  		<td>${employee.birthday }</td>
			  		<td>${employee.lunarName }</td>
			  		<td>${employee.telephone }</td>
			  		<td>${employee.regionName }</td>
			  		<td>${employee.departmentName }</td>
			  		<td>${employee.positionName }</td>
			  		
			  		<%-- <td>${employee.basicPay }</td> --%>
			  		<td>
			 			<fmt:formatNumber value="${employee.basicPay }"
			 			groupingUsed="true"></fmt:formatNumber>			 		
			 		</td>
			 		
			 		<%-- <td>${employee.extraPay }</td> --%>
			 		<td>
			 			<fmt:formatNumber value="${employee.extraPay }"
			 			groupingUsed="true"></fmt:formatNumber>
		 			</td>
			 		
			 		<%-- <td>${employee.pay }</td> --%>
			 		<td>
			 			<fmt:formatNumber value="${employee.pay }"
			 			groupingUsed="true"></fmt:formatNumber>
			 		</td>
			 		
			 		<%-- <td>${employee.grade }</td> --%>
			 		<td>${employee.grade == 0 ? "관리자" : "일반회원" }</td>
			  		<td><button type="button" class="btn">수정</button></td>
					<td><button type="button" class="btn">삭제</button></td>
				</tr>
				</c:forEach>
			
		  	<!-- 
		  	
		  	 <tr>
		  		<td>1</td>
		  		<td>박세진</td>
		  		<td>720527</td>
		  		<td>1972-05-27</td>
		  		<td>양력</td>
		  		<td>010-1255-2451</td>
		  		<td>서울</td>
		  		<td>개발부</td>
		  		<td>사원</td>
		  		<td>1,500,000</td>
		  		<td>1,000,000</td>
		  		<td>2,500,00</td>
		  		<td>일반사원</td>

				<td><button type="button" class="btn">수정</button></td>
				<td><button type="button" class="btn">삭제</button></td>
		  	</tr>
		  	
		  		<tr>
		  		<td>2</td>
		  		<td>안석창</td>
		  		<td>990220</td>
		  		<td>1999-02-20</td>
		  		<td>음력</td>
		  		<td>010-6658-1548</td>
		  		<td>서울</td>
		  		<td>인사부</td>
		  		<td>사원</td>
		  		<td>1,500,000</td>
		  		<td>1,000,000</td>
		  		<td>2,500,00</td>
		  		<td>일반사원</td>

				<td><button type="button" class="btn">수정</button></td>
				<td><button type="button" class="btn">삭제</button></td>
		  	</tr>
		  	
		  	</tr>
		  	
		  		<tr>
		  		<td>3</td>
		  		<td>정영훈</td>
		  		<td>970906</td>
		  		<td>1997-09-06</td>
		  		<td>양력</td>
		  		<td>010-6520-1548</td>
		  		<td>인천</td>
		  		<td>개발부</td>
		  		<td>대리</td>
		  		<td>2,500,000</td>
		  		<td>2,000,000</td>
		  		<td>3,500,00</td>
		  		<td>관리자</td>

				<th><button type="button" class="btn">수정</button></th>
				<th><button type="button" class="btn">삭제</button></th>
		  	</tr> -->
		  </table>
		
	</div>
</div>

</body>
</html>