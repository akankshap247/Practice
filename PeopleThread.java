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
       
    	Employee e1 = new Employee("Neha","Pandey","E003", "1992-09-09", 100, "Sales","2023-06-01");
      	Employee e2 = new Employee( "Samay","Rana","E004", "1989-12-12", 5000, "IT","2024-03-01");
      	Employee e3 = new Employee( "Abhijeet","Bhat","E005", "1985-05-25", 10000, "IT","2023-04-01");
      	Employee e4 = new Employee( "Tanu","Mishra","E006", "1999-01-16", 2000, "Accounts","2022-10-01");
      	Employee e5 = new Employee( "Yogesh","Patidar","E007", "2001-09-11", 500, "HR","2020-11-01");
      	employees.add(e1);
    	employees.add(e2);
    	employees.add(e3);
    	employees.add(e4);
    	employees.add(e5);
    	  
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

