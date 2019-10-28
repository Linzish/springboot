package me.unc.jdbctamplate.service;

import me.unc.jdbctamplate.bean.User;
import me.unc.jdbctamplate.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * @see UserRepository
     * @return
     */
    public int insertUser() {
        return userRepository.insertUser();
    }

    public User selectByUsername(String username) {
        return userRepository.selectByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User insertGetKey(User user) {
        return userRepository.insertGetKey(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }

}
