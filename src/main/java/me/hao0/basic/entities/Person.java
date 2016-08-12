package me.hao0.basic.entities;

/**
 * Author: haolin
 * On: 12/9/14
 */

public class Person {

    private Long id;

    private String username;

    private Integer age;

    private Integer grade;

    public Person() {
    }

    public Person (Long id, String username){
        this.id = id;
        this.username = username;
    }

    public Person(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }
}
