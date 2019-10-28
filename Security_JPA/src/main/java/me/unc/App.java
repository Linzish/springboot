package me.unc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于Spring JPA的Spring Boot Security 操作
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Spring Security With JPA Test!!" );
        SpringApplication.run(App.class, args);
    }
}
