/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccountExecutive;

/**
 *
 * @author 60192
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OutstandingFee {

    public OutstandingFee() {

    }

    public void addOutstanding() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an option:");
        System.out.println("1. Resident");
        System.out.println("2. Vendor");
        int option = scanner.nextInt();
        String filename;
        String idType;
        String fileType;
        String outstandingFeeFilename;
        switch (option) {
            case 1:
                System.out.println("Adding outstanding fee for a resident:");
                idType = "Resident ID";
                fileType = "resident";
                outstandingFeeFilename = "resident_outstandingFee.txt";
                break;
            case 2:
                System.out.println("Adding outstanding fee for a vendor:");
                idType = "Vendor ID";
                fileType = "vendor";
                outstandingFeeFilename = "vendor_outstandingFee.txt";
                break;
            default:
                System.out.println("Invalid option selected.");
                return;
        }

        System.out.println("Enter " + idType + ":");
        scanner.nextLine(); // consume the newline character left by scanner.nextInt()
        String id = scanner.nextLine().trim();

        filename = fileType + "_invoice.txt";
        if (!checkIfFileExists(filename)) {
            System.out.println("The file " + filename + " does not exist.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean found = false;
            StringBuilder record = new StringBuilder();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(": ");
                if (fields.length >= 2 && fields[1].equals(id)) {
                    found = true;
                    record.append(line).append("\n");
                    while ((line = br.readLine()) != null && !line.isEmpty()) {
                        record.append(line).append("\n");
                    }
                    record.append("\n");
                }
            }
            if (!found) {
                System.out.println("No record found with " + idType + " " + id);
                return;
            }

            if (!checkIfFileExists(outstandingFeeFilename)) {
                createFile(outstandingFeeFilename);
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outstandingFeeFilename, true))) {
                bw.write(record.toString());
                System.out.println("Record added to " + outstandingFeeFilename + ".");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    private boolean checkIfFileExists(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            fileReader.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void createFile(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    public void viewOutstanding() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an option:");
        System.out.println("1. View outstanding fees for residents");
        System.out.println("2. View outstanding fees for vendors");
        int option = scanner.nextInt();
        String filename;
        switch (option) {
            case 1:
                System.out.println("Viewing outstanding fees for residents:");
                filename = "resident_outstandingFee.txt";
                break;
            case 2:
                System.out.println("Viewing outstanding fees for vendors:");
                filename = "vendor_outstandingFee.txt";
                break;
            default:
                System.out.println("Invalid option selected.");
                return;

        }

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

}
