/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.io.Serializable;

/**
 *
 * @author Lien
 */
public class Employee implements Serializable{
    public String id;
    public String name;
    public int age;
    public String email;
    public double salary;
    public Employee(String id, String name, int age, String email, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.salary = salary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }
}
