package me.unc.namequery.controller;

import me.unc.namequery.bean.Clazz;
import me.unc.namequery.bean.Student;
import me.unc.namequery.service.SchoolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private SchoolService schoolService;

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

    @RequestMapping("/getClazzStus")
    public List<Map<String,Object>> getClazzStus(String clazzName) {
        return schoolService.getStudentsByClazzName(clazzName);
    }

}
