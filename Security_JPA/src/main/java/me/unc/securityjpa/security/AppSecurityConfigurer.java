package me.unc.securityjpa.security;

import me.unc.securityjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义Spring Security认证处理类
 * 需要继承WebSecurityConfigurerAdapter，重写对应的方法
 */
@Configuration
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    //注入加密接口
    @Autowired
    private PasswordEncoder passwordEncoder;
    //注入用户认证接口
    @Autowired
    private AuthenticationProvider authenticationProvider;
    //注入认证处理成功类，验证用户成功后处理不同用户跳转不同的页面
    @Autowired
    private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    /**
     * BCryptPasswordEncoder是Spring Security提供的PasswordEncoder接口，
     * 是实现类用来创建密码的加密程序，避免将密码明文存储到数据库
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * DaoAuthenticationProvider是Spring Security提供的AuthenticationProvider实现
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        //创建DaoAuthenticationProvider对象
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //不要隐藏“用户未找到”异常
        provider.setHideUserNotFoundExceptions(false);
        //传入在userService中自定义的认证方式
        //Spring Security会通过loadUserByUsername方法获取对应UserDetail进行认证
        provider.setUserDetailsService(userService);
        //设置密码加密程序认证
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置认证方式
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * 设置了登录页面，而且登录页面任何人都可以访问，然后设置了登录失败地址，也设置了注销请求
     * permitAll()表示该请求任何人都可以访问,
     * .anyRequest().authenticated()表示其他的请求都必须要有权限认证
     * @param http
     * @throws Exception
     */
    //TODO 不知道多拦截了什么
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("AppSecurityConfigurer configure() 调用.......");
        http.authorizeRequests()
                //spring-security 5.0 后需要过滤静态资源
                .antMatchers("/login", "/logout", "/css/**", "/js/**", "/img/*", "/fonts/**", "/index").permitAll()
                .antMatchers("/home", "/").hasRole("USER")
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
}
