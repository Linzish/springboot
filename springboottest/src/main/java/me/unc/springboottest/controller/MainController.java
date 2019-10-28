package me.unc.springboottest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String main() {
		System.out.println("MainController main方法被调.....");
		//根据application配置文件的Thymeleaf配置，将返回resources/templates/main.html
		return "main";
	}
	
}
