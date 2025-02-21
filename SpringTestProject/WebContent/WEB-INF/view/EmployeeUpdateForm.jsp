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
<title>EmployeeUpdateForm.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/jquery-ui.css">
<style>
    /* 테이블 크기 조정 */
    table.table {
        width: 50%; /* 테이블 너비를 50%로 설정 */
        border-collapse: collapse; /* 테이블 경계선 중복 제거 */
       
    }
    

</style>

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp%>/js/jquery-ui.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		//AJAX 요청 및 응답 처리
		ajaxRequest();
		
		$("#birthday").datepicker(
				{
					dateFormat: "yy-mm-dd"
					, changeMonth: true
					, changeYear: true
		});
		
		
		//직위(select)의 선택된 내용이 변경되었을 경우 수행해야 할 코드 처리
		$("#positionId").change(function(){
			
			
			
			//alert("확인");
			//AJAX 요청 및 응답 처리
			ajaxRequest();
			
			 
			
		});
		
		$("#submitBtn").click(function(){
			
			//alert("확인");
			//데이터 검사
			//-- 누락된 입력값이 있는지 없는지에 대한 여부 확인
			//	이름, 주민번호앞, 주민번호 뒷, 생년월일, 전화번호, 기본급 항목 누락 시
			//	→ 필수 입력 항목이 누락되었음을 안내
			
			//최소 기본급에 대해 유효성 검사
			//-- 직급별 최소 기본급 > 사용자 입력 기본급
			//	→ 입력한 기본급이 최소 기본급보다 작음을 안내
			
			//폼 submit 액션 처리
			
			dataMissing();
			
		});
		
	});
	
	function dataMissing(){
		
		var name = $("#name").val();
		var ssn1 = $("#ssn1").val();
		var ssn2 = $("#ssn2").val();
		var birthday = $("#birthday").val();
		var telephone = $("#telephone").val();
		var basicPay = $("#basicPay").val();
		
		var minBasicPay = $("#minBasicPay").text();
		
		
		if (name == "" || ssn1 == "" || ssn2 == "" || birthday == "" || telephone == "" || basicPay == "") {
		    $("#error").html("필수정보를 입력하세요");
		    $("#error").css('display', 'block');
		    return;
		 
		}

		 if (parseInt(minBasicPay) > parseInt(basicPay)) {
		        $("#error").html("기본급이 최소 기본급보다 적습니다.");
		        $("#error").css('display', 'block');
		        return;  // 오류가 있으면 함수 종료, submit 하지 않음
		 }
		
		$("#employeeForm").submit();
		
		
	}
	
	
	function ajaxRequest(){
		
		//alert("응답처리");
		
		//『$.post()』/『$.get()』
		//--jQuery 에서 AJAX 를 사용해야 할 경우 지원해주는 함수
		//	(서버 측에서 요청한 데이터를 받아오는 기능의 함수)
		
		//※ 위 함수의 사용 방법 (방식)
		//--『$.post(요청주소, 전송데이터, 응답액션처리)』
		//	.요청주소(url)
		//	→ 데이터를 요청할 파일에 대한 정보
		//	.정보 데이터(data)
		//	→서버 측에 요청하는 과정에서 내가 전달할 파라미터
		//	.응답액션처리(function)
		//	→응답을 받을 수 있는 함수(기능 처리)
		
		//※ 참고로, 전송데이터 는 파라미터의 데이터타입을 그대로 취하게 되므로
		//	html 이든 문자열이든 상관없다.
		
		$.post("ajax.action",{positionId: $("#positionId").val()},function(data){
			
			$("#minBasicPay").html(data);
		});
	}

</script>

</head>
<body>

<!-------------------------------------------------------- 
	#21. EmployeeInsertForm.jsp
	     - 직원 데이터 입력 페이지
	     - 관리자만 접근 허용
--------------------------------------------------------->

<div>

	<!--  메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>

	<!-- 콘텐츠 영역 -->
	<div id="content">
	
		<h1>[ 직원 변경 ]</h1>
		<hr>
		
		<form action="employeeupdate.action" method="post" id="employeeForm">
			<table class="table">
				<!-- 기존 입력 폼과 비교했을 때 주가되는 항목 -->
				<!--사원 번호 -->
				<tr>
					<th>사원번호</th>
					<td>
						<input type="text" id="employeeId" name="employeeId" readonly="readonly"/>
					</td>
				</tr>
			
				<tr>
					<th>이름</th>
					<td>
						<input type="text" id="name" name="name" placeholder="이름" >
					</td>
				</tr>
				<tr>
					<th>주민번호</th>
					<td>
						<input type="text" id="ssn1" name="ssn1" style="width: 100px;"
						placeholder="앞 6자리"> - 
						<input type="password" id="ssn2" name="ssn2" style="width: 110px;"
						placeholder="뒤 7자리">
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<input type="text" id="birthday" name="birthday" placeholder="생년월일">
					</td>
				</tr>				
				<tr>
					<th>양/음력</th>
					<td>
						<input type="radio" value="0" name="lunar" id="lunar0" checked="checked" />
						<label for="lunar0">양력</label>
						<input type="radio" value="1" name="lunar" id="lunar1"/>
						<label for="lunar1">음력</label>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="tel" id="telephone" name="telephone" placeholder="전화번호">
					</td>
				</tr>
				<tr>
					<th>지역</th>
					<td>
						<select id="regionId" name="regionId">
			
						<!-- 	
							<option value="1">관악구</option>
							<option value="2">마포구</option>
							<option value="3">은평구</option>
							<option value="4">도봉구</option>  
						-->
						<c:forEach var="region" items="${regionList }">
							<option value="${region.regionId}">${region.regionName}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>부서</th>
					<td>
						<select id="departmentId" name="departmentId">
							<!--
							<option value="1">축구부</option>
							<option value="2">야구부</option>
							<option value="3">수영부</option>
							<option value="4">원예부</option>
							<option value="5">독서부</option> 
							-->
							<c:forEach var="department" items="${departmentList }">
								<option value="${department.departmentId}">${department.departmentName}</option>
							</c:forEach>			
						</select>
					</td>
				</tr>
				<tr>
					<th>직위</th>
					<td>
						<select id="positionId" name="positionId">

							
						<!-- 	<option value="1">팀장</option>
							<option value="2">기술고문</option>
							<option value="3">팀원</option>
							<option value="4">탈영병</option>
							<option value="5">엑스맨</option>   -->
							
							<c:forEach var="position" items="${positionList}">
    							<option value="${position.positionId}">${position.positionName}</option>
							</c:forEach>
							
						</select>
					</td>
				</tr>
				<tr>
					<th>기본급</th>
					<td>
						<input type="text" id="basicPay" name="basicPay">
						(최소 기본급 <span id="minBasicPay"
						style="color: red; font-weight: bold;">0</span>원)
					</td>
				</tr>
				<tr>
					<th>수당</th>
					<td>
						<input type="text" id="extraPay" name="extraPay">
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<br><br>
						
						<button type="button" class="btn" id="submitBtn"
						style="width: 40%; height: 50%;">직원 추가</button>
						<button type="button" class="btn" id="listBtn"
						style="width: 40%; height: 50%;" onclick="location.href='employeelist.action'">직원 리스트</button>
						<br><br>
						
						<span id="error" style="color: red; font-weight: bold; display: none;">
						</span>
						
					</td>
				</tr>
				
			</table>
		</form>
	
	</div>

	<!-- 회사 소개 및 어플리케이션 소개 영역 -->
	<div id="footer">
	</div>

</div>

</body>
</html>