package me.unc.springboottest.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {

	@RequestMapping("/")
	public String index(Model model) {
		System.out.println("IndexController index方法被调.....");
		//根据application配置文件的Thymeleaf配置，将返回resources/templates/index.html
		return "index";
	}
	
}
