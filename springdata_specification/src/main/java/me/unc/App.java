package me.unc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JPA允许基于Criteria对象进行按条件查询
 * Spring Data JPA提供一个Specification接口，里面封装了JPA的Criteria查询条件
 *Criteria,可以看作传统sql的对象化表示Criteria 可以由session创建。一种比hql更面向对象的查询方式
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Spring Data JPA Specification Test!!" );
        SpringApplication.run(App.class, args);
    }
}
