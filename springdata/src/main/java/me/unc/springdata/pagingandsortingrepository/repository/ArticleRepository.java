package me.unc.springdata.pagingandsortingrepository.repository;

import me.unc.springdata.pagingandsortingrepository.bean.Article;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * PagingAndSortingRepository增加了排序和分页功能
 * @author bet- -
 *
 */
public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {

}
