package me.unc.springboottest.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UserHeader implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private MultipartFile headerPortrait;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile getHeaderPortrait() {
		return headerPortrait;
	}
	public void setHeaderPortrait(MultipartFile headerPortrait) {
		this.headerPortrait = headerPortrait;
	}
	public UserHeader() {
		super();
	}
	@Override
	public String toString() {
		return "UserHeader [name=" + name + ", headerPortrait=" + headerPortrait + "]";
	}
	public UserHeader(String name, MultipartFile headerPortrait) {
		super();
		this.name = name;
		this.headerPortrait = headerPortrait;
	}
	
}
