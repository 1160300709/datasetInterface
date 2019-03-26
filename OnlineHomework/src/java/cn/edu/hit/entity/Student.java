/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.hit.entity;

/**
 *
 * @author dell
 */
public class Student {
    private String name;
    private String sid;
    private String sex;
    private String school;
    private String major;
    private String salary;
    private String exp;
    private String city;
    
    
    public Student()
    {
        
    }
    public Student( String sid, String name,String sex, String school, String major) {
        this.name = name;
        this.sid = sid;
        this.sex = sex;
        this.school = school;
        this.major = major;
    }
    public Student(String name, String sid, String sex, String school, String major, String salary, String exp, String city) {
        this.name = name;
        this.sid = sid;
        this.sex = sex;
        this.school = school;
        this.major = major;
        this.salary = salary;
        this.exp = exp;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    
}
