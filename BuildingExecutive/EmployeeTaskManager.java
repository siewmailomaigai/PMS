/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BuildingExecutive;

/**
 *
 * @author 60192
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeTaskManager {

    public void Run() {

        Scanner scanner = new Scanner(System.in);
        EmployeeTaskManager manager = new EmployeeTaskManager();

        
            while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Assign employee task");
            System.out.println("2. View all employee tasks");
            System.out.println("3. Search employee task");
            System.out.println("4. Update employee task");
            System.out.println("5. Delete employee task");
            System.out.println("6. Exit");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    manager.addEmployeeTask(scanner);
                    break;
                case "2":
                    manager.viewAllEmployeeTasks();
                    break;
                case "3":
                    manager.searchEmployeeTask(scanner);
                    break;
                case "4":
                    manager.updateEmployeeTask(scanner);
                    break;
                case "5":
                    manager.deleteEmployeeTask(scanner);
                    break;
                case "6":
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    private List<String> employeeTasks;

    public EmployeeTaskManager() {
        this.employeeTasks = new ArrayList<>();
        loadEmployeeTasks();
    }

    private void loadEmployeeTasks() {
        try (Scanner scanner = new Scanner(new File("employee_tasks.txt"))) {
            while (scanner.hasNextLine()) {
                employeeTasks.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading employee tasks file: " + e.getMessage());
        }
    }

    private void saveEmployeeTasks() {
        try (PrintWriter writer = new PrintWriter(new File("employee_tasks.txt"))) {
            for (String employeeTask : employeeTasks) {
                writer.println(employeeTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving employee tasks file: " + e.getMessage());
        }
    }

    public void addEmployeeTask(Scanner scanner) {
        System.out.println("Enter employee details:");
        System.out.print("Employee ID: ");
        String id = scanner.nextLine();
        System.out.print("Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Job: ");
        String job = scanner.nextLine();
        System.out.print("Task: ");
        String task = scanner.nextLine();

        String employeeTask = id + "," + name + "," + job + "," + task;
        employeeTasks.add(employeeTask);
        saveEmployeeTasks();
        System.out.println("Task assigned successfully to employee.");
    }

    public void viewAllEmployeeTasks() {
        System.out.println("Employee ID, Employee Name, Job, Task");
        for (String employeeTask : employeeTasks) {
            String[] taskDetails = employeeTask.split(",");
            System.out.println(taskDetails[0] + ", " + taskDetails[1] + ", " + taskDetails[2] + ", " + taskDetails[3]);
        }
    }

    public void searchEmployeeTask(Scanner scanner) {
        System.out.print("Enter employee ID to search: ");
        String id = scanner.nextLine();

        for (String employeeTask : employeeTasks) {
            String[] taskDetails = employeeTask.split(",");
            if (taskDetails[0].equals(id)) {
                System.out.println("Employee ID: " + taskDetails[0]);
                System.out.println("Employee Name: " + taskDetails[1]);
                System.out.println("Job: " + taskDetails[2]);
                System.out.println("Task: " + taskDetails[3]);
                return;
            }
        }

        System.out.println("Employee with ID " + id + " not found.");
    }

    public void updateEmployeeTask(Scanner scanner) {
        System.out.print("Enter employee ID to update: ");
        String id = scanner.nextLine();

        for (int i = 0; i < employeeTasks.size(); i++) {
            String employeeTask = employeeTasks.get(i);
            String[] taskDetails = employeeTask.split(",");
            if (taskDetails[0].equals(id)) {
                System.out.print("Enter updated employee name: ");
                String name = scanner.nextLine();
                System.out.print("Enter updated job: ");
                String job = scanner.nextLine();
                System.out.print("Enter updated task: ");
                String task = scanner.nextLine();

                String updatedEmployeeTask = id + "," + name + "," + job + "," + task;
                employeeTasks.set(i, updatedEmployeeTask);
                saveEmployeeTasks();
                System.out.println("Employee task updated successfully.");
                return;
            }
        }
        
        

        System.out.println("Employee with ID " + id + " not found.");
    }

    public void deleteEmployeeTask(Scanner scanner) {
        System.out.print("Enter employee ID to delete: ");
        String id = scanner.nextLine();

        for (int i = 0; i < employeeTasks.size(); i++) {
            String employeeTask = employeeTasks.get(i);
            String[] taskDetails = employeeTask.split(",");
            if (taskDetails[0].equals(id)) {
                employeeTasks.remove(i);
                saveEmployeeTasks();
                System.out.println("Employee task deleted successfully.");
                return;
            }
        }

        System.out.println("Employee task not found.");
    }

    
}
