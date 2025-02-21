/*=========================
 * EmployeeListController.java
 * -사용자 정의 컨트롤러
 * -리스트 페이지 요청에 대한 액션처리
 * -DAO 객체에 대한 의존성 주입을 위한 준비
 *  → 인터페이스 형태의 자료형을 속성으로 구성
 *  → setter 구성
=========================*/

package com.test.mvc;

import java.util.ArrayList;

import javax.activation.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//※ Spring 의 Controller 인터페이스를 구형하는 방법을 통해
//	사용자 정의 컨트롤러 클래스를 구성한다.

public class EmployeeListController implements Controller
{
	// 주요속성 구성 → 인터페이스
	private IEmployeeDAO dao;
	



	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// Controller 가 수행해야 할 액션 코드
		
		ModelAndView mav = new ModelAndView();
		
		ArrayList<Employee> employeeList = new ArrayList<Employee>();

		try
		{
			employeeList = dao.list();
			mav.addObject("employeeList", employeeList);
			mav.setViewName("/WEB-INF/view/EmployeeList.jsp");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	
		return mav;
	}
	
}