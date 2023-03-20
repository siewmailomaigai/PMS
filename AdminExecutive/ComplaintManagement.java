/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminExecutive;

/**
 *
 * @author Hong Shen
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ComplaintManagement {

    private String complaintID;
    private String id;
    private LocalDate date;
    private String complaintType;
    private String complaintDetail;
    private String complaintStatus;
    Scanner input = new Scanner(System.in);
    Scanner search4 = new Scanner(System.in);
    int choice, cont;
    public static final String FILE_NAME5 = "complaints.txt";
    public static final String DEFAULT_COMPLAINT_ID = "C00";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Format for date input
    ArrayList<ComplaintManagement> complaint = new ArrayList<>();
    //ComplaintManagement ui=new ComplaintManagement();

    public ComplaintManagement() {
    }

    public ComplaintManagement(String complaintID, String id, LocalDate date, String complaintType, String complaintDetail, String complaintStatus) {
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
        Scanner input = new Scanner(System.in);
        int cont;
        do {
            System.out.println("1.Add New Complaint\n2.View Complaint\n3.Update Complaint");
            choice = input.nextInt();

            //add or new resident/tenant details
            switch (choice) {
                case 1 -> {
                    ComplaintManagement add = new ComplaintManagement();
                    add.addComplaint();

                }
                case 2 -> {
                    ComplaintManagement view = new ComplaintManagement();
                    view.viewComplaint();

                }
                case 3 -> {
                    ComplaintManagement update = new ComplaintManagement();
                    update.updateComplaint();

                }

                default ->
                    System.out.println("\nWrong input! Please try again!");
            }

            System.out.println("\nPress 1 to go back and Press 0 to return to menu.");
            do {
                cont = input.nextInt();
                if (cont == 0 || cont == 1) {
                    break; // Valid input, break out of loop
                }
                System.out.println("\nInvalid input! Please try again!");
            } while (true);

            if (cont == 0) {
                return; // Return to menu
            }
        } while (true);
    }

    public static String getLastComplaintId() {
        String lastId = DEFAULT_COMPLAINT_ID;
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

    public static String generateNewComplaintId(String lastId) {
        String numericPart = lastId.substring(1); // extract numeric part of ID
        int newNumericPart = Integer.parseInt(numericPart) + 1; // increment numeric part
        String newId = "C" + String.format("%02d", newNumericPart); // combine prefix and new numeric part
        return newId;
    }

    public void newComplaint() {
        String lastId = getLastComplaintId();
        complaintID = generateNewComplaintId(lastId);
        System.out.println("Enter User ID: ");
        id = input.nextLine();
        System.out.println("Enter date of complaint: ");
        date = LocalDate.parse(input.nextLine(), DATE_FORMATTER);
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
        System.out.println("Date of complaint (yyyy-MM-dd): " + getDate());
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
                    ComplaintManagement newC = new ComplaintManagement();
                    if (parts.length >= 6) {
                        newC.setComplaintID(parts[0]);
                        newC.setId(parts[1]);
                        newC.setDate(LocalDate.parse(parts[2], DATE_FORMATTER));
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

        ComplaintManagement newC2 = new ComplaintManagement();
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
                    ComplaintManagement newC = new ComplaintManagement();
                    newC.setComplaintID(parts[0]);
                    newC.setId(parts[1]);
                    newC.setDate(LocalDate.parse(parts[2], DATE_FORMATTER));
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
            complaint.get(x).showComplaintInfo();
        }

    }

    public void updateComplaint() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME5))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    ComplaintManagement newC = new ComplaintManagement();
                    if (parts.length >= 6) {
                        newC.setComplaintID(parts[0]);
                        newC.setId(parts[1]);
                        newC.setDate(LocalDate.parse(parts[2], DATE_FORMATTER));
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
        String checkComplaintID = search4.nextLine();
        boolean found = false;
        for (int a = 0; a < complaint.size(); a++) {
            if (complaint.get(a).getComplaintID().equalsIgnoreCase(checkComplaintID)) {
                found = true;
                System.out.println("Select the field you want to modify");
                System.out.println("1. User ID");
                System.out.println("2. Date");
                System.out.println("3. Type of Complaint");
                System.out.println("4. Detail of Complaint");
                System.out.println("5. Status of Complaint");
                System.out.println("6. Cancel");
                System.out.print("Enter choice: ");
                int choice1 = input.nextInt();
                input.nextLine();
                ComplaintManagement c = complaint.get(a);
                switch (choice1) {
                    case 1 -> {

                        System.out.print("Enter new customer name: ");
                        String newId = input.nextLine();
                        c.setId(newId);
                    }
                    case 2 -> {
                        System.out.print("Enter new date: ");
                        LocalDate newDate = LocalDate.parse(input.nextLine(), DATE_FORMATTER);
                        c.setDate(newDate);
                    }
                    case 3 -> {
                        System.out.print("Enter new type of complaint: ");
                        String newComplaintType = input.nextLine();
                        c.setComplaintType(newComplaintType);
                    }
                    case 4 -> {
                        System.out.println("Enter new details of complaint: ");
                        String newComplaintDetail = input.nextLine();
                        c.setComplaintDetail(newComplaintDetail);
                    }

                    case 5 -> {
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
}
