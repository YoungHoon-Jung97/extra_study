<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>userInformationForm.jsp</title>
</head>
<body>

<div class="content">
	<form id="join_form" class="form" method="get" action=""></form>
	
	<div class="join_content">
		<div class="section_body">
			<div class="member_field_wrap">
			
				<div class="member_input_field">
					<label class="join_email" for="input_email">
						<span class="email_image">
							<i class="email_input_image"></i>
						</span><!-- 이메일 이미지 -->
						<span class="email_input_form">
							<input type="email" class="email_input_form" id="email"
							 placeholder="아이디(이메일)" maxlength="100"/>
						</span>
					</label><!-- .join_email -->
					<div class="email_error">
						<p>이메일 을 입력하세요</p>
					</div><!-- .email_error  -->	
				</div><!-- .member_input_field   -->
				
				<div class="member_input_field">
					<label class="join_password" for="input_password">
						<span class="password_image">
							<i class="password_input_image"></i>
						</span><!-- 패스워드 이미지 -->
						<span class="possword_input_form">
							<input type="password" class="password_input_form" id="pwd1"
							 placeholder="비밀번호" maxlength="20" minlength="8"/>
						</span>
					</label><!-- .join_password -->
					<div class="password_error">
						<p>영문/숫자/특수문자 2가지 이상 조합 (8~20)</p>
						<p>아이디 제외</p>
					</div><!-- .password_error  -->	
					
				</div><!-- .member_input_field   -->
				
				
				<div class="member_input_field">
					<label class="join_password" for="input_password">
						<span class="password_image">
							<i class="password_input_image"></i>
						</span><!-- 패스워드 이미지 -->
						<span class="possword_input_form">
							<input type="password" class="password_input_form" id="pwd2"
							 placeholder="비밀번호" maxlength="20" minlength="8"/>
						</span>
					</label><!-- .join_password -->
					<div class="password_error">
						<p>영문/숫자/특수문자 2가지 이상 조합 (8~20)</p>
						<p>아이디 제외</p>
					</div><!-- .password_error  -->	
					
				</div><!-- .member_input_field   -->
				
				<div class="member_input_field">
					<label class="join_name" for="input_name">
						<span class="name_image">
							<i class="name_input_image"></i>
						</span><!-- 이름 이미지 -->
						<span class="name_input_form">
							<input type="text" class="name_input_form" id="name"
							 placeholder="이름" maxlength="10" />
						</span>
					</label><!-- .join_password -->
					<div class="name_error">
						<p>이름을 정확히 입력하세요</p>
					</div><!-- .name_error  -->	
					
				</div><!-- .member_input_field   -->
				
				
				
			</div><!-- .member_field_wrap-->
			
		</div><!-- .form_section  -->
	</div><!-- .section_body  -->
</div> <!-- .content -->



</body>
</html>