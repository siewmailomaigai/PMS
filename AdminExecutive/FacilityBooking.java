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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 60192
 */
public class FacilityBooking extends FacilityManagement {

    private String bookId;
    private String id;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    public static final String FILE_NAME3 = "facilitybooking.txt";
    public static final String DEFAULT_BOOKING_ID = "FB00";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Format for date input
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm"); // Format for time input
    ArrayList<FacilityBooking> facilityB = new ArrayList<>();
    //FacilityBooking ui=new FacilityBooking();

    public FacilityBooking() {
        super();
    }

    public FacilityBooking(String bookId, String id, String facilityName, LocalDate bookingDate, LocalTime startTime, LocalTime endTime) {
        super();
        this.bookId = bookId;
        this.id = id;
        this.facilityName = facilityName;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void runFacilityBooking() {
        Scanner input = new Scanner(System.in);
        int cont;
        do {
            System.out.println("1.New booking\n2.View bookings\n3.Update booking\n4.Delete booking");
            choice = input.nextInt();

            //add or new resident/tenant details
            switch (choice) {
                case 1 -> {
                    FacilityBooking add = new FacilityBooking();
                    add.addFacilityB();
                }
                case 2 -> {
                    FacilityBooking view = new FacilityBooking();
                    view.viewFacilityB();
                }
                case 3 -> {
                    FacilityBooking update = new FacilityBooking();
                    update.updateFacilityB();
                }
                case 4 -> {
                    FacilityBooking delete = new FacilityBooking();
                    delete.deleteFacilityB();
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

    public static String getLastBookingId() {
        String lastId = DEFAULT_BOOKING_ID;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME3))) {
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

    public static String generateNewBookingId(String lastId) {
        String numericPart = lastId.substring(2); // extract numeric part of ID
        int newNumericPart = Integer.parseInt(numericPart) + 1; // increment numeric part
        String newId = "FB" + String.format("%02d", newNumericPart); // combine prefix and new numeric part
        return newId;
    }

    public static boolean checkOverlap(ArrayList<FacilityBooking> bookings, String facilityName, LocalDate bookingDate, LocalTime startTime, LocalTime endTime) {
        for (FacilityBooking booking : bookings) {
            if (booking.getFacilityName().equalsIgnoreCase(facilityName) && booking.getBookingDate().equals(bookingDate)
                    && ((booking.getStartTime().isBefore(startTime) && booking.getEndTime().isAfter(startTime))
                    || (booking.getStartTime().isBefore(endTime) && booking.getEndTime().isAfter(endTime)))) {
                return true; // Booking overlaps with existing booking
            }
        }
        return false; // No overlap found
    }

    public void addFacilityB() {
        try {
            Scanner scan = new Scanner(new File(FILE_NAME3));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split("\\|");
                FacilityBooking newFacb = new FacilityBooking();
                if (parts.length >= 5) {
                    newFacb.setBookId(parts[0]);
                    newFacb.setId(parts[1]);
                    newFacb.setFacilityName(parts[2]);
                    newFacb.setBookingDate(LocalDate.parse(parts[3], DATE_FORMATTER));
                    newFacb.setStartTime(LocalTime.parse(parts[4], TIME_FORMATTER));
                    newFacb.setEndTime(LocalTime.parse(parts[5], TIME_FORMATTER));
                }
                facilityB.add(newFacb);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file 'facilitybooking.txt' was not found.");
        }

        String lastId = getLastBookingId();
        bookId = generateNewBookingId(lastId);
        System.out.println("Resident/Tenant ID : ");
        id = input.nextLine();
        System.out.println("Enter facility name(gym, basketball court, pool, snooker): ");
        facilityName = input.nextLine();
        System.out.println("Enter booking date (yyyy-MM-dd): ");
        bookingDate = LocalDate.parse(input.nextLine(), DATE_FORMATTER);
        System.out.println("Enter start time (HH:mm): ");
        startTime = LocalTime.parse(input.nextLine(), TIME_FORMATTER);
        System.out.println("Enter end time (HH:mm): ");
        endTime = LocalTime.parse(input.nextLine(), TIME_FORMATTER);

        if (checkOverlap(facilityB, facilityName, bookingDate, startTime, endTime)) {
            System.out.println("Booking overlap detected. Please choose a different date and time.");
            return;
        }

        FacilityBooking newFacb2 = new FacilityBooking(bookId, id, facilityName, bookingDate, startTime, endTime);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME3, true));
            writer.write(
                    newFacb2.getBookId() + "|"
                    + newFacb2.getId() + "|"
                    + newFacb2.getFacilityName() + "|"
                    + newFacb2.getBookingDate() + "|"
                    + newFacb2.getStartTime() + "|"
                    + newFacb2.getEndTime() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file.");
        }
    }

    public void showBookingInfo() {
        System.out.println("\nBooking Information:");
        System.out.println("Booking ID: " + getBookId());
        System.out.println("Booked By: " + getId());
        System.out.println("Facility Name: " + getFacilityName());
        System.out.println("Booking Date: " + getBookingDate());
        System.out.println("Start Time: " + getStartTime());
        System.out.println("End Time: " + getEndTime());
        System.out.println("\n");
    }

    public void viewFacilityB() {
        try {
            Scanner scan = new Scanner(new File(FILE_NAME3));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split("\\|");
                FacilityBooking newFacb = new FacilityBooking();
                if (parts.length >= 5) {
                    newFacb.setBookId(parts[0]);
                    newFacb.setId(parts[1]);
                    newFacb.setFacilityName(parts[2]);
                    newFacb.setBookingDate(LocalDate.parse(parts[3], DATE_FORMATTER));
                    newFacb.setStartTime(LocalTime.parse(parts[4], TIME_FORMATTER));
                    newFacb.setEndTime(LocalTime.parse(parts[5], TIME_FORMATTER));
                }
                facilityB.add(newFacb);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file 'facilitybooking.txt' was not found.");
        }

        for (int x = 0; x < facilityB.size(); x++) {
            facilityB.get(x).showBookingInfo();
        }
    }

    public void updateFacilityB() {
        try {
            Scanner scan = new Scanner(new File(FILE_NAME3));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split("\\|");
                FacilityBooking newFacb = new FacilityBooking();
                if (parts.length >= 5) {
                    newFacb.setBookId(parts[0]);
                    newFacb.setId(parts[1]);
                    newFacb.setFacilityName(parts[2]);
                    newFacb.setBookingDate(LocalDate.parse(parts[3], DATE_FORMATTER));
                    newFacb.setStartTime(LocalTime.parse(parts[4], TIME_FORMATTER));
                    newFacb.setEndTime(LocalTime.parse(parts[5], TIME_FORMATTER));
                }
                facilityB.add(newFacb);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file 'facilitybooking.txt' was not found.");
        }

        System.out.print("Enter the Booking ID : ");
        String checkId = search2.nextLine();
        boolean found = false;
        for (int a = 0; a < facilityB.size(); a++) {
            if (facilityB.get(a).getBookId().equalsIgnoreCase(checkId)) {
                found = true;
                System.out.println("Select the field you want to modify");
                System.out.println("1. ID");
                System.out.println("2. Facility Name");
                System.out.println("3. Booking Date");
                System.out.println("4. Start Time");
                System.out.println("5. End Time");
                System.out.println("6. Cancel");
                System.out.print("Enter choice: ");
                int choice1 = input.nextInt();
                input.nextLine();
                FacilityBooking facb = facilityB.get(a);
                switch (choice1) {
                    case 1 -> {
                        System.out.println("Enter new Resident/Tenant ID: ");
                        String newId = input.nextLine();
                        facb.setId(newId);
                    }
                    case 2 -> {
                        System.out.print("Enter new facility name: ");
                        String newFacilityName = input.nextLine();
                        facb.setFacilityName(newFacilityName);
                    }
                    case 3 -> {

                        System.out.print("Enter new Booking Date: ");
                        LocalDate newBookingDate = LocalDate.parse(input.nextLine(), DATE_FORMATTER);
                        facb.setBookingDate(newBookingDate);
                    }
                    case 4 -> {
                        System.out.print("Enter new Start Time: ");
                        LocalTime newStartTime = LocalTime.parse(input.nextLine(), TIME_FORMATTER);
                        facb.setStartTime(newStartTime);
                    }
                    case 5 -> {
                        System.out.print("Enter new End Time: ");
                        LocalTime newEndTime = LocalTime.parse(input.nextLine(), TIME_FORMATTER);
                        facb.setEndTime(newEndTime);
                    }
                    case 6 -> {
                        System.out.println("Modification canceled.");
                        return;
                    }
                    default ->
                        System.out.println("Invalid input.");
                }

                facilityB.set(a, facb);
                System.out.println("Update successful!");
                break;
            }
        }
        if (!found) {
            System.out.println("Booking ID not found.");
        } else {
            try {
                try (FileWriter infile = new FileWriter(FILE_NAME3)) {
                    for (int i = 0; i < facilityB.size(); i++) {
                        infile.append(
                                facilityB.get(i).getBookId() + "|"
                                + facilityB.get(i).getId() + "|"
                                + facilityB.get(i).getFacilityName() + "|"
                                + facilityB.get(i).getBookingDate() + "|"
                                + facilityB.get(i).getStartTime() + "|"
                                + facilityB.get(i).getEndTime() + "\n");
                    }
                }
            } catch (IOException ev) {
                System.out.println("An error occured when writing into the file.");
            }
        }
    }

    public void deleteFacilityB() {
        System.out.print("Enter Booking ID to be deleted: ");
        Scanner scan = new Scanner(System.in);
        String bookIdDelete = scan.nextLine();

        StringBuilder contentBuilder = new StringBuilder();
        boolean bookIdFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME3))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] parts = currentLine.split("\\|");
                String savedId = (parts[0]);
                if (savedId.equalsIgnoreCase(bookIdDelete)) {
                    bookIdFound = true;
                } else {
                    contentBuilder.append(currentLine).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!bookIdFound) {
            System.out.println("Booking ID " + bookIdDelete + " was not found.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME3))) {
            bw.write(contentBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Deleted the booking ID: " + bookIdDelete);
    }

}
