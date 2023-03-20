/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BuildingManager;

/**
 *
 * @author 60192
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Budget {

    private int year;
    private double[] budgets;
    private double[] maintenance;

    public Budget() {
        this.budgets = new double[12];
        this.maintenance = new double[12];
    }

    public Budget(int year) throws IOException {
        this.year = year;
        budgets = new double[12];
        maintenance = new double[12];

        // Create budgets.txt file if it does not exist
        File budgetsFile = new File("budgets.txt");
        if (!budgetsFile.exists()) {
            budgetsFile.createNewFile();
        }

        // Load budgets from budgets.txt file
        Scanner budgetsScanner = new Scanner(budgetsFile);
        while (budgetsScanner.hasNextLine()) {
            String[] budgetDetails = budgetsScanner.nextLine().split(",");
            if (Integer.parseInt(budgetDetails[0]) == year) {
                for (int i = 0; i < 12; i++) {
                    budgets[i] = Double.parseDouble(budgetDetails[i + 1]);
                }
                break;
            }
        }
        budgetsScanner.close();

        // Create maintenance.txt file if it does not exist
        File maintenanceFile = new File("maintenance.txt");
        if (!maintenanceFile.exists()) {
            maintenanceFile.createNewFile();
        }

        // Load maintenance from maintenance.txt file
        Scanner maintenanceScanner = new Scanner(maintenanceFile);
        while (maintenanceScanner.hasNextLine()) {
            String[] maintenanceDetails = maintenanceScanner.nextLine().split(",");
            if (Integer.parseInt(maintenanceDetails[0]) == year) {
                for (int i = 0; i < 12; i++) {
                    maintenance[i] = Double.parseDouble(maintenanceDetails[i + 1]);
                }
                break;
            }
        }
        maintenanceScanner.close();
    }

// Method to calculate the total amount spent on maintenance for a specific year
    public double calculateMaintenanceCost() {
        double total = 0;
        for (double m : maintenance) {
            total += m;
        }
        return total;
    }

// Method to calculate the total amount allocated for a specific month
    public double calculateAllocation(int month) {
        return budgets[month - 1];
    }

// Method to set the budget for a specific month
    public void setBudget(int month, double budget) throws IOException {
        budgets[month - 1] = budget;

        // Save the updated budget to budgets.txt file
        FileWriter writer = new FileWriter("budgets.txt");
        writer.write(Integer.toString(year) + ",");
        for (double b : budgets) {
            writer.write(Double.toString(b) + ",");
        }
        writer.close();
    }

// Method to set the maintenance cost for a specific month
    public void setMaintenance(int month, double cost) throws IOException {
        maintenance[month - 1] = cost;

        // Save the updated maintenance cost to maintenance.txt file
        FileWriter writer = new FileWriter("maintenance.txt");
        writer.write(Integer.toString(year) + ",");
        for (double m : maintenance) {
            writer.write(Double.toString(m) + ",");
        }
        writer.close();
    }

// Method to prompt user for input and perform action based on selection
    public void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Please select an option:");
            System.out.println("1. Calculate total maintenance cost for the year");
            System.out.println("2. Calculate total amount allocated for a specific month");
            System.out.println("3. Set the budget for a specific month");
            System.out.println("4. Set the maintenance cost for a specific month");
            System.out.println("5. Exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    double maintenanceCost = calculateMaintenanceCost();
                    System.out.println("The total maintenance cost for the year is: " + maintenanceCost);
                    break;
                case 2:
                    System.out.println("Enter the month number (1-12):");
                    int month = scanner.nextInt();
                    double allocation = calculateAllocation(month);
                    System.out.println("The total amount allocated for month " + month + " is: " + allocation);
                    break;
                case 3:
                    System.out.println("Enter the month number (1-12):");
                    int budgetMonth = scanner.nextInt();
                    System.out.println("Enter the budget for month " + budgetMonth + ":");
                    double budgetAmount = scanner.nextDouble();
                    setBudget(budgetMonth, budgetAmount);
                    System.out.println("Budget for month " + budgetMonth + " set to " + budgetAmount);
                    break;
                case 4:
                    System.out.println("Enter the month number (1-12):");
                    int maintenanceMonth = scanner.nextInt();
                    System.out.println("Enter the maintenance cost for month " + maintenanceMonth + ":");
                    double maintenanceCostAmount = scanner.nextDouble();
                    setMaintenance(maintenanceMonth, maintenanceCostAmount);
                    System.out.println("Maintenance cost for month " + maintenanceMonth + " set to " + maintenanceCostAmount);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }
}
