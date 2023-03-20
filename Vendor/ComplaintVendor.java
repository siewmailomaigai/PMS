/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vendor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Hong Shen
 */
public class ComplaintVendor extends LoginVendor{
    
    private String complaintID;
    private String id; 
    private LocalDate date; 
    private String complaintType;
    private String complaintDetail;
    private String complaintStatus;
    Scanner input = new Scanner(System.in);
    int choice, cont;
    public static final String FILE_NAME5 = "complaints.txt";
    public static final String DEFAULT_COMPLAINT_ID = "C00";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Format for date input
    ArrayList<ComplaintVendor> complaint = new ArrayList<>();

    public ComplaintVendor() {
        super();
    }

    public ComplaintVendor(String complaintID, String id, LocalDate date, String complaintType, String complaintDetail, String complaintStatus) {
        super();
        this.complaintID = complaintID;
        this.id = id;
        this.date = date;
        this.complaintType = complaintType;
        this.complaintDetail = complaintDetail;
        this.complaintStatus = complaintStatus;
    }

    public String getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getComplaintDetail() {
        return complaintDetail;
    }

    public void setComplaintDetail(String complaintDetail) {
        this.complaintDetail = complaintDetail;
    }

    public String getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    
    public void runComplaint() {

        do {
            System.out.println("1.Add New Complaint\n2.View Complaint\n3.Update Complaint\n4.Delete Complaint");
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    ComplaintVendor add = new ComplaintVendor();
                    add.addComplaint();
                }
                case 2 -> {
                    ComplaintVendor view = new ComplaintVendor();
                    view.viewComplaint();
                }
                case 3 -> {
                    ComplaintVendor update = new ComplaintVendor();
                    update.updateComplaint();
                }
                case 4 ->{
                    ComplaintVendor delete = new ComplaintVendor();
                    delete.deleteComplaint();
                }
                default ->
                    System.out.println("\nWrong input! Please try again!");
            }

            System.out.println("\nPlease Press 1 to Return to Back and Press 0 for Return to Main Menu.");
            cont = input.nextInt();

        } while (cont != 0);
    }
    
    
    public static String getLastComplaintId(){
        String lastId= DEFAULT_COMPLAINT_ID;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME5))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                lastId = parts[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }
    
    
    public static String generateNewComplaintId(String lastId){
        String numericPart = lastId.substring(1); // extract numeric part of ID
        int newNumericPart = Integer.parseInt(numericPart) + 1; // increment numeric part
        String newId = "C" + String.format("%02d", newNumericPart); // combine prefix and new numeric part
        return newId;
    }
    
    
    public void newComplaint() {
        String lastId=getLastComplaintId();
        complaintID = generateNewComplaintId(lastId);
        id = checkId;
        System.out.println("Enter date of complaint: ");
        date = LocalDate.parse(input.nextLine(),DATE_FORMATTER);
        System.out.println("Enter complaint type: ");
        complaintType = input.nextLine();
        System.out.println("Enter complaint's details: ");
        complaintDetail = input.nextLine();       
        complaintStatus = "Pending";
    }

    
    public void showComplaintInfo() {
        System.out.println("\nComplaints: ");
        System.out.println("Complaint ID: " + getComplaintID());
        System.out.println("User ID: " + getId());
        System.out.println("Date of complaint: " + getDate());
        System.out.println("Type of complaint: " + getComplaintType());
        System.out.println("Details of complaint: " + getComplaintDetail());
        System.out.println("Complaint Status: " + getComplaintStatus());
    }

    

    public void addComplaint() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME5))) {

                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    ComplaintVendor newC = new ComplaintVendor();
                    if (parts.length >= 6) {
                        newC.setComplaintID(parts[0]);
                        newC.setId(parts[1]);
                        newC.setDate(LocalDate.parse(parts[2],DATE_FORMATTER));
                        newC.setComplaintType(parts[3]);
                        newC.setComplaintDetail(parts[4]);
                        newC.setComplaintStatus(parts[5]);
                    }
                    complaint.add(newC);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'complaints.txt' was not found.");
        }

        ComplaintVendor newC2 = new ComplaintVendor();
        newC2.newComplaint();
        complaint.add(newC2);

        try {
            try (FileWriter infile = new FileWriter(FILE_NAME5)) {
                for (int i = 0; i < complaint.size(); i++) {
                    infile.append(
                            complaint.get(i).getComplaintID() + "|"
                            + complaint.get(i).getId() + "|"
                            + complaint.get(i).getDate() + "|"
                            + complaint.get(i).getComplaintType() + "|"
                            + complaint.get(i).getComplaintDetail() + "|"
                            + complaint.get(i).getComplaintStatus() + "\n");
                }
            }
        } catch (IOException ev) {
            System.out.println("An error occured when writing into the file.");
        }
    }

    
    public void viewComplaint() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME5))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    ComplaintVendor newC = new ComplaintVendor();
                    newC.setComplaintID(parts[0]);
                    newC.setId(parts[1]);
                    newC.setDate(LocalDate.parse(parts[2],DATE_FORMATTER));
                    newC.setComplaintType(parts[3]);
                    newC.setComplaintDetail(parts[4]);
                    newC.setComplaintStatus(parts[5]);
                    complaint.add(newC);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'complaints.txt' was not found.");
        }

        for (int x = 0; x < complaint.size(); x++) {
            if(checkId.equalsIgnoreCase(complaint.get(x).getId())){
                complaint.get(x).showComplaintInfo();
            }
        }
    }

    
    public void updateComplaint() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME5))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    ComplaintVendor newC = new ComplaintVendor();
                    if (parts.length >= 6) {
                        newC.setComplaintID(parts[0]);
                        newC.setId(parts[1]);
                        newC.setDate(LocalDate.parse(parts[2],DATE_FORMATTER));
                        newC.setComplaintType(parts[3]);
                        newC.setComplaintDetail(parts[4]);
                        newC.setComplaintStatus(parts[5]);
                    }
                    complaint.add(newC);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'complaints.txt' was not found.");
        }

        System.out.print("Enter the complaintID: ");
        String checkComplaintID = input.nextLine();
        boolean found = false;
        for (int a = 0; a < complaint.size(); a++) {
            if (complaint.get(a).getComplaintID().equalsIgnoreCase(checkComplaintID) && 
                    complaint.get(a).getId().equalsIgnoreCase(checkId)) {
                found = true;
                System.out.println("Select the field you want to modify");
                System.out.println("1. Date");
                System.out.println("2. Type of Complaint");
                System.out.println("3. Detail of Complaint");
                System.out.println("4. Cancel");
                System.out.print("Enter choice: ");
                int choice1 = input.nextInt();
                input.nextLine();
                ComplaintVendor c = complaint.get(a);
                switch (choice1) {
                    case 1 -> {
                        System.out.print("Enter new date: ");
                        LocalDate newDate = LocalDate.parse(input.nextLine(),DATE_FORMATTER);
                        c.setDate(newDate);
                    }
                    case 2 -> {
                        System.out.print("Enter new type of complaint: ");
                        String newComplaintType = input.nextLine();
                        c.setComplaintType(newComplaintType);
                    }
                    case 3-> {
                        System.out.println("Enter new details of complaint: ");
                        String newComplaintDetail=input.nextLine();
                        c.setComplaintDetail(newComplaintDetail);
                    }
                    case 4 -> {
                        System.out.print("Enter new status of complaint: ");
                        String newComplaintStatus = input.nextLine();
                        c.setComplaintStatus(newComplaintStatus);
                    }
                    default ->
                        System.out.println("Invalid input.");
                    }

                complaint.set(a, c);
                System.out.println("Update successful!");
                break;
            }
        }
        if (!found) {
            System.out.println("Complaint not found.");
        } else {
            try {
                try (FileWriter infile = new FileWriter(FILE_NAME5)) {
                    for (int i = 0; i < complaint.size(); i++) {
                        infile.append(
                            complaint.get(i).getComplaintID() + "|"
                            + complaint.get(i).getId() + "|"
                            + complaint.get(i).getDate() + "|"
                            + complaint.get(i).getComplaintType() + "|"
                            + complaint.get(i).getComplaintDetail() + "|"
                            + complaint.get(i).getComplaintStatus() + "\n");
                    }
                }
            } catch (IOException ev) {
                System.out.println("An error occured when writing into the file.");
            }
        }
    }
    
    public void deleteComplaint() {
        System.out.print("Enter Complaint ID to be deleted: ");
        Scanner scan = new Scanner(System.in);
        String complaintIdDelete = scan.nextLine();

        StringBuilder contentBuilder = new StringBuilder();
        boolean complaintIdFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME5))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] parts = currentLine.split("\\|");
                String savedComplaintId = (parts[0]);
                String savedId = (parts[1]);
                if (savedComplaintId.equalsIgnoreCase(complaintIdDelete) && checkId.equalsIgnoreCase(savedId)) {
                    complaintIdFound = true;
                } else {
                    contentBuilder.append(currentLine).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!complaintIdFound) {
            System.out.println("Complaint ID " + complaintIdDelete + " was not found.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME5))) {
            bw.write(contentBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Deleted the complaint ID: " + complaintIdDelete);
    }
}
