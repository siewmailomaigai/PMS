/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BuildingManager;

/**
 *
 * @author 60192
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamStructureManagement {

    private List<Team> teams = new ArrayList<>();
    private String filename = "teams.txt";

    // Method to prompt user for input and perform action based on selection
    public void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("Please select an option:");
            System.out.println("1. Create a new team");
            System.out.println("2. View all teams");
            System.out.println("3. Update an existing team");
            System.out.println("4. Exit");
            option = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
            switch (option) {
                case 1:
                    createTeam();
                    break;
                case 2:
                    viewTeams();
                    break;
                case 3:
                    updateTeam();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option selected. Please try again.");
                    break;
            }
        } while (option != 4);
    }

    public void createTeam() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter team name: ");
        String name = scanner.nextLine();
        System.out.print("Enter team description: ");
        String description = scanner.nextLine();
        Team team = new Team(name, description);
        teams.add(team);
        saveTeams();
        System.out.println("Team created successfully.");
    }

    public void viewTeams() {
        for (Team team : teams) {
            System.out.println("Name: " + team.getName());
            System.out.println("Description: " + team.getDescription());
            System.out.println();
        }
    }

    public void updateTeam() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter team name to update: ");
        String name = scanner.nextLine();
        boolean teamFound = false;
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getName().equals(name)) {
                System.out.print("Enter new team description: ");
                String description = scanner.nextLine();
                teams.get(i).setDescription(description);
                saveTeams();
                System.out.println("Team updated successfully.");
                teamFound = true;
                break;
            }
        }
        if (!teamFound) {
            System.out.println("Team not found with name: " + name);
        }
    }

    private void saveTeams() {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Team team : teams) {
                writer.write(team.getName() + "," + team.getDescription() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving teams.");
            e.printStackTrace();
        }
    }
}

class Team {

    private String name;
    private String description;

    public Team(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
