package me.unc.springboottest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import me.unc.boottest1.domain.Book;

@Controller
public class ThymeleafFunController {
	
	//测试表达式访问数据
	@RequestMapping("/regexptest")
	public String regexptest(HttpServletRequest request, HttpSession session) {
		System.out.println("ThymeleafFunController regexptest被调用.....");
		//将数据保存到request作用域
		request.setAttribute("book", "疯狂 Spring Boot 讲义");
		//将数据保存到session作用域
		session.setAttribute("school", "广东海洋大学");
		//将数据保存到ServletContext（application）作用域
		request.getServletContext().setAttribute("name", "Thymeleaf模板引擎");
		return "success1";
	}
	
	//测试条件判断
	@RequestMapping("/iftest")
	public String iftest(WebRequest webRequest) {
		System.out.println("ThymeleafFunController iftest被调用.....");
		//将数据保存到request作用域，Spring MVC 更推荐使用WebRequest
		webRequest.setAttribute("username", "a614", webRequest.SCOPE_REQUEST);
		webRequest.setAttribute("age", 21, webRequest.SCOPE_REQUEST);
		webRequest.setAttribute("role", "admin", webRequest.SCOPE_REQUEST);
		return "success2";
	}
	
	//测试循环
	@RequestMapping("/eachtest")
	public String eachtest(WebRequest webRequest) {
		System.out.println("ThymeleafFunController eachtest被调用.....");
		//模拟数据库存取
		List<Book> books = new ArrayList<>();
		books.add(new Book(1, "疯狂Java讲义", "java.jpg", "李刚 编注", 109.00, ""));
		books.add(new Book(2, "轻量级Java EE 企业应用实战", "ee.jpg", "李刚 编注", 108.00, ""));
		books.add(new Book(3, "Spring + Mybatis应用实战", "SpringMybatis.jpg", "疯狂软件 编注", 58.00, ""));
		books.add(new Book(4, "疯狂Android讲义", "android.jpg", "李刚 编注", 108.00, ""));
		books.add(new Book(5, "疯狂Ajax开发", "ajax.jpg", "李刚 编注", 79.00, ""));		
		webRequest.setAttribute("books", books, webRequest.SCOPE_REQUEST);
		return "success3";
	}
	
}
