package com.zyc.java8.po;

import java.math.BigDecimal;

/**
 * Created by zyc on 17/5/12.
 */
public class Person {

    private Integer id;

    private String name ;

    private Integer age;

    private BigDecimal salary;

    public Person() {
    }

    public Person(Integer id, String name, Integer age, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Person{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", age=" + age +
                  ", salary=" + salary +
                  '}';
    }
}
