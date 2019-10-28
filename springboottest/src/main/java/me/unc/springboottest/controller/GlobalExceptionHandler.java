package me.unc.springboottest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ModelAndView globalErrorHandler(HttpServletRequest request, Exception e) throws Exception{
		System.out.println("GlobalExceptionHandler 捕获到异常");
//		Map<String, Object> map = new HashMap<>();
		Integer code = 100;
		String msg = e.getMessage();
		String url = request.getRequestURI().toString();
		String data = "请求失败";
//		map.put("code", code);
//		map.put("mesage", msg);
//		map.put("url", url);
//		map.put("data", data);
		ModelAndView mv = new ModelAndView();
		mv.addObject("ex", e);
		mv.addObject("url", url);
		mv.addObject("code", code);
		mv.addObject("data", data);
		mv.addObject("msg", msg);
		return mv;
	}
	
}
