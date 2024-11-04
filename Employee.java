package com.wissen.practice;
public class Employee {
    private String firstName;
    private String lastName;
    private String id;
    private String dateOfBirth;
    private double salary;
    private String department;
    private String dateOfJoining;

    public Employee(String firstName, String lastName, String id, String dateOfBirth, double salary, String department,String dateOfJoining) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.department = department;
        this.dateOfJoining=dateOfJoining;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

   
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getDateOfJoining() {
        return dateOfJoining;
    }

   
    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
}
