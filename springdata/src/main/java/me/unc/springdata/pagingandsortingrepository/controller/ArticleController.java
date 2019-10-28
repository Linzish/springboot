package me.unc.springdata.pagingandsortingrepository.controller;

import java.util.List;

import javax.annotation.Resource;

import me.unc.springdata.pagingandsortingrepository.bean.Article;
import me.unc.springdata.pagingandsortingrepository.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@Resource
	private ArticleService articleService;
	
	@RequestMapping("/sort")
	public Iterable<Article> sortArticle() {
		//指定排序参数对象：根据id，进行降序排序
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Iterable<Article> articleDatas = articleService.findAllSort(sort);
		return articleDatas;
	}
	
	@RequestMapping("/pager")
	public List<Article> sortPagerArticle(int pageIndex) {
		// 指定排序参数对象：根据id，进行降序排序
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		/*
		 * 封装分页实体
		 * 参数1：pageIndex表示当前查询的是第几页（默认0开始，0表示第一页）
		 * 参数2：表示每页展示多少数据，现在设置煤业展示2条数据
		 * 参数3：封装排序对象，根据该对象的参数指定根据id降序查询
		 */
		Pageable page = PageRequest.of(pageIndex - 1, 2, sort);
		Page<Article> articleDatas = articleService.findAll(page);
		
		System.out.println("查询总页数：" + articleDatas.getTotalPages());
		System.out.println("查询总记录数：" + articleDatas.getTotalElements());
		System.out.println("查询当前第几页：" + articleDatas.getNumber() + 1);
		System.out.println("查询当前页面的记录数" + articleDatas.getNumberOfElements());
		//查询出的结果数据集合
		List<Article> articles = articleDatas.getContent();
		System.out.println("查询当前页面的集合：" + articles);
		return articles;
	}
	
}
