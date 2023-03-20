/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccountExecutive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author 60192
 */
public class PaymentCheck {
    public void paymentCheck() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an option:");
        System.out.println("1. Resident");
        System.out.println("2. Vendor");

        int option1 = scanner.nextInt();

        if (option1 == 1) {
            System.out.println("Select an option:");
            System.out.println("1. Record");
            System.out.println("2. View");
            System.out.println("3. Update");

            int option2 = scanner.nextInt();

            if (option2 == 1) {
                try (PrintWriter writer = new PrintWriter(new FileWriter("residenttoberemoved.txt"))) {
                    writer.println("Generate receipt:");
                    BufferedReader reader = new BufferedReader(new FileReader("residentpayment_history.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.println(line);
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the file.");
                    e.printStackTrace();
                }
                System.out.println("Payment recorded");
            } else if (option2 == 2) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("residentpayment_history.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the file.");
                    e.printStackTrace();
                }
            } else if (option2 == 3) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("residenttoberemoved.txt"));
                    String line;
                    String residentIds = "";
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("Resident ID:")) {
                            residentIds += line.substring(line.indexOf(":") + 2) + ", ";
                        }
                    }
                    if (residentIds.isEmpty()) {
                        System.out.println("No residents found in the file.");
                    } else {
                        System.out.println("Generate a receipt for resident ID(s): " + residentIds.substring(0, residentIds.length() - 2));
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the file.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid option selected.");
                return;
            }

        } else if (option1 == 2) {
            System.out.println("Select an option:");
            System.out.println("1. Record");
            System.out.println("2. View");
            System.out.println("3. Update");

            int option2 = scanner.nextInt();

            if (option2 == 1) {
                try (PrintWriter writer = new PrintWriter(new FileWriter("vendortoberemoved.txt"))) {
                    writer.println("Generate receipt:");
                    BufferedReader reader = new BufferedReader(new FileReader("vendorpayment_history.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.println(line);
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the file.");
                    e.printStackTrace();
                }
                System.out.println("Payment recorded");
            } else if (option2 == 2) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("vendorpayment_history.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the file.");
                    e.printStackTrace();
                }
            } else if (option2 == 3) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("vendortoberemoved.txt"));
                    String line;
                    String vendorIds = "";
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("Vendor ID:")) {
                            vendorIds += line.substring(line.indexOf(":") + 2) + ", ";
                        }
                    }
                    if (vendorIds.isEmpty()) {
                        System.out.println("No vendors found in the file.");
                    } else {
                        System.out.println("Generate a receipt for vendor ID(s): " + vendorIds.substring(0, vendorIds.length() - 2));
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the file.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid option selected.");
                return;
            }

        } else {
            System.out.println("Invalid option selected.");
            return;
        }

    }

}
