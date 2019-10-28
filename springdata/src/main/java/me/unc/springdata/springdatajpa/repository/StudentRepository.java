package me.unc.springdata.springdatajpa.repository;

import me.unc.springdata.springdatajpa.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JpaRepository<Student, Integer>，Student表示持久化对象，Integer表示指定id的类型
 * Spring Data JPA 可以直接通过在数据访问层定义方法名字名称即可进行数据的访问操作
 * 利用关键字通过方法名转变蚊sql语句
 */

public interface StudentRepository extends JpaRepository<Student, Integer> {

    /**
     * 通过对学生姓名来查询学生对象
     * 此方法相当于JPQL语句代码:select s from Student s where s.name = ?1
     *
     * @param name
     * @return
     */
    Student findByName(String name);

    /**
     * 通过名字和地址查询学生信息
     * 此方法相当于JPQL语句代码:select s from Student s where s.name = ?1 and s.address = ?2
     *
     * @param name
     * @param address
     * @return Student对象的List集合
     */
    List<Student> findByNameAndAddress(String name, String address);

    /**
     * 通过学生名字模糊查询学生信息
     * 此方法相当于JPQL语句代码:select s from Student s where s.name like ?1
     *
     * @param name
     * @return Student对象的List集合
     */
    List<Student> findByNameLike(String name);

}
