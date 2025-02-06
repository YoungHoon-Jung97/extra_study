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
			var params="title="+$.trim($("#title").val())
						+"content="+$.trim($("#content").val());
			
			$.ajax(
			{
				type:"POST"
				,url:"ajaxtest04ok.do"
				,data:params
				,dataType:"xml"
				,success:function(args)
				{
					var out = "";
					
					$(args).find("comtent").each(function()
					{
						var item = $(this);
						var num = item.attr("num");
						var title = item.find("title").text();
						var content = item.find("content").text();
						
						out += "<br>===============================";
		    			out += "<br> -id: " +num;
		    			out += "<br> -title:" +title;
		    			out += "<br> -content: " +content;
		    			out += "<br>===============================";
						
					});
					
					$("#resultDiv").html(out);
					
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

<!--
	ajaxtest04.do → com.test.ajax.AjaxTest04Controller.java 
							→WEB-INF/View/AjaxTest04.jsp
											↓
									ajaxtest04ok.do → com.test.ajax.AjaxTest04okController.java → AjaxTest04ok.jsp

  -->


<div>
	<h1>jQuery의 ajax()활용 실습</h1>
	<p>xml control</p>
	<hr>
</div>

<div>
	제목
	<input type="text"  id="title" class="txt"/>
	내용
	<input type="text" id="content" class="txt"/>
	<input type="button"  value="등록하기" id="sendButton" class="btn"/>
</div>

<div id="resultDiv">

</div>


</body>
</html>