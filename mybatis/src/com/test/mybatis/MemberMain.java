/*====================
 * MemberMain.java
 * -컨트롤러
====================*/

package com.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberMain
{
	//mybatis 객체 활용
	@Autowired
	private SqlSession sqlSession;
	//--객체 의존성 (자동) 주입
	
	@RequestMapping(value="/memberlist.action" ,method = RequestMethod.GET)
	public String memberList(Model model)
	{
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		//dao.count();
		//dao.list();
		
		model.addAttribute("count" ,dao.count());
		model.addAttribute("list", dao.list());
		
		return "/WEB-INF/view/MemberList.jsp";
	}
	
	@RequestMapping(value="/memberinsert.action", method = RequestMethod.POST)
	public String memberInsert(MemberDTO member)
	{
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		dao.add(member);
		return "redirect:/memberlist.action";
		
	}
}
