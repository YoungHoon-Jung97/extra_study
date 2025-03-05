/*
 * StudentController.java
 * */

package com.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

//컨트롤러 등록
@Controller
public class StudentController
{
	
	//sqlSession 을 활용하여 마이바티스 객체 의존성 (자동)
	@Autowired
	private SqlSession sqlSession;
	
	//매개변수를 들록하는 과정에서 매개변수 목록에 구성된
	// 클래스의 객체 정보는 스프링이 (스프링 컨테이너가 ) 제공한다.
	
	// 사용자의 요청 주소와 메소드를 매핑하는 과정 필요
	// @RequestMapping(value ="요청주소", methord= 전송및 요청 방식)
	
	@RequestMapping(value="/studentlist.action", method = RequestMethod.GET)
	public String studentList(Model model) {
		String result = null;
		
		 IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		 
		 model.addAttribute("list",dao.list());
		 model.addAttribute("count",dao.count());
		 
		 result = "/WEB-INF/view/StudentList.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/studentinsertform.action", method = RequestMethod.GET)
	public String studentInsertForm() {
		String result = null;
		
		result ="/WEB-INF/view/StudentInsertForm.jsp";
		
		return result;
	}
	
	@RequestMapping(value = "/studentinsert.action" ,method = RequestMethod.POST)
	public String studentInsert(StudentDTO student)
	{
		String result = null;
		
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		
		dao.add(student);
		
		result = "redirect:studentlist.action";
		
		return result;
		
	}
	
	@RequestMapping(value = "/studentupdateform.action" , method = RequestMethod.GET)
	public String studentUpdateForm(String sid , Model model)
	{
		
		String result = null;
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		
		
		model.addAttribute("student",dao.search(sid));
		
		
		
		result ="/WEB-INF/view/StudentUpdateForm.jsp";
		return result;
	}
	
	@RequestMapping(value = "/studentupdate.action" , method = RequestMethod.POST)
	public String studentUpdateForm(StudentDTO student)
	{
		
		String result = null;
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		
		
		dao.modify(student);
		
		
		result ="redirect:studentlist.action";
		return result;
	}

	
	@RequestMapping(value = "/studentdelete.action" ,method = RequestMethod.GET)
	public String studentDelete(String sid,Model model) 
	{
		String result= null;
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		
		String message = "";
		if(dao.search(sid).getSub() == 0)
		{
			
			dao.remove(sid);
			model.addAttribute("message",message);
			result="redirect:studentlist.action";
		}
		else
		{
			message="삭제할 수 없습니다.";
			model.addAttribute("message",message);
			result="redirect:studentlist.action";
		}
		
		return result;
			
	}
}
