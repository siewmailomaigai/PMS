/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resident_Tenant;

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

public class ResidentProfile extends LoginRT{

    private String id;
    private String name;
    private int age;
    private String unit;
    private String password;
    ArrayList<ResidentProfile> resident_tenant = new ArrayList<>();
    //ResidentProfile ui=new ResidentProfile();
    
    Scanner input=new Scanner(System.in);

    //default constructor
    public ResidentProfile(){
        super();
    }
    
    //constructor with arguments
    public ResidentProfile(String id, String name, int age, String unit, String password) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.unit = unit;
        this.password=password;     
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
           
    
    public void runProfile(){
        ResidentProfile run=new ResidentProfile();                 
        System.out.println("""
                           1.View profile
                           2.Modify profile
                           3.Cancel
                           Select:
                           """);     
        int choice=input.nextInt();
        switch(choice){
            case 1->{
                run.searchResident();
            }
            case 2->{
                run.modifyResident();
            }
            case 3->{
                return;
            }
            default ->
                System.out.println("Invalid input!");
        }                        
    }
    
    public void showResidentInfo() {
        System.out.println("Resident Information:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Password: "+getPassword());
        System.out.println("Unit: " + getUnit());
    }
    
    public void searchResident(){
        try {
            try (Scanner scan = new Scanner(new File(filename))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    ResidentProfile newRT = new ResidentProfile();
                    if (parts.length >= 5) {
                        newRT.setId(parts[0]);
                        newRT.setName(parts[1]);
                        newRT.setPassword(parts[2]);
                        newRT.setAge(Integer.parseInt(parts[3]));
                        newRT.setUnit(parts[4]);
                        resident_tenant.add(newRT);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'resident_file.txt' was not found.");
        }

        for (int a = 0; a < resident_tenant.size(); a++) {
            if (checkId.equalsIgnoreCase(resident_tenant.get(a).getId())) {
                resident_tenant.get(a).showResidentInfo();
                
            } 
        } 
    }
    
    public void modifyResident(){
        try {
            try (Scanner scan = new Scanner(new File(filename))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    ResidentProfile newRT = new ResidentProfile();
                    newRT.setId(parts[0]);
                    newRT.setName(parts[1]);
                    newRT.setPassword(parts[2]);
                    newRT.setAge(Integer.parseInt(parts[3]));
                    newRT.setUnit(parts[4]);
                    resident_tenant.add(newRT);
                }
            }
        }   
        catch (FileNotFoundException e) {
                System.out.println("The file 'resident_file.txt' was not found.");
        }

        for (int a = 0; a < resident_tenant.size(); a++) {
            if (resident_tenant.get(a).getId().equalsIgnoreCase(checkId)) {
                System.out.println("Select the field you want to modify");
                System.out.println("1. Name");
                System.out.println("2. Password");
                System.out.println("3. Age");
                System.out.println("4. Cancel");
                System.out.print("Enter choice: ");
                int choice1 = input.nextInt();
                input.nextLine();
                ResidentProfile resident = resident_tenant.get(a);
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
                resident_tenant.set(a, resident);
                System.out.println("Modification successful!");
                break;
            }
        }
            try {
                try (FileWriter infile = new FileWriter(filename)) {
                    for (int i = 0; i < resident_tenant.size(); i++) {
                        infile.append(
                            resident_tenant.get(i).getId() + "|"
                            + resident_tenant.get(i).getName() + "|"
                            + resident_tenant.get(i).getPassword() + "|"
                            + resident_tenant.get(i).getAge() + "|"
                            + resident_tenant.get(i).getUnit() + "\n");
                    }
                }
            }   catch (IOException ev) {
                    System.out.println("An error occured when writing into the file.");
                }
    }
    
    
}
