package me.unc.springbootmybatis.service;

import me.unc.springbootmybatis.bean.User;
import me.unc.springbootmybatis.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * @param user
     * @return
     * @see UserRepository
     */
    public int insertUser(User user) {
        return userRepository.insertUser(user);
    }

    public User selectByUsername(String username) {
        return userRepository.selectByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void insertGetKey(User user) {
        //数据插入成功后，id会保存在User对象中
        userRepository.insertGetKey(user);
    }

    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }

}
