/*=========================
 * HelloCotroller.java
 * -사용자 정의 컨트롤러
 * -회원 데이터 추가 액션 처리 클래스
 * -DAO 객체에 대한 의존성 주입을 위한 준비
 * 	→ 인테페이스 형태의 속성 구성
 * 	→ setter 구성
=========================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//※ Spring 의 Controller 인터페이스를 구형하는 방법을 통해
//	사용자 정의 컨트롤러 클래스를 구성한다.

public class MemberInsertController implements Controller
{
	
	//주요 속성 구성 →인터페이스 형태
	private IMemberDAO dao;
	
	public void setDao(IMemberDAO dao)
	{
		this.dao =dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// Controller 가 수행해야 할 액션 코드
		
		ModelAndView mav = new ModelAndView();
		
		
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String tel = request.getParameter("telephone");
		
		try
		{
			MemberDTO member = new MemberDTO();
			
			member.setName(name);
			member.setTelephone(tel);
			
			dao.add(member);
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		//사용자에게 memberlist.do 를 다시 요청할 수 있도록 안내
		//sendRedirect() → memberlist.do
		mav.setViewName("redirect:memberlist.do");
	
		return mav;
	}
	
}