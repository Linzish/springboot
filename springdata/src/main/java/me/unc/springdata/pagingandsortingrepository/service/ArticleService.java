package me.unc.springdata.pagingandsortingrepository.service;

import javax.annotation.Resource;

import me.unc.springdata.pagingandsortingrepository.bean.Article;
import me.unc.springdata.pagingandsortingrepository.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

	@Resource
	private ArticleRepository articleRepository;
	
	/**
	 * 带排序的分页查询
	 * @param sort
	 * @return
	 */
	public Iterable<Article> findAllSort(Sort sort) {
		return articleRepository.findAll(sort);
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public Page<Article> findAll(Pageable page) {
		return articleRepository.findAll(page);
	}
	
}
