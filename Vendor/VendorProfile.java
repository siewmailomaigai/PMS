/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vendor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Hong Shen
 */
public class VendorProfile extends LoginVendor{
    private String id;
    private String name;
    private int age;
    private String password;
    private String shopId;
    ArrayList<VendorProfile> vendor = new ArrayList<>();
    
    Scanner input=new Scanner(System.in);
    
    public VendorProfile(){
        
    }
    
    public VendorProfile(String id, String name, int age, String password, String shopId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.shopId = shopId;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    
    public void runProfile(){
        VendorProfile run=new VendorProfile();                 
        System.out.println("""
                           1.View profile
                           2.Modify profile
                           3.Cancel
                           Select:
                           """);     
        int choice=input.nextInt();
        switch(choice){
            case 1->{
                run.searchVendor();
            }
            case 2->{
                run.modifyVendor();
            }
            case 3->{
                return;
            }
            default ->
                System.out.println("Invalid input!");
        }        
    }
    
    
    public void showVendorInfo() {
        System.out.println("Vendor Information:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Password: "+getPassword());
        System.out.println("Shop ID: " + getShopId());
    }
    
    
    public void searchVendor(){
        try {
            try (Scanner scan = new Scanner(new File(filename))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    VendorProfile vp = new VendorProfile();
                    if (parts.length >= 5) {
                        vp.setId(parts[0]);
                        vp.setName(parts[1]);
                        vp.setPassword(parts[2]);
                        vp.setAge(Integer.parseInt(parts[3]));
                        vp.setShopId(parts[4]);
                        vendor.add(vp);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'resident_file.txt' was not found.");
        }

        for (int a = 0; a < vendor.size(); a++) {
            if (checkId.equalsIgnoreCase(vendor.get(a).getId())) {
                vendor.get(a).showVendorInfo();
            } 
        }
    }
    
    
    public void modifyVendor(){
        try {
            try (Scanner scan = new Scanner(new File(filename))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    VendorProfile newRT = new VendorProfile();
                    newRT.setId(parts[0]);
                    newRT.setName(parts[1]);
                    newRT.setPassword(parts[2]);
                    newRT.setAge(Integer.parseInt(parts[3]));
                    newRT.setShopId(parts[4]);
                    vendor.add(newRT);
                }
            }
        }   
        catch (FileNotFoundException e) {
                System.out.println("The file 'resident_file.txt' was not found.");
        }

        for (int a = 0; a < vendor.size(); a++) {
            if (vendor.get(a).getId().equalsIgnoreCase(checkId)) {
                System.out.println("Select the field you want to modify");
                System.out.println("1. Name");
                System.out.println("2. Password");
                System.out.println("3. Age");
                System.out.println("4. Cancel");
                System.out.print("Enter choice: ");
                int choice1 = input.nextInt();
                input.nextLine();
                VendorProfile resident = vendor.get(a);
                switch (choice1) {
                    case 1 -> {
                        System.out.print("Enter new name: ");
                        String new_name = input.nextLine();
                        resident.setName(new_name);
                    }
                    case 2->{
                        System.out.println("Enter new password: ");
                        String new_password=input.nextLine();
                        resident.setPassword(new_password);
                    }
                    case 3 -> {
                        System.out.print("Enter new age: ");
                        int new_age = input.nextInt();
                        input.nextLine();
                        resident.setAge(new_age);
                    }
                    case 4 -> {
                        System.out.println("Modification canceled.");
                        return;
                    }
                    default ->
                        System.out.println("Invalid input.");
                }
                vendor.set(a, resident);
                System.out.println("Modification successful!");
                break;
            }
        }
            try {
                try (FileWriter infile = new FileWriter(filename)) {
                    for (int i = 0; i < vendor.size(); i++) {
                        infile.append(
                            vendor.get(i).getId() + "|"
                            + vendor.get(i).getName() + "|"
                            + vendor.get(i).getPassword() + "|"
                            + vendor.get(i).getAge() + "|"
                            + vendor.get(i).getShopId()+ "\n");
                    }
                }
            }
            catch (IOException ev) {
                    System.out.println("An error occured when writing into the file.");
            }
    }
    
}
