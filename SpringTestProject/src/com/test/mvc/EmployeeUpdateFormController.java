/*=========================
 * HelloCotroller.java
 * -사용자 정의 컨트롤러
 * -직원 데이터 수정 폼 요청에 대한 액션 처리
 * 
=========================*/

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//※ Spring 의 Controller 인터페이스를 구형하는 방법을 통해
//	사용자 정의 컨트롤러 클래스를 구성한다.

public class EmployeeUpdateFormController implements Controller
{

	// EmployeeInsertFormController 구성했을 때 와는 다른 방식으로 처리
	// EmployeeInsertFormController 의 경우에는
	// 필요한 모든 메소드를 EmployeeDAO에 정의해 두었던 상태
	
	//주요 속성
	private IEmployeeDAO employeeDao;
	private IDepartmentDAO departmentDao;
	private IRegionDAO regionDao;
	private IPositionDAO positionDao;
	

	public void setEmployeeDao(IEmployeeDAO employeeDao)
	{
		this.employeeDao = employeeDao;
	}


	public void setDepartmentDao(IDepartmentDAO departmentDao)
	{
		this.departmentDao = departmentDao;
	}



	public void setRegionDao(IRegionDAO regionDao)
	{
		this.regionDao = regionDao;
	}



	public void setPositionDao(IPositionDAO positionDao)
	{
		this.positionDao = positionDao;
	}



	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// Controller 가 수행해야 할 액션 코드
		
		ModelAndView mav = new ModelAndView();
		
		ArrayList<Region> regionList = new ArrayList<Region>();
		ArrayList<Position> positionList = new ArrayList<Position>();
		ArrayList<Department> departmentList =new ArrayList<Department>();
		
		try
		{
			//이전 페이지로(→ EmployeeList.jsp) 부터 넘어온 데이터 수신
			//--employeeId
			String employeeId =  request.getParameter("employeeId");
			
			Employee employee = employeeDao.searchId(employeeId);
			
			regionList = regionDao.list();
			positionList = positionDao.list();
			departmentList = departmentDao.list();
			
			
			mav.addObject("employee",employee);
			mav.addObject("regionList",regionList);
			mav.addObject("positionList",positionList);
			mav.addObject("departmentList",departmentList);
			
			mav.setViewName("/WEB-INF/view/EmployeeUpdateForm.jsp");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	
	
		
		
		return mav;
	}
	
}