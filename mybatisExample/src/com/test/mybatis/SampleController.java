/*====================
 * SampleController.java
 * -컨트롤러
 ====================*/

package com.test.mybatis;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController
{
	@RequestMapping(value = "hello.action",method = RequestMethod.GET)
	public String hello(Model model) {
		
		model.addAttribute("message", "Hello Spring annotation + MVC + mybatis Framwork");
		

		return "/WEB-INF/view/hello.jsp";
	}
}
