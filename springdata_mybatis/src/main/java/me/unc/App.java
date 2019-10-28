package me.unc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Data JPA整合MyBatis
 *
 */
@SpringBootApplication
@MapperScan("me.unc.springbootmybatis.repository")
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Spring Data JPA With MyBatis Test!!" );
        SpringApplication.run(App.class, args);
    }
}
