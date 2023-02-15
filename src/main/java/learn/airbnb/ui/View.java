package learn.airbnb.ui;
import learn.airbnb.domain.ReservationResult;
import learn.airbnb.model.Guest;
import learn.airbnb.model.Host;
import learn.airbnb.model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class View {

    private ConsoleIO io;
    public View(ConsoleIO io) { this.io = this.io; }
    private final Scanner console = new Scanner(System.in);

    public MenuOptions displayMainMenu(){
        MenuOptions[] values = MenuOptions.values();
        printHeader("Main Menu");
        for (int i = 0; i < values.length; i++){
            System.out.printf("%s. %s%n", i, values[i].getTitle());
        }
        int index = readInt("Select and option, [0-4]", 0, 4);
        return values[index];
    }

    public void printHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length())); //since it's a header, underlines with equals and will repeat to the length of the string
    }

    public void displayReservations(List<Reservation> reservations) {
        printHeader("Reservations:" );
        if (reservations.size() == 0 || reservations == null || reservations.isEmpty()){
            System.out.println("No reservations detected.");
            return;
        } else {
            for (Reservation reservation : reservations){
                System.out.printf("%s %s %s %s %s %s",
                reservation.getResId(),
                reservation.getGuest(),
                reservation.getHost(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getLocation()
                );
            }
        }
    }

    public LocalDate getReservationDates() {
        printHeader(MenuOptions.VIEW_RESERVATIONS.getMessage());
        return io.readLocalDate("Select a date [MM/DD/YYYY]: ");
    }

    public void displayResult(ReservationResult reservationResult){
        if (reservationResult.isSuccess()){
            System.out.println("R.ID | START - END | FIRST & LAST NAME | STATE");
            printHeader(
                    "R.ID " + reservationResult.getReservation().getResId() + " | " +
                    reservationResult.getReservation().getStartDate() + " - " +
                    reservationResult.getReservation().getEndDate() + " | " +
                    reservationResult.getReservation().getGuest().getGuestFirstName() + ", " +
                    reservationResult.getReservation().getGuest().getGuestLastName() + " | " +
                    reservationResult.getReservation().getLocation());
        } else {
            printHeader("Error: ");
            for (String error : reservationResult.getMessages()){
                System.out.println(error);
            }
        }
    }

    public Reservation createReservation(Guest guest, Host host) {
        Reservation reservation = new Reservation();
        reservation.setGuest(guest);
        reservation.setHost(host);
        reservation.getGuest().setGuestFirstName(readRequiredString("First Name: "));
        reservation.getGuest().setGuestLastName(readRequiredString("Last Name: "));
        reservation.setStartDate(LocalDate.parse("Enter start date MM/DD/YYYY: "));
        reservation.setEndDate(LocalDate.parse("Enter end date MM/DD/YYYY: "));
        reservation.setLocation("Enter Location:  ");
        return reservation;
    }

    public void modifyReservation() {
    }

    public void cancelReservation() {
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }

    private String readRequiredString(String prompt) {
        String result = null;
        do {
            result = readString(prompt).trim();
            if (result.length() == 0) {
                System.out.println("Value is required.");
            }
        } while (result.length() == 0);
        return result;
    }

    private Boolean readBoolean(String prompt) {
        return readRequiredString(prompt).equalsIgnoreCase("yes");
    }

    private int readInt(String prompt) {
        int result = 0;
        //guard variable, set to true or false to let us move on
        boolean isValid = false;
        do {
            String value = readRequiredString(prompt);
            try {
                result = Integer.parseInt(value);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Value must be a number.");
            }
        } while (!isValid);
        return result;
    }

    //Overloading
    private int readInt(String prompt, int min, int max) {
        int result = 0;
        do {
            result = readInt(prompt);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        } while (result < min || result > max);

        return result;
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, String.valueOf(List.of(message)));
    }


    public Host getHostEmail(List<Host> hosts) {
        printHeader(MenuOptions.VIEW_RESERVATIONS.getMessage());
        displayHosts(hosts);
        if (hosts.size() == 0) {
            return null;
        }

        String hostEmail = io.readString("Select an item email: ");
        Host host = hosts.stream()
                .filter(i -> i.getHostId().equals(hostEmail))
                .findFirst()
                .orElse(null);

        if (host == null) {
            displayStatus(false, String.format("No item with email %s found.", hostEmail));
        }

        return host;

    }


    public void enterToContinue() {
        io.readString("Press [Enter] to continue.");
    }

    public void displayHosts(List<Host> hosts) {
        if (hosts == null || hosts.isEmpty()){
            io.println("No hosts found.");
            return;
        }

        for (Host host : hosts){
            io.printf(" %s | %s | %s | %s | %s | %s ", host.getHostId(), host.getLastName(), host.getEmail(), host.getState(), host.getStandardRate(), host.getWeekendRate());
        }
    }
}
