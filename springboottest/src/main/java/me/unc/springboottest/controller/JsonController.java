package me.unc.springboottest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.unc.boottest1.domain.Book;

@Controller
public class JsonController {

	@RequestMapping("/jsontest")
	public String toJsonTest1() {
		System.out.println("JsonController toJsonTest1方法被调用.....");
		return "jsontest";
	}
	
	@RequestMapping("/jsontest2")
	public String toJsonTest2() {
		System.out.println("JsonController toJsonTest2方法被调用.....");
		return "jsontest2";
	}
	
	@RequestMapping("/findbook")
	@ResponseBody
	public Book jsonTest(@RequestBody Book book) {
		//观察页面床入的json数据是否封装到Book对象中
		System.out.println(book);
		//设置book其他信息
		book.setAuthor("肖文吉");
		book.setImage("SpringMyBatis.jpg");
		book.setPrice(58.0);
		book.setRemark("媲美于SSH组合的轻量级java EE 应用开发方式");
		return book;
	}
	
	@RequestMapping("/findbooks")
	@ResponseBody
	public List<Book> findbooks(){
		List<Book> books = new ArrayList<>();
		books.add(new Book(1, "疯狂Java讲义", "java.jpg", "李刚 编注", 109.00, ""));
		books.add(new Book(2, "轻量级Java EE 企业应用实战", "ee.jpg", "李刚 编注", 108.00, ""));
		books.add(new Book(3, "Spring + Mybatis应用实战", "SpringMybatis.jpg", "疯狂软件 编注", 58.00, ""));
		books.add(new Book(4, "疯狂Android讲义", "android.jpg", "李刚 编注", 108.00, ""));
		books.add(new Book(5, "疯狂Ajax开发", "ajax.jpg", "李刚 编注", 79.00, ""));	
		return books;
	}
	
}
