package me.unc.securityjpa.service;

import me.unc.securityjpa.pojo.Role;
import me.unc.securityjpa.pojo.User;
import me.unc.securityjpa.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 需要实现UserDetailsService接口
 * 因为在Spring Security中配置相关参数需要UserDetailsService类型的数据
 */
@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    /**
     * 重写UserDetailsService接口中的loadUserByUsername方法，通过该方法查询对应的用户
     * 返回对象 UserDetail 是 Spring Security 的一个核心接口
     * 其中定义一些可以获取用户名、密码、权限等的认证相关信息的方法
     * @param loginName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        //调用持久化层接口的方法查找用户，此处传入username，实际参数是loginName（名字我改了）
        User user = userRepository.findByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //创建一个List，用于保存用户权限，GrantedAuthority对象代表赋予当前用户的权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        //获取当前用户的权限
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            //将关联对象Role的authority属性保存为用户的认证权限
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        //此处需要返回org.springframework.security.core.userdetails.User
        //该类是Spring Security内部的实现，专门用于保存用户名、密码、权限等认证信息
        //这里返回用户名、密码和权限
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
