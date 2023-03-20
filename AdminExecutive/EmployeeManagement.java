package AdminExecutive;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagement {

    private String eID;
    private String name;
    private String contactInfo;
    private String role;
    private String password;
    private String workingStatus;
    Scanner input = new Scanner(System.in);
    Scanner search3 = new Scanner(System.in);
    int choice, cont;
    public static final String FILE_NAME4 = "employee.txt";
    public static final String DEFAULT_SECURITYGUARD_ID = "SG00";
    public static final String DEFAULT_TECHNICIAN_ID = "TC00";
    public static final String DEFAULT_CLEANER_ID = "CL00";
    public static final String DEFAULT_OTHER_ID = "EM00";
    ArrayList<EmployeeManagement> employee = new ArrayList<>();
    //EmployeeManagement ui=new EmployeeManagement();

    public EmployeeManagement() {
    }

    public EmployeeManagement(String eID, String name, String contactInfo, String role, String password, String workingStatus) {
        this.eID = eID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.role = role;
        this.password = password;
        this.workingStatus = workingStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String geteID() {
        return eID;
    }

    public void seteID(String eID) {
        this.eID = eID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    public void runEmployee() {
        Scanner input = new Scanner(System.in);
        int cont;
        do {
            System.out.println("1.Add New Employee\n2.View Employee\n3.Update Employee");
            choice = input.nextInt();

            //add or new resident/tenant details
            switch (choice) {
                case 1 -> {
                    EmployeeManagement add = new EmployeeManagement();
                    add.addEmployee();

                }
                case 2 -> {
                    EmployeeManagement view = new EmployeeManagement();
                    view.viewEmployee();

                }
                case 3 -> {
                    EmployeeManagement update = new EmployeeManagement();
                    update.updateEmployee();

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

    public static String getLastSGId() {
        String lastId = DEFAULT_SECURITYGUARD_ID;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME4))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 1 && parts[0].startsWith("SG")) {
                    lastId = parts[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public static String generateNewSGId(String lastId) {
        String numericPart = lastId.substring(2); // extract numeric part of ID
        int newNumericPart = Integer.parseInt(numericPart) + 1; // increment numeric part
        String newId = "SG" + String.format("%02d", newNumericPart); // combine prefix and new numeric part
        return newId;
    }

    public static String getLastTCId() {
        String lastId = DEFAULT_TECHNICIAN_ID;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME4))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 1 && parts[0].startsWith("TC")) {
                    lastId = parts[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public static String generateNewTCId(String lastId) {
        String numericPart = lastId.substring(2); // extract numeric part of ID
        int newNumericPart = Integer.parseInt(numericPart) + 1; // increment numeric part
        String newId = "TC" + String.format("%02d", newNumericPart); // combine prefix and new numeric part
        return newId;
    }

    public static String getLastCLId() {
        String lastId = DEFAULT_SECURITYGUARD_ID;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME4))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 1 && parts[0].startsWith("CL")) {
                    lastId = parts[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public static String generateNewCLId(String lastId) {
        String numericPart = lastId.substring(2); // extract numeric part of ID
        int newNumericPart = Integer.parseInt(numericPart) + 1; // increment numeric part
        String newId = "CL" + String.format("%02d", newNumericPart); // combine prefix and new numeric part
        return newId;
    }

    public static String getLastOtherId() {
        String lastId = DEFAULT_OTHER_ID;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME4))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 1 && parts[0].startsWith("EM")) {
                    lastId = parts[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public static String generateNewOtherId(String lastId) {
        String numericPart = lastId.substring(2); // extract numeric part of ID
        int newNumericPart = Integer.parseInt(numericPart) + 1; // increment numeric part
        String newId = "EM" + String.format("%02d", newNumericPart); // combine prefix and new numeric part
        return newId;
    }

    public void addEmployee() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME4))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    EmployeeManagement newE = new EmployeeManagement();
                    if (parts.length >= 6) {
                        newE.seteID(parts[0]);
                        newE.setName(parts[1]);
                        newE.setContactInfo(parts[2]);
                        newE.setRole(parts[3]);
                        newE.setPassword(parts[4]);
                        newE.setWorkingStatus(parts[5]);
                    }
                    employee.add(newE);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'employee.txt' was not found.");
        }

        System.out.println("Enter employee name: ");
        name = input.nextLine();
        System.out.println("Enter contact information: ");
        contactInfo = input.nextLine();
        System.out.println("Enter employee role (security guard, technician, cleaner, other): ");
        role = input.nextLine();
        if (role.equalsIgnoreCase("security guard")) {
            String lastId = getLastSGId();
            eID = generateNewSGId(lastId);
            System.out.println("Enter password: ");
            password = input.nextLine();
        } else if (role.equalsIgnoreCase("technician")) {
            String lastId = getLastTCId();
            eID = generateNewTCId(lastId);
            password = " ";
        } else if (role.equalsIgnoreCase("cleaner")) {
            String lastId = getLastCLId();
            eID = generateNewCLId(lastId);
            password = " ";
        } else if (role.equalsIgnoreCase("other")) {
            String lastId = getLastOtherId();
            eID = generateNewOtherId(lastId);
            password = " ";
        } else {
            System.out.println("Error! Unknown role");
            return;
        }
        workingStatus = "Working";

        EmployeeManagement newEmployee = new EmployeeManagement(eID, name, contactInfo, role, password, workingStatus);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME4, true));
            writer.write(
                    newEmployee.geteID() + "|"
                    + newEmployee.getName() + "|"
                    + newEmployee.getContactInfo() + "|"
                    + newEmployee.getRole() + "|"
                    + newEmployee.getPassword() + "|"
                    + newEmployee.getWorkingStatus() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file.");
        }
        /*EmployeeManagement newE2 = new EmployeeManagement();
        newE2.newEmployee();
        employee.add(newE2);*/

        //newRT.addToFile();
        /*try {
            try (FileWriter infile = new FileWriter(FILE_NAME4)) {
                for (int i = 0; i < employee.size(); i++) {
                    infile.append(employee.get(i).getName() + " "
                            + employee.get(i).getContactInfo() + " "
                            + employee.get(i).getRole() + " "
                            + employee.get(i).getJobTitle() + " "
                            + employee.get(i).getDateOfHire() + "\n");
                }
            }
        } catch (IOException ev) {
            System.out.println("An error occured when writing into the file.");
        }*/
    }

    public void showEmployeeInfo() {
        System.out.println("\nEmployee Information:");
        System.out.println("Employee ID: " + geteID());
        System.out.println("Employee Name: " + getName());
        System.out.println("Contact information: " + getContactInfo());
        System.out.println("Employee role: " + getRole());
        System.out.println("Employee working status: " + getWorkingStatus());
    }

    public void viewEmployee() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME4))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    EmployeeManagement newE = new EmployeeManagement();
                    if (parts.length >= 6) {
                        newE.seteID(parts[0]);
                        newE.setName(parts[1]);
                        newE.setContactInfo(parts[2]);
                        newE.setRole(parts[3]);
                        newE.setPassword(parts[4]);
                        newE.setWorkingStatus(parts[5]);
                    }
                    employee.add(newE);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'employee.txt' was not found.");
        }

        for (int x = 0; x < employee.size(); x++) {
            employee.get(x).showEmployeeInfo();
        }
    }

    public void updateEmployee() {
        try {
            try (Scanner scan = new Scanner(new File(FILE_NAME4))) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");
                    EmployeeManagement newE = new EmployeeManagement();
                    if (parts.length >= 6) {
                        newE.seteID(parts[0]);
                        newE.setName(parts[1]);
                        newE.setContactInfo(parts[2]);
                        newE.setRole(parts[3]);
                        newE.setPassword(parts[4]);
                        newE.setWorkingStatus(parts[5]);
                    }
                    employee.add(newE);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file 'employee.txt' was not found.");
        }

        System.out.print("Enter the employee ID: ");
        String searchID = search3.nextLine();
        boolean found = false;
        for (int a = 0; a < employee.size(); a++) {
            if (employee.get(a).geteID().equalsIgnoreCase(searchID)) {
                found = true;
                System.out.println("Select the field you want to modify");
                System.out.println("1. Employee Name");
                System.out.println("2. Contact Information");
                System.out.println("3. Role");
                System.out.println("4. Password");
                System.out.println("5. Working Status");
                System.out.println("6. Cancel");
                System.out.print("Enter choice: ");
                int choice1 = input.nextInt();
                input.nextLine();
                EmployeeManagement e = employee.get(a);
                switch (choice1) {
                    case 1 -> {
                        System.out.println("Enter new employee name: ");
                        String newName = input.nextLine();
                        e.setName(newName);
                    }
                    case 2 -> {

                        System.out.print("Enter new Contact Info: ");
                        String newContactInfo = input.nextLine();
                        e.setContactInfo(newContactInfo);
                    }
                    case 3 -> {
                        System.out.print("Enter new role: ");
                        String newRole = input.nextLine();
                        e.setRole(newRole);
                    }
                    case 4 -> {
                        System.out.print("Enter new password: ");
                        String newPassword = input.nextLine();
                        e.setPassword(newPassword);
                    }

                    case 5 -> {
                        System.out.print("Enter new working status: ");
                        String newWorkingStatus = input.nextLine();
                        e.setWorkingStatus(newWorkingStatus);
                    }

                    default ->
                        System.out.println("Invalid input.");
                }

                employee.set(a, e);
                System.out.println("Update successful!");
                break;
            }
        }
        if (!found) {
            System.out.println("Employee not found.");
        } else {
            try {
                try (FileWriter infile = new FileWriter(FILE_NAME4)) {
                    for (int i = 0; i < employee.size(); i++) {
                        infile.append(
                                employee.get(i).geteID() + "|"
                                + employee.get(i).getName() + "|"
                                + employee.get(i).getContactInfo() + "|"
                                + employee.get(i).getRole() + "|"
                                + employee.get(i).getPassword() + "|"
                                + employee.get(i).getWorkingStatus() + "\n");
                    }
                }
            } catch (IOException ev) {
                System.out.println("An error occured when writing into the file.");
            }
        }

    }
}
