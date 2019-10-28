package me.unc.springdata.springdatajpa_query.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_clazz2")
public class Clazz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private String name;
    //班级与学生一对多关系
    @OneToMany(targetEntity = Student2.class, fetch = FetchType.LAZY, mappedBy = "clazz")
    private Set<Student2> students = new HashSet<>();

    @Override
    public String toString() {
        return "Clazz{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    public Clazz(String name) {
        this.name = name;
    }

    public Clazz() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student2> getStudents() {
        return students;
    }

    public void setStudents(Set<Student2> students) {
        this.students = students;
    }
}
