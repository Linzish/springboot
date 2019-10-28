package me.unc.namequery.repository;

import me.unc.namequery.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    /**
     * 查询班级所有学生
     * @param clazzName
     * @return
     */
    List<Student> findStudentsByClazzName(String clazzName);

}
