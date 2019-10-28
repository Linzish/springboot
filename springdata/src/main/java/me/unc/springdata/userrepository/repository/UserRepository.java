package me.unc.springdata.userrepository.repository;

import me.unc.springdata.userrepository.bean.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 继承CrudRepository接口，User表示被操作的持久化对象，Integer用于表示ID类型
 * @author bet- -
 *
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}
