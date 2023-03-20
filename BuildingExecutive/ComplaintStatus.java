/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author 60192
 */
package BuildingExecutive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ComplaintStatus {

    private String fileName;

    public ComplaintStatus(){
        
    }
    public ComplaintStatus(String fileName) {
        this.fileName = fileName;
    }

    public void viewComplaints() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void updateComplaintStatus(Scanner scanner) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            ArrayList<String> complaints = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                complaints.add(line);
            }
            reader.close();

            System.out.print("Enter complaint ID to update status: ");
            String id = scanner.nextLine();

            boolean found = false;
            for (int i = 0; i < complaints.size(); i++) {
                String complaint = complaints.get(i);
                String[] complaintDetails = complaint.split(",");
                if (complaintDetails[0].equals(id)) {
                    System.out.print("Enter updated complaint status: ");
                    String status = scanner.nextLine();

                    String updatedComplaint = id + "," + complaintDetails[1] + "," + status;
                    complaints.set(i, updatedComplaint);

                    FileWriter writer = new FileWriter(fileName);
                    for (String c : complaints) {
                        writer.write(c + "\n");
                    }
                    writer.close();

                    System.out.println("Complaint status updated successfully.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Complaint not found with ID: " + id);
            }
        } catch (IOException e) {
            System.out.println("Error reading or writing file: " + e.getMessage());
        }
    }

    public void Complaint() {
        Scanner scanner = new Scanner(System.in);
        ComplaintStatus complaintStatus = new ComplaintStatus("complaints.txt");

        System.out.println("Select an option:");
        System.out.println("1. View complaints");
        System.out.println("2. Update complaint status");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        switch (choice) {
            case 1:
                complaintStatus.viewComplaints();
                break;
            case 2:
                complaintStatus.updateComplaintStatus(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

}
