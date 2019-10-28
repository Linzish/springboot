package me.unc.specification.service;

import me.unc.specification.bean.Clazz;
import me.unc.specification.bean.Student;
import me.unc.specification.repository.ClazzRepository;
import me.unc.specification.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
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
    public void saveStudentAll(List<Student> students) {
        studentRepository.saveAll(students);
    }

    /**
     * 根据性别查询学生信息
     *
     * @param sex
     * @return
     */
    public List<Map<String, Object>> getStusBySex(char sex) {
        List<Student> students = studentRepository.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //root.get("sex")表示获取sex这个字段名称，equal表示执行equal查询
                //相当于select s from Student s where s.sex = ?1
                Predicate p1 = criteriaBuilder.equal(root.get("sex"), sex);
                return p1;
            }
        });
        List<Map<String, Object>> results = new ArrayList<>();
        //遍历数据，提取
        for (Student student : students) {
            Map<String, Object> stu = new HashMap<>();
            stu.put("name", student.getName());
            stu.put("age", student.getAge());
            stu.put("sex", student.getSex());
            results.add(stu);
        }
        return results;
    }

    /**
     * 动态查询学生信息：可以根据学生对象的姓名（模糊匹配）、地址查询（模糊匹配）、性别、班级查询学生信息
     * 如果没有输入参数，默认查询全部学生信息
     *
     * @param student
     * @return
     */
    public List<Map<String, Object>> getStusByDynamic(Student student) {
        List<Student> students = studentRepository.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //本集合用于封装查询条件
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (student != null) {
                    //判断是否传入用于查询的姓名
                    if (!StringUtils.isEmpty(student.getName())) {
                        predicates.add(criteriaBuilder.like(root.<String>get("name"), "%" + student.getName() + "%"));
                    }
                    //判断是否传入用于查询的地址
                    if (!StringUtils.isEmpty(student.getAddress())) {
                        predicates.add(criteriaBuilder.like(root.<String>get("address"), "%" + student.getAddress() + "%"));
                    }
                    //判断是否传入用于查询的性别
                    if (student.getSex() != '\0') {
                        predicates.add(criteriaBuilder.equal(root.<String>get("sex"), student.getSex()));
                    }
                    //判断是否传入用于查询的班级信息
                    if (student.getClazz() != null && !StringUtils.isEmpty(student.getClazz().getName())) {
                        root.join("clazz", JoinType.INNER);
                        Path<String> clazzName = root.get("clazz").get("name");
                        predicates.add(criteriaBuilder.equal(clazzName, student.getClazz().getName()));
                    }
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        });
        List<Map<String, Object>> results = new ArrayList<>();
        for (Student student1 : students) {
            Map<String, Object> stuMap = new HashMap<>();
            stuMap.put("name", student1.getName());
            stuMap.put("age", student1.getAge());
            stuMap.put("sex", student1.getSex());
            stuMap.put("address", student1.getAddress());
            stuMap.put("clazzName", student1.getClazz().getName());
            results.add(stuMap);
        }
        return results;
    }

    /**
     * 分页查询某个班级的学生信息
     *
     * @param clazzName 班级名称
     * @param pageIndex 当前查询第几页
     * @param pageSize  每页查询的最大数据量
     * @return
     */
    public Page<Student> getStusByPage(String clazzName, int pageIndex, int pageSize) {
        //指定排序参数对象：根据id进行降序排序
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        //分页查询学生信息，返回分页实体对象数据
        //pages对象中包含了了查询出来的数据信息以及分页相关的信息
        Page<Student> pages = studentRepository.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                root.join("clazz", JoinType.INNER);
                Path<String> cn = root.get("clazz").get("name");
                Predicate p1 = criteriaBuilder.equal(cn, clazzName);
                return p1;
            }
        }, PageRequest.of(pageIndex - 1, pageSize, sort));
        return pages;
    }

}
