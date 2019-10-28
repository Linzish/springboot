package me.unc.springboottest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("LoginController loginForm方法被调用.....");
		return "loginForm";
	}
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("loginname") String loginname, 
			@RequestParam("password") String password, ModelAndView mv) {
		System.out.println("LoginController login方法被调用.....");
		System.out.println("LoginController 登录名：" + loginname + "密码：" + password);
		mv.setViewName("redirect:/main");
		return mv;
	}
	
}
