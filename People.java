package com.wissen.practice;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
public class People {


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
       
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", "Doe", "E001", "1990-05-15", 60000, "HR"));
        employees.add(new Employee("Jane", "Smith", "E002", "1985-08-20", 75000, "IT"));
    
    	Employee e1 = new Employee("Akanksha","Pandey","E003", "1992-09-09", 100, "Sales");
    	Employee e2 = new Employee( "Samay","Rana","E004", "1989-12-12", 5000, "IT");
    	Employee e3 = new Employee( "Abhijeet","Bhat","E005", "1985-05-25", 10000, "IT");
    	Employee e4 = new Employee( "Tanu","Mishra","E006", "1999-01-16", 2000, "Accounts");
    	Employee e5 = new Employee( "Yogesh","Patidar","E007", "2001-09-11", 500, "Sales");
    	employees.add(e1);
    	employees.add(e2);
    	employees.add(e3);
    	employees.add(e4);
    	employees.add(e5);
    	
    	employees.stream().filter(e -> e.getSalary()>2000).forEach(e -> System.out.println(e.getFirstName()+" "+e.getLastName()));;
    	
        
        List<User> users = new ArrayList<>();
        for (Employee employee : employees) {
            users.add(employeeToUserMapper.apply(employee));
        }

        for (User user : users) {
            System.out.println("User ID: " + user.getId());
            System.out.println("Username: " + user.getUserName());
            System.out.println("Password: " + user.getPassword());
            System.out.println();
        }
        
        employees.sort(Comparator.comparing(employee -> {
            LocalDate dateOfBirth = LocalDate.parse(employee.getDateOfBirth(), DateTimeFormatter.ISO_DATE);
            return dateOfBirth.getMonthValue(); // Get the month as an integer
        }));

        for (Employee employee : employees) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getDateOfBirth());
        }
    

}
}
