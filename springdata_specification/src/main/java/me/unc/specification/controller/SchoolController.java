package me.unc.specification.controller;

import me.unc.specification.bean.Clazz;
import me.unc.specification.bean.Student;
import me.unc.specification.service.SchoolService;
import me.unc.specification.utils.PageData;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class SchoolController {

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

        Student swk = new Student("孙悟空", "花果山", 700, '男', clazz1);
        Student zzj = new Student("蜘蛛精", "广州", 700, '女', clazz1);
        Student zx = new Student("紫霞仙子", "盘丝洞", 500, '女', clazz1);
        Student nmw = new Student("牛魔王", "广州", 500, '男', clazz2);
        Student zzb = new Student("至尊宝", "广州", 500, '男', clazz2);
        Student tsgz = new Student("铁扇公主", "火焰山", 500, '女', clazz2);
        List<Student> students = new ArrayList<>();
        students.add(swk);
        students.add(zzj);
        students.add(nmw);
        students.add(zx);
        students.add(zzb);
        students.add(tsgz);
        schoolService.saveStudentAll(students);

        return "保存成功";
    }

    @RequestMapping("/getStusBySex")
    public List<Map<String, Object>> getStusBySex(char sex) {
        return schoolService.getStusBySex(sex);
    }

    //动态查询学生信息
    @RequestMapping("/getStusByDynamic")
    public List<Map<String, Object>> getStusByDynamic(Student student) {
        return schoolService.getStusByDynamic(student);
    }

    //分页查询某个班级的学生信息
    @RequestMapping("/getStusByPage")
    public PageData getStusByPage(String clazzName, int pageIndex, int pageSize) {
        //分页查询
        Page<Student> page = schoolService.getStusByPage(clazzName, pageIndex, pageSize);
        //对查询结果进行分析
        List<Student> students = page.getContent();
        List<Map<String, Object>> stuDatas = new ArrayList<>();
        for (Student student : students) {
            Map<String, Object> stuMap = new HashMap<>();
            stuMap.put("name", student.getName());
            stuMap.put("age", student.getAge());
            stuMap.put("sex", student.getSex());
            stuMap.put("address", student.getAddress());
            stuMap.put("clazzName", student.getClazz().getName());
            stuDatas.add(stuMap);
        }
        //把数据存入PageData队形中返回给浏览器
        PageData data = new PageData();
        data.setStuDatas(stuDatas);
        data.setPageIndex(page.getNumber() + 1);
        data.setPageSize(page.getTotalPages());
        data.setTotalCount(page.getTotalElements());
        data.setPageNum(page.getSize());

        return data;
    }

}
