package me.unc.springbootmybatis.repository;

import me.unc.springbootmybatis.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserRepository {

    /**
     * 插入数据
     * @param user
     * @return
     */
    @Insert("insert into tb_user2(login_name, username, password) values(#{loginName}, #{username}, #{password})")
    public int insertUser(User user);

    /**
     * 插入数据获取主键
     * @param user
     */
    @Insert("insert into tb_user2(login_name, username, password) values(#{loginName}, #{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void insertGetKey(User user);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Select("select * from tb_user2 where username = #{username}")
    @ResultMap("userResult") //引用id为userResult的resultMap
    public User selectByUsername(@Param("username") String username);

    /**
     * 查询全部数据
     * @return
     */
    @Select("select * from tb_user2")
    @Results(id = "userResult", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "login_name", property = "loginName"),
            @Result(column = "password", property = "password"),
            @Result(column = "username", property = "username")
    })
    public List<User> findAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from tb_user2 where id = #{id}")
    @ResultMap("userResult")
    public User findUserById(int id);

    /**
     * 根据id删除
     * @param id
     */
    @Delete("delete from tb_user2 where id = #{id}")
    public void delete(final Integer id);

    /**
     *更新用户信息
     * @param user
     */
    @Update("update tb_user2 set username = #{username}, login_name = #{loginName} where id = #{id}")
    public void update(final User user);

}
