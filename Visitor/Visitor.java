/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visitor;

import Resident_Tenant.VisitorRT;
import static Resident_Tenant.VisitorRT.VISITORFILE;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Hong Shen
 */
public class Visitor extends VisitorRT{
    ArrayList<Visitor> visitor = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    public Visitor() {
    }

    public Visitor(String vID, String rtID, String name, String entryStatus, LocalDate date) {
        super(vID, rtID, name, entryStatus, date);
    }
    
    
    public void runViewVisitor() {
        try {
            try (Scanner scan = new Scanner(new File(VISITORFILE))) {

                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    Visitor vrt = new Visitor();
                    if (parts.length >= 4) {
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
            System.out.println("No records.");
        }
        
        System.out.println("Enter visitor ID: ");
        String checkVisitorID = input.nextLine();
        System.out.println("Enter resident/tenant ID: ");
        String checkRTid = input.nextLine();
        for (int a = 0; a < visitor.size(); a++) {
            if (visitor.get(a).getvID().equalsIgnoreCase(checkVisitorID) && 
                    visitor.get(a).getRtID().equalsIgnoreCase(checkRTid)) {
                visitor.get(a).showVisitorInfo();
            } else {
                System.out.println("Visitor pass not found");
            }
        }
        
    }
}

