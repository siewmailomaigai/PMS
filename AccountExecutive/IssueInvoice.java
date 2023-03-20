/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccountExecutive;

/**
 *
 * @author 60192
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IssueInvoice {

    public IssueInvoice() {

    }

    public void runInvoice() {
        Scanner input = new Scanner(System.in);
        int cont;
        Scanner scanner = new Scanner(System.in);

        // Select an option: Resident/Tenant or Vendor
        System.out.println("Please select an option: ");
        System.out.println("1. Resident/Tenant");
        System.out.println("2. Vendor");
        int option1 = 0;
        boolean validOption1 = false;
        do {
            try {
                option1 = scanner.nextInt();
                if (option1 != 1 && option1 != 2) {
                    throw new Exception();
                }
                validOption1 = true;
            } catch (Exception e) {
                System.out.println("Invalid option selected. Please try again.");
                scanner.nextLine();
            }
        } while (!validOption1);

        scanner.nextLine(); // Consume newline character left over from nextInt()

        // Select an option: Issue invoice, Issue receipt or Generate statement
        System.out.println("Please select an option: ");
        System.out.println("1. Issue invoice");
        System.out.println("2. Issue receipt");
        System.out.println("3. Generate statement");
        int option2 = 0;
        boolean validOption2 = false;
        do {
            try {
                option2 = scanner.nextInt();
                if (option2 < 1 || option2 > 3) {
                    throw new Exception();
                }
                validOption2 = true;
            } catch (Exception e) {
                System.out.println("Invalid option selected. Please try again.");
                scanner.nextLine();
            }
        } while (!validOption2);

        scanner.nextLine(); // Consume newline character left over from nextInt()

        // Enter details for receipt, statement, or invoice
        String details = "";
        switch (option2) {
            case 1:
                System.out.println("Please enter the details for the invoice: ");
                System.out.println("ID: ");
                String id = scanner.nextLine();
                System.out.println("Date of invoice (MM/dd/yyyy): ");
                String date = scanner.nextLine();
                System.out.println("Description of Charges: ");
                String description = scanner.nextLine();
                System.out.println("Total Amount Due: ");
                Double amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Due Date (MM/dd/yyyy): ");
                String dueDate = scanner.nextLine();
                details = "ID: " + id + "\nDate issued: " + date + "\nDescription of Charges: " + description + "\nAmount: " + amount + "\nDueDate: " + dueDate;
                break;
            case 2:
                System.out.println("Please enter the details for the receipt: ");
                System.out.println("ID: ");
                String receiptID = scanner.nextLine();
                System.out.println("Date of receipt (MM/dd/yyyy): ");
                String receiptDate = scanner.nextLine();
                System.out.println("Description of Charges: ");
                String receiptDescription = scanner.nextLine();
                System.out.println("Total Amount Due: ");
                double totalAmount = scanner.nextDouble();

                double amountPaid = 0;
                boolean validAmountPaid = false;

                while (!validAmountPaid) {
                    System.out.println("Amount paid: ");
                    amountPaid = scanner.nextDouble();

                    if (amountPaid < totalAmount) {
                        System.out.println("Invalid amount paid. Amount paid must be greater than or equal to total amount due.");
                    } else {
                        validAmountPaid = true;
                    }
                }

                double change = amountPaid - totalAmount; // calculate the change
                System.out.println("Change: " + change); // print the change amount

                details = "ID: " + receiptID + "\nDate issued: " + receiptDate + "\nDescription of Charges: " + receiptDescription + "\nAmount: " + totalAmount + "\nAmount paid:" + amountPaid + "\nChange:" + change;
                break;

            case 3:
                System.out.println("Please enter the details for the statement: ");
                System.out.println("ID: ");
                String statementID = scanner.nextLine();
                System.out.println("Start date (MM/dd/yyyy): ");
                String startDate = scanner.nextLine();
                System.out.println("End date (MM/dd/yyyy): ");
                String endDate = scanner.nextLine();
                System.out.println("Total transactions: ");
                double totalTransactions = scanner.nextDouble();
                System.out.println("Initial balance: ");
                double balance = scanner.nextDouble();

                // Calculate current balance as total charges minus total payments
                double currentBalance = balance - totalTransactions;
                System.out.println("Current balance: " + currentBalance);

                details = "ID: " + statementID + "\nStart Date: " + startDate + "\nEnd Date: " + endDate
                        + "\nTotal Trannsaction: " + totalTransactions + "\nInitial Balance: " + balance
                        + "\nCurrent Balance: " + currentBalance;
                break;

            default:
                System.out.println("Invalid option selected.");
                scanner.close();
                return;
        }

        // Create file name based on selected options
        String fileName = "";

        switch (option1) {
            case 1:
                fileName = "resident_";
                break;
            case 2:
                fileName = "vendor_";
                break;
            default:
                System.out.println("Invalid option selected.");
                scanner.close();
                return;
        }

        switch (option2) {
            case 1:
                fileName += "invoice.txt";
                break;
            case 2:
                fileName += "receipt.txt";
                break;
            case 3:
                fileName += "statement.txt";
                break;
            default:
                System.out.println("Invalid option selected.");
                scanner.close();
                return;
        }

        // Write details to file with permissions for all users
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true), 0777); // Open file in append mode with permissions for all users
            writer.write(details);
            writer.newLine(); // Add newline character at the end of record for better readability
            writer.newLine();
            writer.close();

            System.out.println("Data saved to " + fileName + " successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file " + fileName);
        }

        scanner.close();
    }
}
