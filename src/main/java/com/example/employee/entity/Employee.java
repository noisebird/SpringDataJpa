package com.example.employee.entity;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
    //主键自增
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String name;
    private String gender;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private int companyId;
    @Column(nullable = false)
    private Integer salary;

    public Employee() {
    }

    public Employee(String name, int id, String gender, int age, int companyId, int salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.companyId = companyId;
        this.salary = salary;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
