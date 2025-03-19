<%@page import="java.util.ArrayList"%>
<%@page import="com.test.WeatherDAO"%>
<%@page import="com.test.WeatherDTO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	//코드 추가
	//사용자가 선택한 지역 데이터 수신
	String stnId = request.getParameter("stnId");

	if(stnId==null){
		
		stnId = "108"; //--날씨 정보 전국
		
	}

	
	StringBuffer sb = new StringBuffer();
	WeatherDAO dao = new WeatherDAO(stnId);

	//타이틀
	String title = dao.weatherTitle();
	
	//육상 중기 예보
	String weatherInfo = dao.weatherInfo();
	
	//도시별 날짜 시간별 날씨 정보
	ArrayList<String> cityList =  dao.weathCityList();
	for(int i = 0 ; i<cityList.size();i++){
		
		sb.append(String.format("<h3>%s</h3>",cityList.get(i)));
		
		ArrayList<WeatherDTO> weatherList =  dao.weatherList(String.valueOf(i));
		
		//테이블 동적 생성
		sb.append("<table class='table'>");
		
		sb.append("<thead>");
		sb.append("<tr>");
		sb.append("<th>날짜</th>");
		sb.append("<th>날씨</th>");
		sb.append("<th>최저/최고 기온</th>");
		sb.append("<th>강수확률</th> ");
		sb.append("</tr>");
		sb.append("</thead>");
		for(WeatherDTO w : weatherList ){
			sb.append("<tr>");
			sb.append(String.format("<td>%s</td>",w.getTmEf()));
			sb.append("<td>");
			sb.append(String.format("<img src='images/%s' />%s",w.getImg(), w.getWf()));
			sb.append("</td>");
			sb.append(String.format("<td>%s℃ / %s℃</td>",w.getTmn(),w.getTmx()));
			sb.append(String.format("<td>%s%%</td>",w.getRnSt()));
			sb.append("</tr>");
		}
		
		
		sb.append("<tbody>");
		
		sb.append("</tbody>");
		
		sb.append("</table>");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기상청 육상 중기 예보(Weather.jsp)</title>
<link rel="stylesheet" type="text/css" href="css/main.css">

<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />

<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript"
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<script type="text/javascript">
	$(document).ready(function(){
		
		//$(":radio:eq()").attr("checked","checked");
		
		$(":radio[value='<%=stnId %>']").attr("checked","checked");
		
	});
</script>
</head>
<body>

	<!--  
 stnId= 108 전국
 stnId= 109 서울 ,경기
 stnId= 105 강원
 stnId= 131 충북
 stnId= 133 충남
 stnId= 146 전북
 stnId= 156 전남
 stnId= 143 경북
 stnId= 159 경남
 stnId= 184 제주특별자치도
-->


	<div class="container">
		<h2>
			기상 정보 <small>중기 예보</small>
		</h2>

		<div class="panel-group" role="group">

			<div class="panel panel-default" role="group">
				<div class="panel-heading">지역 선택</div>
				<div class="panel-body">
				
					<!-- action 속성 생략 → 페이지 요청 및 데이터 수신처는 자기 자신 -->
					<form action="" method="get" role="form">
						<input type="radio" name="stnId" value="108" checked="checked" />전국 
						<input type="radio" name="stnId" value="109" /> 서울 ,경기 
						<input type="radio" name="stnId" value="105" /> 강원 
						<input type="radio" name="stnId" value="131" /> 충북 
						<input type="radio" name="stnId" value="133" /> 충남 
						<input type="radio" name="stnId" value="146" /> 전북 
						<input type="radio" name="stnId" value="156" /> 전남 
						<input type="radio" name="stnId" value="143" /> 경북 
						<input type="radio" name="stnId" value="159" /> 경남 
						<input type="radio" name="stnId" value="184" /> 제주특별자치도

						<button type="submit" class="btn btn-default">Submit</button>
					</form>

				</div>

			</div>
			<!--.panel.panel-default  -->
			<br />

			<div class="panel panel-default" role="group">
				<div class="panel-heading">기상 정보 출력</div>
				<div class="panel-body">
					<p>
						<!--  <b>서울,경기도 육상 중기예보 - 2025년 03월 19일 (수)요일 06:00 발표</b>-->
						<b><%=title%></b>
					</p>
					<!-- 
					<p>
						○ (하늘상태) 이번 예보기간에는 구름많거나 흐린 날이 많겠습니다.<br /> 
						○ (기온) 이번 예보기간 아침 기온은 1~9도, 낮 기온은 11~21도로 평년(최저기온 -1~4도, 최고기온 11~15도)과 비슷하거나 높겠습니다.<br />
						○ (해상) 서해중부해상의 물결은 25일(화) 1.0~3.0m로 높게 일겠고, 그 밖의 날은 0.5~2.0m로 일겠습니다.<br /> 
						○ (주말전망) 22일(토)~23일(일)은 대체로 맑겠습니다. 아침 기온은 2~7도, 낮 기온은 13~21도가 되겠습니다.
					</p>
					-->
					<p>
						<span style="font-size: 10pt;">
							<%=weatherInfo %>
						</span>
					</p>
<!--  
					<h3>서울</h3>
					<table class="table">
						<thead>
							<tr>
								<th>날짜</th>
								<th>날씨</th>
								<th>최저/최고 기온</th>
								<th>강수확률</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>2025-03-23 00:00</th>
								<th>맑음</th>
								<th>6 ~ 19</th>
								<th>10</th>
							</tr>
							<tr>
								<th>2025-03-23 12:00</th>
								<th>흐림</th>
								<th>6 ~ 19</th>
								<th>10</th>
							</tr>
							<tr>
								<th>2025-03-24 00:00</th>
								<th>맑음</th>
								<th>6 ~ 20</th>
								<th>10</th>
							</tr>
							<tr>
								<th>2025-03-23 12:00</th>
								<th>맑음</th>
								<th>15 ~ 21</th>
								<th>10</th>
							</tr>
						</tbody>
					</table>
					<h3>인천</h3>
					<table class="table">
						<thead>
							<tr>
								<th>날짜</th>
								<th>날씨</th>
								<th>최저/최고 기온</th>
								<th>강수확률</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>2025-03-23 00:00</th>
								<th>맑음</th>
								<th>6 ~ 19</th>
								<th>10</th>
							</tr>
							<tr>
								<th>2025-03-23 12:00</th>
								<th>흐림</th>
								<th>6 ~ 19</th>
								<th>10</th>
							</tr>
							<tr>
								<th>2025-03-24 00:00</th>
								<th>맑음</th>
								<th>6 ~ 20</th>
								<th>10</th>
							</tr>
							<tr>
								<th>2025-03-23 12:00</th>
								<th>맑음</th>
								<th>15 ~ 21</th>
								<th>10</th>
							</tr>
						</tbody>
					</table>
-->

					<%=sb.toString() %>

				</div>
				<!--.panel-body  -->

			</div>
			<!--.panel.panel-default  -->

		</div>
		<!--.panel-group  -->
	</div>

</body>
</html>