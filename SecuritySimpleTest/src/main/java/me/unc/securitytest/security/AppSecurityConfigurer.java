package me.unc.securitytest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 用于处理用户认证和授权操作
 */
@Configuration
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {

    //注入认证处理类，处理不同用户跳转到不同的页面
    @Autowired
    private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    /**
     * 用户授权操作
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("AppSecurityConfigurer configure() 调用......");
        http.authorizeRequests()
                //spring-security 5.0 后需要过滤静态资源
                .antMatchers("/login", "/css/**", "/js/**", "/img/*").permitAll()
                .antMatchers("/", "/home").hasRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "DBA")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .successHandler(appAuthenticationSuccessHandler)
                .usernameParameter("loginName").passwordParameter("password")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }

    /**
     * 用户认证操作
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("AppSecurityConfigurer configureGlobal() 调用......");
        //spring-security 5.0 后需要密码编辑器，否则会抛出异常
        //设置密码示例 auth.inMemoryAuthentication().withUser("xxx").password("{noop}123456").roles("USER")
        //inMemoryAuthentication()方法可以添加一个用户，并给用户指定权限
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("unc").password("123456").roles("USER");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("admin").password("admin").roles("ADMIN", "DBA");
    }

}
