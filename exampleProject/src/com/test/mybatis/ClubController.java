package com.test.mybatis;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClubController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="/createclub.action", method = RequestMethod.GET)
	public String startPage() {
		
		
		return "/WEB-INF/view/createClub.jsp";
		
	}
}
