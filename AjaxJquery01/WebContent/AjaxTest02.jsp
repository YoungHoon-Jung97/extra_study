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
<title>AjaxTest02.jsp</title>
<link rel="stylesheet" type="text/css" href="css/MemberListScore.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(function()
	{
		
		$("#sendBtn").click(function()
		{
			 var params = "name=" + $("#name").val()
				+ "&content=" + $.trim($("#content").val()); 
			
			alert("1");
		    
			$.ajax(
			{
				
			    	type:"POST"	
			    	, url:"AjaxTest02_ok.jsp"
			    	, data: params
			    	, dataType:"xml"
			    	//, async:false // 동기
			    	, success: function(args)
			    	{
			 
			    		
			    		var out ="";
			    		out =">> 게시물 갯수 : "+ $(args).find("totalDataCount").text() + "<br>";
			    		
			    		$(args).find("comment").each(function()
			    		{
			    			var item = $(this);
			    			var num = item.attr("num");
			    			var name= item.find("name").text();
			    			var content = item.find("content").text();
			    			
			    			out += "<br>===============================";
			    			out += "<br> - 번호 : " +num;
			    			out += "<br> - 이름 : " +name;
			    			out += "<br> - 내용 : " +content;
			    			out += "<br>===============================";
			    			
			    		});
			    		
			    		
			    		
			    		
			    		$("#resultDiv").html(out);
			    		
			    		$("#name").val("");
			    		$("#content").val("");
			    		
			    		$("#name").focus();
			    	}
			    	, beforeSend:function()
			    	{
		
			    		return true;
			    	}
			   		, error:function(e)
			   		{
			   			alert(e.responseTest);
			   		}
			});
		    
			
			
		});
		
	});
	
	function showRequest()
	{
		if (!$.trim($("#name").val()))
		{
			alert("이름을 입력해야 합니다.");
			$("#name").focus();
			
			return false;
		}
		
		if (!$.trim($("#content").val()))
		{
			alert("내용을 입력해야 합니다.");
			$("#content").focus();
			
			return false;
		}
		
		return true;
	}

</script>


</head>
<body>

<div>
	<h1>jQuery의 ajax()활용 실습</h1>
	<hr />
</div>

<div> 
	이름
	<input type="text" id="name" class="txt"/>
	
	내용
	<textarea id="content" cols="50" rows="3"></textarea>
	
	<input type="button"  value="등록하기" id="sendBtn" class="btn control"/>
</div>

<div id="resultDiv">
</div>

</body>
</html>