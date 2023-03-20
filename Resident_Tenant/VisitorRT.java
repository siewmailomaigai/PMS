/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resident_Tenant;

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
public class VisitorRT extends LoginRT{
    private String vID;
    private String rtID;
    private String name;
    private String entryStatus;
    private LocalDate date;
    public static final String VISITORFILE = "visitor.txt";
    public static final String DEFAULT_VISITOR_ID = "V00";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Format for date input
    ArrayList<VisitorRT> visitor = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    int cont;
    //VisitorRT ui=new VisitorRT();
    
    
    public VisitorRT(){
        super();
    }
    public VisitorRT(String vID, String rtID, String name, String entryStatus, LocalDate date) {
        super();
        this.vID = vID;
        this.rtID = rtID;
        this.name = name;
        this.entryStatus = entryStatus;
        this.date = date;
    }
    
    public String getvID() {
        return vID;
    }

    public void setvID(String vID) {
        this.vID = vID;
    }

    public String getRtID() {
        return rtID;
    }

    public void setRtID(String rtID) {
        this.rtID = rtID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }
   
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    
    public void runVisitorRT() {
        do {
            System.out.println("1.New Visitor\n2.View Visitor\n3.Update Visitor\n4.Delete Visitor");
            int choice = input.nextInt();

            //add or new resident/tenant details
            switch (choice) {
                case 1 -> {
                    VisitorRT add = new VisitorRT();
                    add.addVisitor();
                }
                case 2 -> {
                    VisitorRT view = new VisitorRT();
                    view.viewVisitor();
                }
                case 3 -> {
                    VisitorRT update = new VisitorRT();
                    update.updateVisitor();
                }
                case 4 -> {
                    VisitorRT delete = new VisitorRT();
                    delete.deleteVisitor();
                }
                default ->
                    System.out.println("\nWrong input! Please try again!");
            }
            System.out.println("\nPlease Press 1 to Return to Back and Press 0 for Return to Main Menu.");
            cont = input.nextInt();
        } while (cont != 0);
    }
    
    public static String getLastVisitorId(){
        String lastId= DEFAULT_VISITOR_ID;
        try (BufferedReader br = new BufferedReader(new FileReader(VISITORFILE))) {
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
    
    
    public static String generateNewVisitorId(String lastId){
        String numericPart = lastId.substring(1); // extract numeric part of ID
        int newNumericPart = Integer.parseInt(numericPart) + 1; // increment numeric part
        String newId = "V" + String.format("%02d", newNumericPart); // combine prefix and new numeric part
        return newId;
    }
    
    
    public void newVisitorRT() {
        String lastId=getLastVisitorId();
        vID = generateNewVisitorId(lastId);
        rtID = checkId;
        System.out.println("Enter visitor name: ");
        name = input.nextLine();
        System.out.println("Enter date (yyyy-MM-dd): ");
        date = LocalDate.parse(input.nextLine(),DATE_FORMATTER);
        entryStatus = "No";
    }
    
    
    public void addVisitor(){
        try {
            try (Scanner scan = new Scanner(new File(VISITORFILE))) {

                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    VisitorRT vrt = new VisitorRT();
                    if (parts.length >= 5) {
                        vrt.setvID(parts[0]);
                        vrt.setRtID(parts[1]);
                        vrt.setName(parts[2]);
                        vrt.setDate(LocalDate.parse(parts[3],DATE_FORMATTER));
                        vrt.setEntryStatus(parts[4]);
                    }
                    visitor.add(vrt);
                }
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
        }
        
        VisitorRT vrt2 = new VisitorRT();
        vrt2.newVisitorRT();
        visitor.add(vrt2);

        try {
            try (FileWriter infile = new FileWriter(VISITORFILE)) {
                for (int i = 0; i < visitor.size(); i++) {
                    infile.append(
                            visitor.get(i).getvID() + "|"
                            + visitor.get(i).getRtID() + "|"
                            + visitor.get(i).getName() + "|"
                            + visitor.get(i).getDate() + "|"
                            + visitor.get(i).getEntryStatus() + "\n");
                }
            }
        } catch (IOException ev) {
            System.out.println("An error occured when writing into the file.");
        }
        
    }
    
    
    public void showVisitorInfo() {
        System.out.println("\nVisitor: ");
        System.out.println("Visitor ID: " + getvID());
        System.out.println("Resident/Tenant ID: " + getRtID());
        System.out.println("Name: " + getName());
        System.out.println("Date: " + getDate());
        System.out.println("Entry Status: " + getEntryStatus());
    }    
    
    
    public void viewVisitor() {
        try {
            try (Scanner scan = new Scanner(new File(VISITORFILE))) {

                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    VisitorRT vrt = new VisitorRT();
                    if (parts.length >= 5) {
                        vrt.setvID(parts[0]);
                        vrt.setRtID(parts[1]);
                        vrt.setName(parts[2]);
                        vrt.setDate(LocalDate.parse(parts[3],DATE_FORMATTER));
                        vrt.setEntryStatus(parts[4]);
                    }
                    visitor.add(vrt);
                }
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
        }

        for (int x = 0; x < visitor.size(); x++) {
            if(checkId.equalsIgnoreCase(visitor.get(x).getRtID())){
                visitor.get(x).showVisitorInfo();
            }
        }
    }

    
    public void updateVisitor() {
        try {
            try (Scanner scan = new Scanner(new File(VISITORFILE))) {

                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    VisitorRT vrt = new VisitorRT();
                    if (parts.length >= 5) {
                        vrt.setvID(parts[0]);
                        vrt.setRtID(parts[1]);
                        vrt.setName(parts[2]);
                        vrt.setDate(LocalDate.parse(parts[3],DATE_FORMATTER));
                        vrt.setEntryStatus(parts[4]);
                    }
                    visitor.add(vrt);
                }
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
        }

        System.out.print("Enter the Visitor ID: ");
        String checkVisitorID = input.nextLine();
        boolean found = false;
        for (int a = 0; a < visitor.size(); a++) {
            if (visitor.get(a).getvID().equalsIgnoreCase(checkVisitorID) && 
                    visitor.get(a).getRtID().equalsIgnoreCase(checkId)) {
                found = true;
                System.out.println("Select the field you want to modify");
                System.out.println("1. Name");
                System.out.println("2. Date");
                System.out.println("3. Cancel");
                System.out.print("Enter choice: ");
                int choice1 = input.nextInt();
                input.nextLine();
                VisitorRT v = visitor.get(a);
                switch (choice1) {
                    case 1 ->{
                        System.out.println("Enter new name: ");
                        String newName = input.nextLine();
                        v.setName(newName);
                    }
                    case 2 -> {
                        System.out.print("Enter new date: ");
                        LocalDate newDate = LocalDate.parse(input.nextLine(),DATE_FORMATTER);
                        v.setDate(newDate);
                    }
                    case 3-> {
                        return;
                    }
                    default ->
                        System.out.println("Invalid input.");
                    }

                visitor.set(a, v);
                System.out.println("Update successful!");
                break;
            }
        }
        if (!found) {
            System.out.println("Complaint not found.");
        } else {
            try {
                try (FileWriter infile = new FileWriter(VISITORFILE)) {
                    for (int i = 0; i < visitor.size(); i++) {
                        infile.append(
                            visitor.get(i).getvID() + "|"
                            + visitor.get(i).getRtID() + "|"
                            + visitor.get(i).getName() + "|"
                            + visitor.get(i).getDate() + "|"
                            + visitor.get(i).getEntryStatus() + "\n");
                    }
                }
            } catch (IOException ev) {
                System.out.println("An error occured when writing into the file.");
            }
        }
    }
    
    public void deleteVisitor() {
        System.out.print("Enter visitor ID to be deleted: ");
        Scanner scan = new Scanner(System.in);
        String visitorIdDelete = scan.nextLine();

        StringBuilder contentBuilder = new StringBuilder();
        boolean visitorIdFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(VISITORFILE))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] parts = currentLine.split("\\|");
                String savedVisitorId = (parts[0]);
                String savedId = (parts[1]);
                if (savedVisitorId.equalsIgnoreCase(visitorIdDelete) && checkId.equalsIgnoreCase(savedId)) {
                    visitorIdFound = true;
                } else {
                    contentBuilder.append(currentLine).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!visitorIdFound) {
            System.out.println("Visitor ID " + visitorIdDelete + " was not found.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VISITORFILE))) {
            bw.write(contentBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Deleted the visitor ID: " + visitorIdDelete);
    }
}
