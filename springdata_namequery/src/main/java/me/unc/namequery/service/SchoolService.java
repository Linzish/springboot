package me.unc.namequery.service;

import me.unc.namequery.bean.Clazz;
import me.unc.namequery.bean.Student;
import me.unc.namequery.repository.ClazzRepository;
import me.unc.namequery.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolService {

    @Resource
    private StudentRepository studentRepository;
    @Resource
    private ClazzRepository clazzRepository;

    @Transactional
    public void saveClazzAll(List<Clazz> clazzes) {
        clazzRepository.saveAll(clazzes);
    }

    @Transactional
    public void saveStudentAll(List<Student> students){
        studentRepository.saveAll(students);
    }

    public List<Map<String, Object>> getStudentsByClazzName(String clazzName){
        //查询班级所有学生
        List<Student> students = studentRepository.findStudentsByClazzName(clazzName);
        List<Map<String, Object>> results = new ArrayList<>();
        //遍历学生对象，提取相关信息
        for (Student student : students) {
            Map<String, Object> stu = new HashMap<>();
            stu.put("name", student.getName());
            stu.put("age", student.getAge());
            stu.put("sex", student.getSex());
            results.add(stu);
        }
        return results;
    }

}
