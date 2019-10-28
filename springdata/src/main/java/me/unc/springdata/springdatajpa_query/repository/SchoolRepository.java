package me.unc.springdata.springdatajpa_query.repository;

import me.unc.springdata.springdatajpa_query.bean.Student2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SchoolRepository extends JpaRepository<Student2, Integer> {

    /**
     * 根据班级名称查询这个班级所有学生信息
     * 相当于JPQL语句:select s from Student s where s.clazz.name = ?1
     * @param clazzName
     * @return
     */
    List<Student2> findByClazz_name(String clazzName);

    /**
     * @Query写法
     * 根据班级名称查询这个班级所有学生的信息
     * ?1此处使用的是参数的位置，代表的是第一个参数
     * 此写法与findByClazz_name方法实现的功能一样
     * @param clazzName
     * @return
     */
    @Query("select s from Student2 s where s.clazz.name = ?1")
    List<Student2> findStudentsByClazzName(String clazzName);

    /**
     * 查询某个班级下所有学生的姓名和性别
     * @param clazzName
     * @return
     */
    @Query("select new Map(s.name as name, s.sex as sex) from Student2 s where s.clazz.name = ?1")
    List<Map<String, Object>> findNameAndSexByClazzName(String clazzName);

    /**
     * 查询某个班级下某种性别的所有学生的姓名
     * 上面的方法使用参数位置来查询的，除此以外，还支持使用名称匹配查询，使用格式为 “:参数名称” 引用
     * @param clazzName
     * @param sex
     * @return
     */
    @Query("select s.name from Student2 s where s.clazz.name = :clazzName and s.sex = :sex")
    List<String> findNameByClazzNameAndSex(@Param("clazzName") String clazzName, @Param("sex") char sex);

    /**
     * 查询某个学生所在班级
     * @param stuName
     * @return
     */
    //TODO 有问题
    @Query("select c.name from Clazz c inner join Student2 s where s.name = ?1")
    String findClazzNameByStuName(String stuName);

    /**
     * 执行更新操作，删除某个学生的信息
     * 使用@Query和@Modifying可以执行更新操作
     * @param stuName
     * @return
     */
    @Modifying
    @Query("delete from Student2 s where s.name = ?1")
    int deleteStuByStuName(String stuName);

}
