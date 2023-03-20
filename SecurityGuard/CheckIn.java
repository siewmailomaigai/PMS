/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecurityGuard;

/**
 *
 * @author 60192
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckIn {
    public CheckIn(){
        
    }
    public void CheckIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Checkpoint ID: ");
        String id = scanner.nextLine();

        boolean found = false;

        try (Scanner fileScanner = new Scanner(new File("checkpoints.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(id)) {
                    System.out.println("Checkpoint:");
                    System.out.println("ID: " + parts[0]);
                    System.out.println("Location: " + parts[1]);
                    System.out.println("Description: " + parts[2]);
                    System.out.println("Now Checked In");
                    found = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading checkpoints file: " + e.getMessage());
        }

        if (!found) {
            System.out.println("Checkpoint with ID " + id + " not found.");
        }
    }

}
