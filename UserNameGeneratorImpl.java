package com.wissen.practice;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;


public class UserNameGeneratorImpl {


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

    public static void main(String[] args) {
        // Create sample employees
        employees.add(new Employee("John", "Doe", "E001", "1990-05-15", 60000, "HR"));
        employees.add(new Employee("Jane", "Smith", "E002", "1985-08-20", 75000, "IT"));
        employees.add(new Employee("Alice", "Johnson", "E003", "1992-03-10", 50000, "Finance"));
        employees.add(new Employee("Bob", "Brown", "E004", "1987-12-05", 70000, "Marketing"));


        UserNameGenerator userNameGenerator = (firstName, lastName, yearOfBirth, id) ->
            firstName + lastName + yearOfBirth + id;


        for (Employee employee : employees) {
            String yearOfBirth = employee.getDateOfBirth().substring(0, 4); // Extract year from dateOfBirth
            String userName = userNameGenerator.generate(employee.getFirstName(), employee.getLastName(), yearOfBirth, employee.getId());
            String password = passwordSupplier.get();
            users.add(new User(employee.getId(), userName, password));
        }

        System.out.println("List of Users:");
        for (User user : users) {
            System.out.println("User ID: " + user.getId() + ", Username: " + user.getUserName());
        }
    }
}