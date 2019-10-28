package me.unc.namequery.bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_student2")
//该注解定义了方法名findStudentsByClazzName，到query中的查询语句关系
//NamedQuery注解要反到查询的实体u上面，并且命名格式为：实体类名.方法名称
@NamedQuery(name = "Student.findStudentsByClazzName", query = "select s from Student s where s.clazz.name = ?1")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private int age;
    private char sex;
    //学生与班级是多对一关系，这里配置的是双向关联
    @ManyToOne(targetEntity = Clazz.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "clazzId", referencedColumnName = "code")
    private Clazz clazz;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", clazz=" + clazz +
                '}';
    }

    public Student(String name, String address, int age, char sex, Clazz clazz) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
        this.clazz = clazz;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

}
