package me.unc.springboottest.controller;

import java.io.File;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import me.unc.boottest1.domain.UserHeader;

@Controller
public class UpAndDownLoadController {

	@RequestMapping("/uploadForm")
	public String uploadForm() {
		System.out.println("UpAndDownLoadController uploadForm方法被调用.....");
		return "upload";
	}
	
	@RequestMapping("/upload1")
	public String upload1(HttpServletRequest request, 
			@RequestParam("description") String description, 
			@RequestParam("file") MultipartFile file) throws Exception { //上传文件会自动绑定到MultipartFile中
		System.out.println("description = " + description);
		//如果文件不为空，写入上传路径
		if(!file.isEmpty()) {
			//上传路径
			String path = request.getServletContext().getRealPath("/upload/");
			System.out.println("path = " + path);
			//上传文件名
			String filename = description + ".jpg";
			System.out.println("filename = " + filename);
			File filepath = new File(path, filename);
			//判断文件路径是否存在，如果不存在就创建一个
			if(!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			//将上传文件保存到一个目标文件当中
			file.transferTo(new File(path + File.separator + filename));
			return "filesuccess";
		} else {
			return "error";
		}
	}
	
	@RequestMapping("/uploadForm2")
	public String uploadForm2() {
		System.out.println("UpAndDownLoadController uploadForm2方法被调用.....");
		return "uploadForm";
	}
	
	@RequestMapping("/upload2")
	public String upload2(HttpServletRequest request, @ModelAttribute UserHeader userHeader,
			Model model) throws Exception{
		System.out.println("UpAndDownLoadController upload2方法被调用.....");
		System.out.println("username = " + userHeader.getName());
		System.out.println("file = " + userHeader.getHeaderPortrait().getOriginalFilename());
		//如果文件不为空，写入上传路径
		if(!userHeader.getHeaderPortrait().isEmpty()) {
			//上传路径
			String path = request.getServletContext().getRealPath("/upload/");
			System.out.println("path = " + path);
			//上传文件名
			String filename = userHeader.getName() + ".jpg";
			System.out.println("filename = " + filename);
			File filepath = new File(path, filename);
			//判断文件路径是否存在，如果不存在就创建一个
			if(!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			//将上传文件保存到一个目标文件当中
			userHeader.getHeaderPortrait().transferTo(new File(path + File.separator + filename));
			model.addAttribute("UserHeader", userHeader);
			return "fileInfo";
		} else {
			return "error";
		}
	}
	
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request, 
			@RequestParam("filename") String filename, @RequestHeader("User-Agent") String userAgent,
			Model model) throws Exception {
		System.out.println("UpAndDownLoadController download方法被调用.....");
		System.out.println("filename = " + filename);
		String path = request.getServletContext().getRealPath("/upload/");
		File file = new File(path + File.separator + filename + ".jpg");
		System.out.println("file = " + file);
		//ok表示HTTP中的状态200
		BodyBuilder builder = ResponseEntity.ok();
		//内容长度
		builder.contentLength(file.length());
		// application/octet-steam 二进制数据流（最常见的文件下载）
		builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
		//对文件进行编码
		filename = URLEncoder.encode(filename, "UTF-8");
		//设置实际的响应文件名，告诉浏览器文件要用于下载或保存
		//不同浏览器，处理方法不同，要根据浏览器版本区别对待
		if(userAgent.indexOf("MSIE") > 0) {
			// 如果是IE，只需要用UTF-8字符集进行URL编码即可
            builder.header("Content-Disposition", "attachment; filename=" + filename);
		} else {
			// 而FireFox、Chrome等浏览器，则需要说明编码的字符集
            // 注意filename后面有个*号，在UTF-8后面有两个单引号！
            builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + filename);
		}
		return builder.body(FileUtils.readFileToByteArray(file));
	}
	
}
