package me.unc.jdbctamplate.repository;

import me.unc.jdbctamplate.bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

//Repository注解：标注这是一个持久化操作对象
@Repository
public class UserRepository {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入数据
     *
     * @return 插入数据的影响行数
     */
    public int insertUser() {
        String sql = "insert into tb_user2(login_name, username, password) " +
                "values(?,?,?), (?,?,?), (?,?,?)";
        Object[] args = new Object[]{"swk", "孙悟空", "123456", "zbj", "猪八戒", "123456", "ts", "唐僧", "123456"};
        //参数1：sql，参数2：占位符?的参数
        return jdbcTemplate.update(sql, args);
    }

    /**
     * 根据username查询数据
     *
     * @param username
     * @return User 对象
     */
    public User selectByUsername(String username) {
        String sql = "select * from tb_user2 where username = ?";
        //定义一个RowMapper
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        //执行查询
        User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, rowMapper);
        return user;
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return User 对象
     */
    public User findUserById(int id) {
        String sql = "select * from tb_user2 where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    public List<User> findAll() {
        String sql = "select * from tb_user2";
        //声明结果集的映射RowMapper，将结果集的数据映射成User对象
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * 根据id删除数据
     * @param id
     */
    public void delete(final Integer id) {
        String sql = "delete from tb_user2 where id = ?";
        //执行
        jdbcTemplate.update(sql, id);
    }

    /**
     * 修改数据
     * @param user
     */
    public void update(final User user) {
        String sql = "update tb_user2 set username = ?, login_name = ?, where id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getLoginName(), user.getId());
    }

    /**
     * 插入数据，获取被插入数据的主键
     * @param user
     * @return
     */
    public User insertGetKey(User user) {
        String sql = "insert into tb_user2(username, login_name, password) " +
                "values(?,?,?)";
        //定义插入数据后获取主键的对象
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                //插入数据后，将被插入数据的主键返回
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getLoginName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        }, holder);
        //获取被插入数据库的主键，注入到user对象
        int newUserId = Objects.requireNonNull(holder.getKey()).intValue();
        user.setId(newUserId);
        return user;
    }

}
