package me.unc.springdata.userrepository.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//用于标记持久化类，Spring Boot项目加载后会自动根据持久化类建表
@Table(name = "tb_user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 使用指定主键，使用代码@GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 指定主键生成策略，MySQL默认为自增长
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;  //主键
	private String username;  //姓名
	private String loginame;  //登录名
	private char sex;  //性别
	private int age;  //年龄
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginame() {
		return loginame;
	}
	public void setLoginame(String loginame) {
		this.loginame = loginame;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
