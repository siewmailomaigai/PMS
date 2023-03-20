/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecurityGuard;

/**
 *
 * @author 60192
 */
import java.io.*;
import java.util.*;

public class RecordIncident {

    private List<String> incidents;

    public RecordIncident() {
        this.incidents = new ArrayList<>();
        loadIncidents();
    }

    private void loadIncidents() {
        // Create incidents file if it doesn't exist
        File file = new File("incident.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Incidents file created successfully.");
            } catch (IOException e) {
                System.out.println("Error creating incidents file: " + e.getMessage());
            }
        }

        // Load incidents from file
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                incidents.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading incidents file: " + e.getMessage());
        }
    }

    private void saveIncidents() {
        try (PrintWriter writer = new PrintWriter(new File("incident.txt"))) {
            for (String incident : incidents) {
                writer.println(incident);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving incidents file: " + e.getMessage());
        }
    }

    public void recordIncident(Scanner scanner) {
        System.out.println("Enter incident details:");
        System.out.print("Incident ID: ");
        String id = scanner.nextLine();
        System.out.print("Date (dd/mm/yyyy): ");
        String date = scanner.nextLine();
        System.out.print("Time (hh:mm:ss): ");
        String time = scanner.nextLine();
        System.out.print("Location: ");
        String location = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        String incident = id + "," + date + "," + time + "," + location + "," + description;
        incidents.add(incident);
        saveIncidents();
        System.out.println("Incident recorded successfully.");
    }

    public void updateIncident(Scanner scanner) {
        System.out.println("Enter the Incident ID to update:");
        String id = scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < incidents.size(); i++) {
            String incident = incidents.get(i);
            String[] parts = incident.split(",");
            if (parts[0].equals(id)) {
                System.out.println("Enter the field to update (date/time/location/description):");
                String fieldToUpdate = scanner.nextLine().toLowerCase();

                String newIncident = "";
                switch (fieldToUpdate) {
                    case "date":
                        System.out.print("New date (dd/mm/yyyy): ");
                        String date = scanner.nextLine();
                        parts[1] = date;
                        newIncident = String.join(",", parts);
                        break;
                    case "time":
                        System.out.print("New time (hh:mm:ss): ");
                        String time = scanner.nextLine();
                        parts[2] = time;
                        newIncident = String.join(",", parts);
                        break;
                    case "location":
                        System.out.print("New location: ");
                        String location = scanner.nextLine();
                        parts[3] = location;
                        newIncident = String.join(",", parts);
                        break;
                    case "description":
                        System.out.print("New description: ");
                        String description = scanner.nextLine();
                        parts[4] = description;
                        newIncident = String.join(",", parts);
                        break;
                    default:
                        System.out.println("Invalid field to update.");
                        return;
                }

                incidents.set(i, newIncident);
                saveIncidents();
                System.out.println("Incident with ID " + id + " updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Incident with ID " + id + " not found.");
        }
    }

    public void viewIncidents() {
        try (Scanner scanner = new Scanner(new File("incident.txt"))) {
            System.out.println("Incident ID\tDate\t\tTime\t\tLocation\tDescription");
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                System.out.println(parts[0] + "\t\t" + parts[1] + "\t" + parts[2] + "\t" + parts[3] + "\t\t" + parts[4]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading incidents file: " + e.getMessage());
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean backToMenu = false;
        do {
            System.out.println("Enter option: ");
            System.out.println("1. Record new incident");
            System.out.println("2. Update existing incident");
            System.out.println("3. View incidents");
            System.out.println("4. Exit");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume new line character

            switch (option) {
                case 1:
                    recordIncident(scanner);
                    break;
                case 2:
                    updateIncident(scanner);
                    break;
                case 3:
                    viewIncidents();
                case 4:
                    System.out.println("Exiting incident recording system.");
                    backToMenu = true;
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        } while (!backToMenu);
    }

}
