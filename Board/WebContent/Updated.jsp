<%@page import="com.test.BoardDTO"%>
<%@page import="com.util.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.test.BoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>

<%
	String status = request.getParameter("status");
	String  strNum = request.getParameter("num");
	int num = Integer.parseInt(strNum);

	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	
	Connection conn = DBConn.getConnection();
	BoardDAO dao = new BoardDAO(conn);
	
	BoardDTO board = dao.getReadData(num);

	String emaliStr = "";
	if(board.getEmail() != null)
	{
		emaliStr = board.getEmail();
	}
	
	dao.getClass();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Updated.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/created.css"> 
<script type="text/javascript" src ="<%=cp%>/js/util.js"></script>

<script type="text/javascript">
	
	function sendIt()
	{
		//확인 
		//alert("확인");
		
		var f = document.myForm;
		
		//제목 입력 확인
		//필수 입력 항목 기재여부 확인 및 공백 처리
		var str = f.subject.value;
		str = str.trim();
		
		//※ javascript 의 trim()
		//IE 8.0 이후부터 사용 가능
		
		//확인
		//alert("|" + str +"|");
		
		if (!str)
		{
			alert("\n제목을 입력하세요");
			f.subject.focus();
			return;
		}
		//----------------------------------------제목 입력 확인
		
		//이름 입력 확인
		str = f.name.value;
		str = str.trim();
		
		if (!str)
		{
			alert("\n작성자 이름을 입력하세요");
			f.name.focus();
			return;
		}
		//----------------------------------------이름 입력 확인
		
		//이메일 검사
		//이메일은 필수 입력 항목이 아니므로 선택적인 입력이 가능하지만
		//입력을 한 상황이라면, 이메일 형식에 맞도록 입력했는지
		//확인이 필요한 데이터이므로 이에 대한 처리
		
		if (f.email.value)
		{
			if (!isValidEmail(f.email.value))
			{
				alert("\n정상적인 이메일 형식을 입력하세요");
				f.email.focus();
				return;
			}
			
		}
		//----------------------------------------이메일 검사 확인
		
		
		//내용 입력 확인---------------------------
		//필수 입력 항목 기재 여부 확인 및 공백 처리
		str = f.content.value;
		str =  str.trim();
		
		if (!str)
		{
			alert("\n내용을 입력하세요");
			f.content.focus();
			return;
			
		}
		//----------------------------------------내용 입력 확인
		
		//패스워드 입력 확인
		str = f.pwd.value;
		str = str.trim();
		
		if (!str)
		{
			alert("\n패스워드를 입력하세요");
			f.pwd.focus();
			return;
		}
		//----------------------------------------패스워드 입력 확인
		
		f.action = "<%=cp%>/Update_ok.jsp?num=<%=num%>&pageNum=<%=pageNum%>";
		
		if (f.pwd.value != f.pwdSource.value)
		{
			alert("\n패스워드를 잘못입력하셨습니다.");
			f.pwd.focus();
			return;
		}
		
		f.submit();
	}
	
	function removeIt()
	{
		
		//alert("함수 호출");
		
		var f = document.myForm;
		
		//패스워드 입력 확인
		str = f.pwd.value;
		str = str.trim();
		
		if (!str)
		{
			alert("\n패스워드를 입력하세요");
			f.pwd.focus();
			return;
		}
		
		f.action = "<%=cp%>/Delete_ok.jsp?num=<%=num%>&pageNum=<%=pageNum%>";
		
		f.submit();
	}
</script>

</head>
<body>

<div id="bbs">
	<div id="bbs_title">
		게 시 판 (JDBC 연동 버전)
	</div><!--bbs_title  -->
	
	<form action="" method="post" name="myForm">
		<div id="bbsCreated">
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt>제&nbsp;&nbsp;&nbsp;&nbsp;목</dt>
					<dd>
					<%
					if(status.equals("1"))
					{
					%>
						<input type="text" name ="subject" class="boxTF" maxlength="100" 
						style="width: :414px;" value="<%=board.getSubject()%>">
					<%
					}
					else
					{
					%>
						<input type="text" name ="subject" class="boxTF" maxlength="100" 
						style="width: :414px;" value="<%=board.getSubject()%>" disabled="disabled">
					<%
					}
					%>
					</dd>
				</dl>
			</div><!-- .bbsCreated_bottomLine -->
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt>작 성 자</dt>
					<dd>
					<%
					if(status.equals("1"))
					{
					%>
						<input type="text" name ="name" class="boxTF" maxlength="20"
						style="width: 414px;" value="<%=board.getName() %>">
					<%
					}
					else
					{
					%>
						<input type="text" name ="name" class="boxTF" maxlength="20"
						style="width: 414px;" value="<%=board.getName() %>" disabled="disabled">
					<%
					}
					%>
					</dd>
				</dl>
			</div><!-- .bbsCreated_bottomLine -->
			
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt>비공개 여부</dt>

					<dd>
					<%
					if(status.equals("1"))
					{
					%>
						<input type="radio" name ="secret" id="secret1" class="boxTF" >
						<label for="secret1" value="yes">yes</label>
						<input type="radio" name ="secret" id="secret2" class="boxTF" >
						<label for="secret2" value="no">no</label>
					
					<%
					}
					else
					{
					%>
						<input type="radio" name ="secret" id="secret1" class="boxTF" >
						<label for="secret1" value="yes" >yes</label>
						<input type="radio" name ="secret" id="secret2" class="boxTF" >
						<label for="secret2" value="no">no</label>
					
					<%
					}
					%>
					</dd>
				</dl>
			</div><!-- .bbsCreated_bottomLine -->
			
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt>이 메 일</dt>
					<dd>
					<%
					if(status.equals("1"))
					{
					%>
						<input type="email" name ="email" class="boxTF" maxlength="50"
						style="width: 414px;value="<%=emaliStr %>">
					<%
					}
					else
					{
					%>
						<input type="email" name ="email" class="boxTF" maxlength="50"
						style="width: 414px;value="<%=emaliStr %>" disabled="disabled">
					<%
					}
					%>
					</dd>
				</dl>
			</div><!-- .bbsCreated_bottomLine -->
			
			
			
			<div id="bbsCreated_content">
				<dl>
					<dt>내&nbsp;&nbsp;&nbsp;&nbsp;용</dt>
					<dd>
					<%
					if(status.equals("1"))
					{
					%>
						<textarea rows="12" cols="63" name="content" class="boxTA"><%=board.getContent() %></textarea>
					<%
					}
					else
					{
					%>
						<textarea rows="12" cols="63" name="content" class="boxTA" disabled="disabled"><%=board.getContent() %></textarea>
					<%
					}
					%>	
					</dd>
				</dl>
			</div><!--#bbsCreated_content  -->
			
			<div class="bbsCreated_noLine">
				<dl>
					<dt>패스워드</dt>
					<dd>
						<input type="hidden" name="pwdSource" value="<%=board.getPwd()%>"/>
						<input type="password" name ="pwd" class="boxTF" maxlength="10">
						&nbsp;<span style="font-size: 6pt;">(게시물 수정 삭제 시 필요)</span>
					</dd>
				</dl>
			</div><!--.bbsCreated_noLine  -->
			
			<div id="bbsCreated_footer">
			<%
			if(status.equals("1"))
			{
			%>
				<input type="hidden" name="pageNum" value="<%=pageNum%>"/>
				<input type="button" value ="수정하기" class="btn2" onclick="sendIt()">
				<input type="reset" value ="다시입력" class="btn2" >
				<input type="button"  value ="작성취소" class="btn2" 
				onclick="javascript:location.href='<%=cp%>/List.jsp?pageNum=<%=pageNum%>'">
			<%
			}
			else
			{
			%>
				<input type="button" value ="삭제하기" class="btn2" onclick="removeIt()">
				<input type="button"  value ="삭제취소" class="btn2" 
				onclick="javascript:location.href='<%=cp%>/List.jsp?pageNum=<%=pageNum%>'">
			<%
			}
			%>	
			</div><!--#bbsCreated_footer  -->
		</div>
	</form>
</div><!--#bbs  -->

</body>
</html>