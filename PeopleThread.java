package com.wissen.practice;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class PeopleThread {
    
    private static List<Employee> employees = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    
    private static final Supplier<String> passwordSupplier = () -> {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    };

    private static final Function<Employee, User> employeeToUserMapper = employee -> {
        String userName = employee.getFirstName() + employee.getLastName() + employee.getDateOfBirth().substring(0, 4) + employee.getId();
        String password = passwordSupplier.get();
        return new User(employee.getId(), userName, password);
    };

    public static void main(String[] args) {
       
        employees.add(new Employee("John", "Doe", "E001", "1990-05-15", 60000, "HR"));
        employees.add(new Employee("Jane", "Smith", "E002", "1985-08-20", 75000, "IT"));
        employees.add(new Employee("Alice", "Johnson", "E003", "1992-03-10", 50000, "Finance"));
        employees.add(new Employee("Bob", "Brown", "E004", "1987-12-05", 70000, "Marketing"));
         
        for (Employee employee : employees) {
            users.add(employeeToUserMapper.apply(employee));
        }

        Thread employeeThread = new Thread(() -> {
            System.out.println("List of Employees:");
            for (Employee employee : employees) {
                System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getDateOfBirth());
            }
        });

        Thread userThread = new Thread(() -> {
            System.out.println("\nList of Users:");
            for (User user : users) {
                System.out.println("User ID: " + user.getId() + ", Username: " + user.getUserName());
            }
        });

        employeeThread.start(); 
        userThread.start();     
        
        try {
            employeeThread.join(); 
            userThread.join();     
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

