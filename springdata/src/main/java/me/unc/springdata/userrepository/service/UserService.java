package me.unc.springdata.userrepository.service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import me.unc.springdata.userrepository.bean.User;
import me.unc.springdata.userrepository.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Resource
	private UserRepository userRepository;
	
	//save、update、delete方法需要绑定事务，使用进行事务的绑定
	
	/**
	 * 保持对象
	 * @param user
	 * @return
	 */
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	
	/**
	 * 根据id删除对象
	 * @param id
	 */
	@Transactional
	public void delete(int id) {
		userRepository.deleteById(id);
	}
	
	/**
	 * 查询全部数据
	 * @return
	 */
	public Iterable<User> getAll(){
		return userRepository.findAll();
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public User getById(Integer id) {
		Optional<User> op = userRepository.findById(id);
		return op.get();
	}
	
	/**
	 * 修改用户对象数据，持久化对象会自动更新到数据库
	 * @param user
	 */
	@Transactional
	public void update(User user) {
		user.setUsername("孙悟空");
		user.setLoginame("swk");
	}
	
}
