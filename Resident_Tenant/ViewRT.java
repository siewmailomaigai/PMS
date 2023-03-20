/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resident_Tenant;

/**
 *
 * @author 60192
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ViewRT {
    public void ViewResidentTenant() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an option:");
        System.out.println("1. View invoice");
        System.out.println("2. View statement");
        System.out.println("3. View receipt");
        System.out.println("4. View pending fee");
        System.out.println("5. View payment history");
        int option = scanner.nextInt();

        String filename;
        switch (option) {
            case 1:
                filename = "resident_invoice.txt";
                break;
            case 2:
                filename = "resident_statement.txt";
                break;
            case 3:
                filename = "resident_receipt.txt";
                break;
            case 4:
                filename = "resident_invoice.txt";
                break;
            case 5:
                filename = "residentpayment_history.txt";
                break;
            default:
                System.out.println("Invalid option selected.");
                return;
        }

        System.out.print("Enter Resident ID: ");
        scanner.nextLine(); // consume the newline character left by scanner.nextInt()
        String id = scanner.nextLine().trim();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Resident ID:")) {
                    String[] fields = line.split(": ");
                    if (fields.length >= 2 && fields[1].equals(id)) {
                        found = true;
                        System.out.println(line);
                        while ((line = br.readLine()) != null && !line.isEmpty()) {
                            System.out.println(line);
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("No record found for Resident ID " + id);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

