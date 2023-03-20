/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminExecutive;

/**
 *
 * @author Hong Shen
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author 60192
 */
public class ResidentTenantManagement {

    private String id;
    private String name;
    private int age;
    private String unit;
    private String password;
    public static final String FILE_NAME = "residentfile.txt";
    public static final String DEFAULT_RESIDENT_ID = "R00";
    ArrayList<ResidentTenantManagement> resident_tenant = new ArrayList<>();
    //ResidentTenantManagement ui=new ResidentTenantManagement();

    Scanner input = new Scanner(System.in);
    Scanner search = new Scanner(System.in);
    int choice, cont;

    //default constructor
    public ResidentTenantManagement() {
    }

    //constructor with arguments
    public ResidentTenantManagement(String id, String name, int age, String password, String unit) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
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

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ResidentTenant{id=" + id + ", name=" + name + ", age=" + age + ", unit=" + unit + "}";
    }

    public void runResident() {
        Scanner input = new Scanner(System.in);
        int cont;

        do {
            System.out.println("1. Add New Resident/Tenant\n2. Modify Resident/Tenant\n3. Search\n4. View\n5. Delete");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    ResidentTenantManagement add = new ResidentTenantManagement();
                    add.addResident();
                    break;
                case 2:
                    ResidentTenantManagement modify = new ResidentTenantManagement();
                    modify.modifyResident();
                    break;
                case 3:
                    ResidentTenantManagement searchRT = new ResidentTenantManagement();
                    searchRT.searchResident();
                    break;
                case 4:
                    ResidentTenantManagement view = new ResidentTenantManagement();
                    view.viewResident();
                    break;
                case 5:
                    ResidentTenantManagement delete = new ResidentTenantManagement();
                    delete.deleteResident();
                    break;
                default:
                    System.out.println("\nWrong input! Please try again!");
                    continue; // Go back to menu options
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

    public static String getLastResidnetId() {
        String lastId = DEFAULT_RESIDENT_ID;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
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

    public static String generateNewResidentId(String lastId) {
        String numericPart = lastId.substring(1); // extract numeric part of ID
        int newNumericPart = Integer.parseInt(numericPart) + 1; // increment numeric part
        String newId = "R" + String.format("%02d", newNumericPart); // combine prefix and new numeric part
        return newId;
    }

    public void newResident_Tenant() {
        String lastId = getLastResidnetId();
        id = generateNewResidentId(lastId);
        System.out.println("Enter Resident/Tenant name: ");
        name = input.nextLine();
        System.out.println("Enter Resident/Tenant password: ");
        password = input.nextLine();
        System.out.println("Enter Resident/Tenant age: ");
        age = input.nextInt();
        input.nextLine();
        System.out.println("Enter Resident/Tenant unit: ");
        unit = input.nextLine();

    }

    public void showResidentInfo() {
        System.out.println("\nResident Information:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Unit: " + getUnit());
    }

    public void addResident() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    ResidentTenantManagement newRT = new ResidentTenantManagement();
                    newRT.setId(parts[0]);
                    newRT.setName(parts[1]);
                    newRT.setPassword(parts[2]);
                    newRT.setAge(Integer.parseInt(parts[3]));
                    newRT.setUnit(parts[4]);
                    resident_tenant.add(newRT);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'resident_file.txt' was not found.");
        }

        ResidentTenantManagement newRT2 = new ResidentTenantManagement();
        newRT2.newResident_Tenant();
        resident_tenant.add(newRT2);

        try {
            try (FileWriter infile = new FileWriter(FILE_NAME)) {
                for (int i = 0; i < resident_tenant.size(); i++) {
                    infile.append(resident_tenant.get(i).getId() + "|"
                            + resident_tenant.get(i).getName() + "|"
                            + resident_tenant.get(i).getPassword() + "|"
                            + resident_tenant.get(i).getAge() + "|"
                            + resident_tenant.get(i).getUnit() + "\n");
                }
            }
        } catch (IOException ev) {
            System.out.println("An error occured when writing into the file.");
        }
    }

    public void modifyResident() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    ResidentTenantManagement newRT = new ResidentTenantManagement();
                    newRT.setId(parts[0]);
                    newRT.setName(parts[1]);
                    newRT.setPassword(parts[2]);
                    newRT.setAge(Integer.parseInt(parts[3]));
                    newRT.setUnit(parts[4]);
                    resident_tenant.add(newRT);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'resident_file.txt' was not found.");
        }

        System.out.print("Enter the ID of the resident/tenant: ");
        String checkId = search.nextLine();
        boolean found = false;
        for (int a = 0; a < resident_tenant.size(); a++) {
            if (resident_tenant.get(a).getId().equalsIgnoreCase(checkId)) {
                found = true;
                System.out.println("Select the field you want to modify");
                System.out.println("1. Name");
                System.out.println("2. Password");
                System.out.println("3. Age");
                System.out.println("4. Unit");
                System.out.println("5. Cancel");
                System.out.print("Enter choice: ");
                int choice1 = input.nextInt();
                input.nextLine();
                ResidentTenantManagement resident = resident_tenant.get(a);
                switch (choice1) {
                    case 1 -> {
                        System.out.print("Enter new name: ");
                        String new_name = input.nextLine();
                        resident.setName(new_name);
                    }
                    case 2 -> {
                        System.out.println("Enter new password: ");
                        String new_password = input.nextLine();
                        resident.setPassword(new_password);
                    }
                    case 3 -> {
                        System.out.print("Enter new age: ");
                        int new_age = input.nextInt();
                        input.nextLine();
                        resident.setAge(new_age);
                    }
                    case 4 -> {
                        System.out.print("Enter new unit: ");
                        String new_unit = input.nextLine();
                        input.nextLine();
                        resident.setUnit(new_unit);
                    }
                    case 5 -> {
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
        if (!found) {
            System.out.println("Resident/tenant not found.");
        } else {
            try {
                try (FileWriter infile = new FileWriter(FILE_NAME)) {
                    for (int i = 0; i < resident_tenant.size(); i++) {
                        infile.append(resident_tenant.get(i).getId() + "|"
                                + resident_tenant.get(i).getName() + "|"
                                + resident_tenant.get(i).getPassword() + "|"
                                + resident_tenant.get(i).getAge() + "|"
                                + resident_tenant.get(i).getUnit() + "\n");
                    }
                }
            } catch (IOException ev) {
                System.out.println("An error occured when writing into the file.");
            }
        }
    }

    public void searchResident() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    ResidentTenantManagement newRT = new ResidentTenantManagement();
                    newRT.setId(parts[0]);
                    newRT.setName(parts[1]);
                    newRT.setPassword(parts[2]);
                    newRT.setAge(Integer.parseInt(parts[3]));
                    newRT.setUnit(parts[4]);
                    resident_tenant.add(newRT);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'resident_file.txt' was not found.");
        }

        System.out.print("Enter the resident ID: ");
        String savedId = search.nextLine();
        boolean foundResident = false;
        for (int a = 0; a < resident_tenant.size(); a++) {
            if (savedId.equals(resident_tenant.get(a).getId())) {
                if (!foundResident) {
                    System.out.println("Residents with the ID " + savedId + ":");
                    foundResident = true;
                }
                resident_tenant.get(a).showResidentInfo();
            }
        }
        if (!foundResident) {
            System.out.println("No residents with the ID " + savedId + ".");
        }

    }

    public void viewResident() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    ResidentTenantManagement newRT = new ResidentTenantManagement();
                    newRT.setId(parts[0]);
                    newRT.setName(parts[1]);
                    newRT.setPassword(parts[2]);
                    newRT.setAge(Integer.parseInt(parts[3]));
                    newRT.setUnit(parts[4]);
                    resident_tenant.add(newRT);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'resident_file.txt' was not found.");
        }

        for (int x = 0; x < resident_tenant.size(); x++) {
            resident_tenant.get(x).showResidentInfo();
        }
    }

    public void deleteResident() {
        System.out.print("Enter the resident ID to be deleted: ");
        Scanner scan = new Scanner(System.in);
        String idToDelete = scan.nextLine();

        StringBuilder contentBuilder = new StringBuilder();
        boolean idFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] parts = currentLine.split("\\|");
                String savedID = parts[0];
                if (savedID.equals(idToDelete)) {
                    idFound = true;
                } else {
                    contentBuilder.append(currentLine).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!idFound) {
            System.out.println("ID " + idToDelete + " was not found.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write(contentBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Deleted the Resident/Tenant with ID: " + idToDelete);
    }
}
