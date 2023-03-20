/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BuildingExecutive;

/**
 *
 * @author 60192
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ViewBE {

    private String schedulesFileName = "schedules.txt";
    private String complaintsFileName = "complaints.txt";
    private String employeesFileName = "employee.txt";

    public ViewBE(){
        
    }
    public ViewBE(String schedulesFileName, String complaintsFileName, String employeesFileName) {
        this.schedulesFileName = schedulesFileName;
        this.complaintsFileName = complaintsFileName;
        this.employeesFileName = employeesFileName;
    }

    public void viewPatrolling() {
        int count = getCount(schedulesFileName);
        System.out.println("Number of patrolling records: " + count);
    }

    public void viewComplaints() {
        int count = getCount(complaintsFileName);
        System.out.println("Number of complaints records: " + count);
    }

    public void viewJobReports() {
        int count = getCount(employeesFileName);
        System.out.println("Number of job report records: " + count);
    }

    private int getCount(String fileName) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void showOptions() {
        Scanner input = new Scanner(System.in);
        int option = 0;
        while (option != 4) {
            System.out.println("Please select an option:");
            System.out.println("1. View patrolling");
            System.out.println("2. View complaints");
            System.out.println("3. View job reports");
            System.out.println("4. Exit");
            System.out.print("Enter option: ");
            option = input.nextInt();
            processOption(option);
        }
    }

    public void processOption(int option) {
        switch (option) {
            case 1:
                viewPatrolling();
                break;
            case 2:
                viewComplaints();
                break;
            case 3:
                viewJobReports();
                break;
            case 4:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }
}

