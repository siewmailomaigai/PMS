/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminExecutive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Hong Shen
 */
public class VendorManagement {

    private String id;
    private String name;
    private int age;
    private String password;
    private String shopId;
    public static final String VENDORFILE = "vendorfile.txt";
    public static final String DEFAULT_VENDOR_ID = "V00";
    ArrayList<VendorManagement> vendor = new ArrayList<>();
    //VendorManagement ui=new VendorManagement();

    Scanner input = new Scanner(System.in);
    Scanner search = new Scanner(System.in);
    int choice, cont;

    //default constructor
    public VendorManagement() {
    }

    //constructor with arguments
    public VendorManagement(String id, String name, String password, int age, String shopId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.shopId = shopId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getShopId() {
        return shopId;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void runVendor() {
        Scanner input = new Scanner(System.in);
        int cont;
        do {
            System.out.println("1.Add New Vendor\n2.Modify Vendor\n3.Search Vendor\n4.View Vendor\n5.Delete Vendor");
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    VendorManagement add = new VendorManagement();
                    add.addVendor();

                }
                case 2 -> {
                    VendorManagement modify = new VendorManagement();
                    modify.modifyVendor();

                }
                case 3 -> {
                    VendorManagement searchRT = new VendorManagement();
                    searchRT.searchVendor();

                }
                case 4 -> {
                    VendorManagement view = new VendorManagement();
                    view.viewVendor();

                }
                case 5 -> {
                    VendorManagement delete = new VendorManagement();
                    delete.deleteVendor();
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

    public static String getLastVendorId() {
        String lastId = DEFAULT_VENDOR_ID;
        try (BufferedReader br = new BufferedReader(new FileReader(VENDORFILE))) {
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

    public static String generateNewVendorId(String lastId) {
        String numericPart = lastId.substring(1); // extract numeric part of ID
        int newNumericPart = Integer.parseInt(numericPart) + 1; // increment numeric part
        String newId = "V" + String.format("%02d", newNumericPart); // combine prefix and new numeric part
        return newId;
    }

    public void newVendor() {
        String lastId = getLastVendorId();
        id = generateNewVendorId(lastId);
        System.out.println("Enter Vendor name: ");
        name = input.nextLine();
        System.out.println("Enter Vendor password: ");
        password = input.nextLine();
        System.out.println("Enter Vendor age: ");
        age = input.nextInt();
        input.nextLine();
        System.out.println("Enter Vendor Shop ID: ");
        shopId = input.nextLine();
    }

    public void showVendorInfo() {
        System.out.println("\nVendor Information:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Shop ID: " + getShopId());
    }

    public void addVendor() {
        try {
            try (Scanner scan = new Scanner(new File(VENDORFILE))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    VendorManagement newV = new VendorManagement();
                    newV.setId(parts[0]);
                    newV.setName(parts[1]);
                    newV.setPassword(parts[2]);
                    newV.setAge(Integer.parseInt(parts[3]));
                    newV.setShopId(parts[4]);
                    vendor.add(newV);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No records!");
        }

        VendorManagement newV = new VendorManagement();
        newV.newVendor();
        vendor.add(newV);

        try {
            try (FileWriter infile = new FileWriter(VENDORFILE)) {
                for (int i = 0; i < vendor.size(); i++) {
                    infile.append(vendor.get(i).getId() + "|"
                            + vendor.get(i).getName() + "|"
                            + vendor.get(i).getPassword() + "|"
                            + vendor.get(i).getAge() + "|"
                            + vendor.get(i).getShopId() + "\n");
                }
            }
        } catch (IOException ev) {
            System.out.println("An error occured when writing into the file.");
        }
    }

    public void modifyVendor() {
        try {
            try (Scanner scan = new Scanner(new File(VENDORFILE))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    VendorManagement newV = new VendorManagement();
                    newV.setId(parts[0]);
                    newV.setName(parts[1]);
                    newV.setPassword(parts[2]);
                    newV.setAge(Integer.parseInt(parts[3]));
                    newV.setShopId(parts[4]);
                    vendor.add(newV);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'resident_file.txt' was not found.");
        }

        System.out.print("Enter Vendor ID: ");
        String checkId = search.nextLine();
        boolean found = false;
        for (int a = 0; a < vendor.size(); a++) {
            if (vendor.get(a).getId().equalsIgnoreCase(checkId)) {
                found = true;
                System.out.println("Select the field you want to modify");
                System.out.println("1. Name");
                System.out.println("2. Password");
                System.out.println("3. Age");
                System.out.println("4. Shop ID");
                System.out.println("5. Cancel");
                System.out.print("Enter choice: ");
                int choice1 = input.nextInt();
                input.nextLine();
                VendorManagement v = vendor.get(a);
                switch (choice1) {
                    case 1 -> {
                        System.out.print("Enter new name: ");
                        String new_name = input.nextLine();
                        v.setName(new_name);
                    }
                    case 2 -> {
                        System.out.println("Enter new password: ");
                        String new_password = input.nextLine();
                        v.setPassword(new_password);
                    }
                    case 3 -> {
                        System.out.print("Enter new age: ");
                        int new_age = input.nextInt();
                        input.nextLine();
                        v.setAge(new_age);
                    }
                    case 4 -> {
                        System.out.println("Enter new shop ID: ");
                        String new_shopId = input.nextLine();
                        v.setShopId(new_shopId);
                    }
                    case 5 -> {
                        System.out.println("Modification canceled.");
                        return;
                    }
                    default ->
                        System.out.println("Invalid input.");
                }

                vendor.set(a, v);
                System.out.println("Modification successful!");
                break;
            }
        }
        if (!found) {
            System.out.println("Vendor not found.");
        } else {
            try {
                try (FileWriter infile = new FileWriter(VENDORFILE)) {
                    for (int i = 0; i < vendor.size(); i++) {
                        infile.append(vendor.get(i).getId() + "|"
                                + vendor.get(i).getName() + "|"
                                + vendor.get(i).getPassword() + "|"
                                + vendor.get(i).getAge() + "|"
                                + vendor.get(i).getShopId() + "\n");
                    }
                }
            } catch (IOException ev) {
                System.out.println("An error occured when writing into the file.");
            }
        }
    }

    public void searchVendor() {
        try {
            try (Scanner scan = new Scanner(new File(VENDORFILE))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    VendorManagement newV = new VendorManagement();
                    newV.setId(parts[0]);
                    newV.setName(parts[1]);
                    newV.setPassword(parts[2]);
                    newV.setAge(Integer.parseInt(parts[3]));
                    newV.setShopId(parts[4]);
                    vendor.add(newV);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'resident_file.txt' was not found.");
        }

        System.out.print("Enter the vendor ID: ");
        String searchId = search.nextLine();
        boolean foundResident = false;
        for (int a = 0; a < vendor.size(); a++) {
            if (searchId.equals(vendor.get(a).getId())) {
                if (!foundResident) {
                    System.out.println("Vendor with the ID " + searchId + ":");
                    foundResident = true;
                }
                vendor.get(a).showVendorInfo();
            }
        }
        if (!foundResident) {
            System.out.println("No Vendor with the ID " + searchId + ".");
        }

    }

    public void viewVendor() {
        try {
            try (Scanner scan = new Scanner(new File(VENDORFILE))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    VendorManagement newV = new VendorManagement();
                    newV.setId(parts[0]);
                    newV.setName(parts[1]);
                    newV.setPassword(parts[2]);
                    newV.setAge(Integer.parseInt(parts[3]));
                    newV.setShopId(parts[4]);
                    vendor.add(newV);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
        }

        for (int x = 0; x < vendor.size(); x++) {
            vendor.get(x).showVendorInfo();
        }
    }

    public void deleteVendor() {
        System.out.print("Enter the vendor ID  to be deleted: ");
        Scanner scan = new Scanner(System.in);
        String idToDelete = scan.nextLine();

        StringBuilder contentBuilder = new StringBuilder();
        boolean vendorFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(VENDORFILE))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] parts = currentLine.split("\\|");
                String savedId = (parts[0]);
                if (savedId.equals(idToDelete)) {
                    vendorFound = true;
                } else {
                    contentBuilder.append(currentLine).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!vendorFound) {
            System.out.println("Vendor ID with " + idToDelete + " was not found.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VENDORFILE))) {
            bw.write(contentBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Deleted the vendor ID: " + idToDelete);
    }
}
