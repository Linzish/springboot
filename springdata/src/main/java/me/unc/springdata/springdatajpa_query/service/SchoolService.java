package me.unc.springdata.springdatajpa_query.service;

import me.unc.springdata.springdatajpa_query.bean.Clazz;
import me.unc.springdata.springdatajpa_query.bean.Student2;
import me.unc.springdata.springdatajpa_query.repository.ClazzRepository;
import me.unc.springdata.springdatajpa_query.repository.SchoolRepository;
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
    private SchoolRepository schoolRepository;
    @Resource
    private ClazzRepository clazzRepository;

    /**
     * 保存所有班级信息
     * @param clazzs
     */
    @Transactional
    public void saveClazzAll(List<Clazz> clazzs) {
        clazzRepository.saveAll(clazzs);
    }

    /**
     * 保存所有学生信息
     * @param students
     */
    @Transactional
    public void saveStudentAll(List<Student2> students) {
        schoolRepository.saveAll(students);
    }

    /**
     * 通过班级名字获取学生信息
     * @param clazzName
     * @return
     */
    public List<Map<String, Object>> getStusByClazzName(String clazzName) {
        //两种方法参训结果一致
        List<Student2> students = schoolRepository.findByClazz_name(clazzName);
//        List<Student> students = studentRepository.findStudentsByClazzName(clazzName);
        List<Map<String, Object>> result = new ArrayList<>();
        //遍历查询出来的学生对象，提取姓名、年龄、性别信息
        for (Student2 student : students) {
            Map<String, Object> stu = new HashMap<>();
            stu.put("name", student.getName());
            stu.put("age", student.getAge());
            stu.put("sex", student.getSex());
            result.add(stu);
        }
        return result;
    }

    /**
     * 通过班级信息获取学生姓名和性别信息
     * @param clazzName
     * @return
     */
    public List<Map<String, Object>> findNameAndSexByClazz(String clazzName) {
        return schoolRepository.findNameAndSexByClazzName(clazzName);
    }

    /**
     * 通过班级名称和性别信息查找学生信息
     * @param clazzName
     * @param sex
     * @return
     */
    public List<String> findNameByClazzNameAndSex(String clazzName, char sex) {
        return schoolRepository.findNameByClazzNameAndSex(clazzName, sex);
    }

    /**
     * 通过学生名字查找班级信息
     * @param stuName
     * @return
     */
    public String findClazzNameByStuName(String stuName) {
        return schoolRepository.findClazzNameByStuName(stuName);
    }

    /**
     * 通过学生名字删除用户信息
     * @param stuName
     * @return
     */
    @Transactional
    public int deleteStuByStuName(String stuName) {
        return schoolRepository.deleteStuByStuName(stuName);
    }

}
