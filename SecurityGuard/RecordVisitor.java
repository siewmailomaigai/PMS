/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecurityGuard;

import static Resident_Tenant.LoginRT.checkId;
import Resident_Tenant.VisitorRT;
import static Resident_Tenant.VisitorRT.DATE_FORMATTER;
import static Resident_Tenant.VisitorRT.VISITORFILE;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Hong Shen
 */
public class RecordVisitor extends VisitorRT {

    ArrayList<RecordVisitor> visitor = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    private String VISITORFILE = "visitor.txt";

    public RecordVisitor() {
    }

    public RecordVisitor(String vID, String rtID, String name, String entryStatus, LocalDate date) {
        super(vID, rtID, name, entryStatus, date);
    }

    @Override
    public void updateVisitor() {
        System.out.print("Enter the Visitor ID: ");
        String checkVisitorID = input.nextLine();
        boolean found = false;
        try (Scanner scan = new Scanner(new File(VISITORFILE))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split("\\|");
                RecordVisitor vrt = new RecordVisitor();
                if (parts.length >= 5) {
                    vrt.setvID(parts[0]);
                    vrt.setRtID(parts[1]);
                    vrt.setName(parts[2]);
                    vrt.setDate(LocalDate.parse(parts[3], DATE_FORMATTER));
                    vrt.setEntryStatus(parts[4]);
                }
                if (vrt.getvID().equalsIgnoreCase(checkVisitorID)) {
                    found = true;
                    System.out.println("Visitor found. Entry Status: " + vrt.getEntryStatus());
                    System.out.println("Enter new entry status: ");
                    String newEntryStatus = input.nextLine();
                    vrt.setEntryStatus(newEntryStatus);
                }
                visitor.add(vrt);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
        }

        if (!found) {
            System.out.println("Visitor not found.");
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
                System.out.println("An error occurred when writing into the file.");
            }
            System.out.println("Update successful!");
        }
    }
}
