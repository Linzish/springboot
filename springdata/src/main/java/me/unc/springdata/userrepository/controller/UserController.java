package me.unc.springdata.userrepository.controller;

import javax.annotation.Resource;

import me.unc.springdata.userrepository.bean.User;
import me.unc.springdata.userrepository.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/save")
	public String save() {
		User user = new User();
		user.setLoginame("bajie");
		user.setUsername("猪八戒");
		user.setSex('男');
		user.setAge(20);
		userService.save(user);
		return "数据保存成功！";
	}
	
	@RequestMapping("/update")
	public String update() {
		//修改的对象必须是持久化对象，随意先从数据库查询id为1 的对象开始修改
		User user = userService.getById(1);
		userService.update(user);
		return "修改成功！";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		userService.delete(1);
		return "删除数据成功！";
	}
	
	@RequestMapping("/getAll")
	public Iterable<User> getAll(){
		//查询所有用户
		return userService.getAll();
	}
}
