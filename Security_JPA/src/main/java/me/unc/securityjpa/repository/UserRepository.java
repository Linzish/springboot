package me.unc.securityjpa.repository;

import me.unc.securityjpa.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据登录名查询用户
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);

}
