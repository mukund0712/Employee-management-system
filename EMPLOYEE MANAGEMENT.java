import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {

    private static final String FILE_NAME = "employees.txt";
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        loadEmployeesFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    saveEmployeesToFile() ;
                    break;
                case 2:
                    displayAllEmployees();
                    loadEmployeesFromFile() ;
                    break;
                case 3:
                    saveEmployeesToFile();
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.next();

        System.out.print("Enter employee id: ");
        int id = scanner.nextInt();

        System.out.print("Enter designation: ");
        String designation = scanner.next();

        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(id, name, designation, salary);
        employees.add(employee);
        System.out.println("Employee added successfully!");
    }

    private static void displayAllEmployees() {
        System.out.println("\nEmployee Details:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void loadEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String designation = data[2];
                double salary = Double.parseDouble(data[3]);
                Employee employee = new Employee(id, name, designation, salary);
                employees.add(employee);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void saveEmployeesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee employee : employees) {
                writer.write(employee.getId() + "," + employee.getName() + "," +
                        employee.getDesignation() + "," + employee.getSalary());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static class Employee {
        private int id;
        private String name;
        private String designation;
        private double salary;

        public Employee(int id, String name, String designation, double salary) {
            this.id = id;
            this.name = name;
            this.designation = designation;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDesignation() {
            return designation;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", designation='" + designation + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
