/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BuildingExecutive;

/**
 *
 * @author 60192
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatrollingandCheckpoint {

    public PatrollingandCheckpoint() {

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 3) {
            System.out.println("Select an option:");
            System.out.println("1. Patrolling Schedule");
            System.out.println("2. Checkpoint");
            System.out.println("3. Exit");
            System.out.print("Option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (option) {
                case 1:
                    patrollingScheduleOptions(scanner);
                    break;
                case 2:
                    checkpointOptions(scanner);
                    break;
                case 3:
                    System.out.println("Exiting Patrolling and Checkpoint management system...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void patrollingScheduleOptions(Scanner scanner) {
        int option = 0;
        while (option != 4) {
            System.out.println("Select an option:");
            System.out.println("1. Add Patrolling Schedule");
            System.out.println("2. View Patrolling Schedules");
            System.out.println("3. Modify Patrolling Schedule");
            System.out.println("4. Delete Patrolling Schedule");
            System.out.println("5. Back");
            System.out.print("Option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (option) {
                case 1:
                    addSchedule(scanner);
                    break;
                case 2:
                    viewSchedules();
                    break;
                case 3:
                    modifySchedule(scanner);
                    break;
                case 4:
                    deleteSchedule(scanner);
                    break;
                case 5:
                    return; // go back to previous menu
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void checkpointOptions(Scanner scanner) {
        int option = 0;
        while (option != 4) {
            System.out.println("Select an option:");
            System.out.println("1. Add Checkpoint");
            System.out.println("2. View Checkpoints");
            System.out.println("3. Modify Checkpoint");
            System.out.println("4. Delete Checkpoint");
            System.out.println("5. Back");
            System.out.print("Option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (option) {
                case 1:
                    addCheckpoint(scanner);
                    break;
                case 2:
                    viewCheckpoints();
                    break;
                case 3:
                    modifyCheckpoint(scanner);
                    break;
                case 4:
                    deleteCheckpoint(scanner);
                    break;
                case 5:
                    return; // go back to previous menu
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void addSchedule(Scanner scanner) {
        System.out.println("Enter Patrolling Schedule details:");
        System.out.print("Schedule ID: ");
        String id = scanner.nextLine();
        System.out.print("Security Guard Name: ");
        String guardName = scanner.nextLine();
        System.out.print("Start Time (hh:mm:ss): ");
        String startTime = scanner.nextLine();
        System.out.print("End Time (hh:mm:ss): ");
        String endTime = scanner.nextLine();

        try (FileWriter writer = new FileWriter("schedules.txt", true)) {
            writer.write(id + "," + guardName + "," + startTime + "," + endTime + "\n");
            System.out.println("Patrolling Schedule added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to schedules file: " + e.getMessage());
        }
    }

    private void addCheckpoint(Scanner scanner) {
        System.out.println("Enter Checkpoint details:");
        System.out.print("Checkpoint ID: ");
        String id = scanner.nextLine();
        System.out.print("Location: ");
        String location = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        try (FileWriter writer = new FileWriter("checkpoints.txt", true)) {
            writer.write(id + "," + location + "," + description + "\n");
            System.out.println("Checkpoint added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to checkpoints file: " + e.getMessage());
        }
    }

    private void modifySchedule(Scanner scanner) {
        System.out.println("Enter the Schedule ID of the schedule you want to modify: ");
        String id = scanner.nextLine();

        boolean found = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("schedules.txt"));
            String line;
            StringBuilder fileContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] scheduleDetails = line.split(",");
                if (scheduleDetails[0].equals(id)) {
                    found = true;
                    System.out.println("Enter the field you want to modify (1. Security Guard Name, 2. Start Time, 3. End Time):");
                    int fieldChoice = Integer.parseInt(scanner.nextLine());

                    switch (fieldChoice) {
                        case 1:
                            System.out.print("Enter new Security Guard Name: ");
                            String guardName = scanner.nextLine();
                            scheduleDetails[1] = guardName;
                            break;
                        case 2:
                            System.out.print("Enter new Start Time (hh:mm:ss): ");
                            String startTime = scanner.nextLine();
                            scheduleDetails[2] = startTime;
                            break;
                        case 3:
                            System.out.print("Enter new End Time (hh:mm:ss): ");
                            String endTime = scanner.nextLine();
                            scheduleDetails[3] = endTime;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }

                    String newLine = String.join(",", scheduleDetails);
                    fileContent.append(newLine).append("\n");
                    System.out.println("Patrolling Schedule modified successfully.");
                } else {
                    fileContent.append(line).append("\n");
                }
            }
            reader.close();

            if (!found) {
                System.out.println("Patrolling Schedule with ID " + id + " not found.");
            } else {
                FileWriter writer = new FileWriter("schedules.txt");
                writer.write(fileContent.toString());
                writer.close();
            }

        } catch (IOException e) {
            System.out.println("Error reading or writing to file: " + e.getMessage());
        }
    }

    private void modifyCheckpoint(Scanner scanner) {
        System.out.println("Enter the Checkpoint ID of the checkpoint you want to modify: ");
        String id = scanner.nextLine();

        boolean found = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("checkpoints.txt"));
            String line;
            StringBuilder fileContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] checkpointDetails = line.split(",");
                if (checkpointDetails[0].equals(id)) {
                    found = true;
                    System.out.print("Enter new Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter new Description: ");
                    String description = scanner.nextLine();

                    checkpointDetails[1] = location;
                    checkpointDetails[2] = description;

                    String newLine = String.join(",", checkpointDetails);
                    fileContent.append(newLine).append("\n");
                    System.out.println("Checkpoint modified successfully.");
                } else {
                    fileContent.append(line).append("\n");
                }
            }
            reader.close();

            if (!found) {
                System.out.println("Checkpoint with ID " + id + " not found.");
            } else {
                FileWriter writer = new FileWriter("checkpoints.txt");
                writer.write(fileContent.toString());
                writer.close();
            }

        } catch (IOException e) {
            System.out.println("Error reading or writing to file: " + e.getMessage());
        }
    }

    private void deleteSchedule(Scanner scanner) {
        System.out.println("Enter the Schedule ID of the schedule you want to delete: ");
        String id = scanner.nextLine();

        boolean found = false;

        try (Scanner fileScanner = new Scanner(new File("schedules.txt")); PrintWriter writer = new PrintWriter(new FileWriter("temp.txt"))) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(id)) {
                    System.out.println("Patrolling Schedule with ID " + id + " deleted successfully.");
                    found = true;
                } else {
                    writer.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading schedules file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing temp file: " + e.getMessage());
        }

        if (!found) {
            System.out.println("Patrolling Schedule with ID " + id + " not found.");
        } else {
            File oldFile = new File("schedules.txt");
            File tempFile = new File("temp.txt");
            oldFile.delete();
            tempFile.renameTo(oldFile);
        }
    }

    private void deleteCheckpoint(Scanner scanner) {
        System.out.println("Enter the Checkpoint ID of the checkpoint you want to delete: ");
        String id = scanner.nextLine();

        boolean found = false;

        try (Scanner fileScanner = new Scanner(new File("checkpoints.txt")); PrintWriter writer = new PrintWriter(new FileWriter("temp.txt"))) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(id)) {
                    System.out.println("Checkpoint with ID " + id + " deleted successfully.");
                    found = true;
                } else {
                    writer.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading checkpoints file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing temp file: " + e.getMessage());
        }

        if (!found) {
            System.out.println("Checkpoint with ID " + id + " not found.");
        } else {
            File oldFile = new File("checkpoints.txt");
            File tempFile = new File("temp.txt");
            oldFile.delete();
            tempFile.renameTo(oldFile);
        }
    }

    private void viewCheckpoints() {
        System.out.println("Checkpoints:");

        try (Scanner fileScanner = new Scanner(new File("checkpoints.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                System.out.println("ID: " + parts[0]);
                System.out.println("Location: " + parts[1]);
                System.out.println("Description: " + parts[2]);
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading checkpoints file: " + e.getMessage());
        }
    }

    private void viewSchedules() {
        System.out.println("Patrolling Schedules:");

        try (Scanner fileScanner = new Scanner(new File("schedules.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                System.out.println("ID: " + parts[0]);
                System.out.println("Security Guard Name: " + parts[1]);
                System.out.println("Start Time: " + parts[2]);
                System.out.println("End Time: " + parts[3]);
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading schedules file: " + e.getMessage());
        }
    }

}
