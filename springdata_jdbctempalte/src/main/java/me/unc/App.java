package me.unc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring 对数据库的操作在JDBC上做了深层次的封装，奖励一个JDBC存取框架 JDBCTemplate（JDBC模板）
 * JDBCTemplate设计的目的是为了不同类型的JDBC操作提供模板方法，每个模板方法都能控制整个访问的过程
 * 通过这种方式，可以尽可能保持灵活性的情况下，把数据库存取差欧洲的工作量见到最低
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Spring Data JPA JdbcTemplate Test!!" );
        SpringApplication.run(App.class, args);
    }
}
