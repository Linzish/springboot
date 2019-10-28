package me.unc.springdata.springdatajpa_query.controller;

import me.unc.springdata.springdatajpa_query.bean.Clazz;
import me.unc.springdata.springdatajpa_query.bean.Student;
import me.unc.springdata.springdatajpa_query.service.SchoolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    /**
     * 保存信息
     * @return
     */
    @RequestMapping("/save")
    public String save() {
        //保存班级信息
        Clazz clazz1 = new Clazz("Java开发1班");
        Clazz clazz2 = new Clazz("Java开发2班");
        List<Clazz> clazzes = new ArrayList<>();
        clazzes.add(clazz1);
        clazzes.add(clazz2);
        schoolService.saveClazzAll(clazzes);

        Student swk = new Student("孙悟空", "广州", 700, '男', clazz1);
        Student zzj = new Student("蜘蛛精", "广州", 700, '女', clazz1);
        Student nmw = new Student("牛魔王", "广州", 500, '男', clazz2);
        List<Student> students = new ArrayList<>();
        students.add(swk);
        students.add(zzj);
        students.add(nmw);
        schoolService.saveStudentAll(students);

        return "保存成功！";
    }

    /**
     * 查询某个班级所有学生的姓名、年龄、性别
     * @param clazzName
     * @return
     */
    @RequestMapping("/getClazzStus")
    public List<Map<String, Object>> getClazzStus(String clazzName) {
        return schoolService.getStusByClazzName(clazzName);
    }

    /**
     * 查询某个班级所有学生的姓名、性别
     * @param clazzName
     * @return
     */
    @RequestMapping("/findNameAndSexByClazzName")
    public List<Map<String, Object>> findNameAndSexByClazzName(String clazzName) {
        return schoolService.findNameAndSexByClazz(clazzName);
    }

    /**
     * 查询某个班级某种性别的所有学生的名字
     * @param clazzName
     * @param sex
     * @return
     */
    @RequestMapping("/findNameByClazzNameAndSex")
    public List<String> findNameByClazzNameAndSex(String clazzName, Character sex) {
        return schoolService.findNameByClazzNameAndSex(clazzName, sex);
    }

    /**
     * 查询某个学生属于哪个班级
     * @param stuName
     * @return
     */
    @RequestMapping("/findClazzNameByStuName")
    public String findClazzNameByStuName(String stuName) {
        return schoolService.findClazzNameByStuName(stuName);
    }

    /**
     * 删除学生信息
     * @param stuName
     * @return
     */
    @RequestMapping("/deleteStuByStuName")
    public String deleteStuByStuName(String stuName) {
        return "删除数据：" + schoolService.deleteStuByStuName(stuName);
    }

}
