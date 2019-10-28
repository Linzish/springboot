package me.unc.specification.repository;

import me.unc.specification.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//同时实现JpaRepository接口和JpaSpecificationExecutor接口
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
}
