/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BuildingManager;

/**
 *
 * @author 60192
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UserManagement {

    public class User {

        private String id;
        private String password;
        private String name;
        private String email;

        public User(String id, String password, String name, String email) {
            this.id = id;
            this.password = password;
            this.name = name;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{"
                    + "id='" + id + '\''
                    + ", password='" + password + '\''
                    + ", name='" + name + '\''
                    + ", email='" + email + '\''
                    + '}';
        }
    }

    private ArrayList<User> users;

    public UserManagement() {
        users = new ArrayList<>();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add user");
            System.out.println("2. Modify user");
            System.out.println("3. Search user");
            System.out.println("4. Delete user");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the remaining newline character

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    modifyUser();
                    break;
                case 3:
                    searchUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }

            System.out.println();
        }
    }

    private void addUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a user type:");
        System.out.println("1. Account Executive");
        System.out.println("2. Admin Executive");
        System.out.println("3. Building Executive");

        int userType = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline character

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        switch (userType) {
            case 1:
                String accountExecutiveString = String.format("%s|%s|%s|%s", id, name, password, email);
                try {
                    File file = new File("accountE.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                    writer.write(accountExecutiveString);
                    writer.newLine();
                    writer.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to file.");
                    e.printStackTrace();
                }
                break;
            case 2:
                String adminExecutiveString = String.format("%s|%s|%s|%s", id, name, password, email);
                try {
                    File file = new File("adminE.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                    writer.write(adminExecutiveString);
                    writer.newLine();
                    writer.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to file.");
                    e.printStackTrace();
                }
                break;
            case 3:
                String buildingExecutiveString = String.format("%s|%s|%s|%s", id, name, password, email);
                try {
                    File file = new File("buildingE.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                    writer.write(buildingExecutiveString);
                    writer.newLine();
                    writer.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to file.");
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid user type!");
                return;
        }

        System.out.println("User added successfully.");
    }

    private void modifyUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a user type:");
        System.out.println("1. Account Executive");
        System.out.println("2. Admin");
        System.out.println("3. Buildings Executive");
        int userType = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline character

        System.out.print("Enter ID of the user to modify: ");
        String id = scanner.nextLine();
        User user = findUserById(id, userType);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("Select a field to modify:");
        System.out.println("1. Password");
        System.out.println("2. Name");
        System.out.println("3. Email");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline character
        switch (choice) {
            case 1:
                System.out.print("Enter new password: ");
                String password = scanner.nextLine();
                user.setPassword(password);
                break;
            case 2:
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                user.setName(name);
                break;
            case 3:
                System.out.print("Enter new email: ");
                String email = scanner.nextLine();
                user.setEmail(email);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        // Update the corresponding line in the text file
        String filename;
        switch (userType) {
            case 1:
                filename = "accountE.txt";
                break;
            case 2:
                filename = "adminE.txt";
                break;
            case 3:
                filename = "buildingE.txt";
                break;
            default:
                System.out.println("Invalid user type!");
                return;
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (int i = 0; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(",");
                if (fields[0].equals(id)) {
                    String updatedLine = String.format("%s|%s|%s|%s", id, user.getName(), user.getPassword(), user.getEmail());
                    lines.set(i, updatedLine);
                    break;
                }
            }
            Files.write(Paths.get(filename), lines);
            System.out.println("User modified successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while modifying user.");
            e.printStackTrace();
        }
    }

    private void searchUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a user type:");
        System.out.println("1. Account Executive");
        System.out.println("2. Admin");
        System.out.println("3. Buildings Executive");
        int userType = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline character
        System.out.print("Enter ID of the user to search: ");
        String id = scanner.nextLine();
        User user = findUserById(id, userType);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        System.out.println(user);
    }

    private User findUserById(String id, int userType) {
        String filename;
        switch (userType) {
            case 1:
                filename = "accountE.txt";
                break;
            case 2:
                filename = "adminE.txt";
                break;
            case 3:
                filename = "buildingE.txt";
                break;
            default:
                System.out.println("Invalid user type!");
                return null;
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] fields = line.split("\\|");
                if (fields[0].equals(id)) {
                    User user = new User(fields[0], fields[1], fields[2], fields[3]);
                    return user;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while finding user.");
            e.printStackTrace();
        }
        return null;
    }

    private void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a user type:");
        System.out.println("1. Account Executive");
        System.out.println("2. Admin");
        System.out.println("3. Buildings Executive");
        int userType = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline character

        System.out.print("Enter ID of the user to delete: ");
        String id = scanner.nextLine();

        String filename;
        switch (userType) {
            case 1:
                filename = "accountE.txt";
                break;
            case 2:
                filename = "adminE.txt";
                break;
            case 3:
                filename = "buildingE.txt";
                break;
            default:
                System.out.println("Invalid user type!");
                return;
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (int i = 0; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(",");
                if (fields[0].equals(id)) {
                    lines.remove(i);
                    break;
                }
            }
            Files.write(Paths.get(filename), lines);
        } catch (IOException e) {
            System.out.println("An error occurred while deleting user.");
            e.printStackTrace();
        }

        System.out.println("User deleted successfully.");
    }

}
