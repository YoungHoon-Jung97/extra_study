/*=========================
 * HelloCotroller.java
 * -사용자 정의 컨트롤러
=========================*/

package com.test.mvc;

import javax.activation.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//※ Spring 의 Controller 인터페이스를 구형하는 방법을 통해
//	사용자 정의 컨트롤러 클래스를 구성한다.

public class EmployeeInsertController implements Controller
{
	private IEmployeeDAO dao;

	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// Controller 가 수행해야 할 액션 코드

		// 이름, 주민번호앞, 주민번호 뒷, 생년월일, 전화번호,
		ModelAndView mav = new ModelAndView();

		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String lunar = request.getParameter("lunar");
		String telephone = request.getParameter("telephone");
		String departmentId = request.getParameter("departmentId");
		String positionId = request.getParameter("positionId");
		String regionId = request.getParameter("regionId");
		String basicPay = request.getParameter("basicPay");
		String extraPay = request.getParameter("extraPay");
		String ssn1 = request.getParameter("ssn1");
		String ssn2 = request.getParameter("ssn2");

		try
		{

			Employee employee = new Employee();

			employee.setName(name);
			employee.setBirthday(birthday);
			employee.setLunar(Integer.parseInt(lunar));
			employee.setTelephone(telephone);
			employee.setPositionId(positionId);
			employee.setDepartmentId(departmentId);
			employee.setRegionId(regionId);
			employee.setBasicPay(Integer.parseInt(basicPay));
			employee.setSsn1(ssn1);
			employee.setSsn2(ssn2);
			
			if (extraPay !=null)
			{
				employee.setExtraPay(Integer.parseInt(extraPay));
			}
			else
			{
				employee.setExtraPay(0);
			}

			dao.add(employee);

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}

		mav.setViewName("redirect:employeelist.action"); // 리디렉션할 URL 설정

		return mav;
	}

}