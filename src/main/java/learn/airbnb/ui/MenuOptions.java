package learn.airbnb.ui;

import java.awt.*;

public enum MenuOptions {
    VIEW_RESERVATIONS("View Reservations"),
    CREATE_RESERVATIONS("Create New Reservation"),
    MODIFY_RESERVATIONS("Modify Existing Reservation"),
    CANCEL_RESERVATIONS("Cancel Reservation"),
    EXIT("Exit");

    private final String title;
    private String message;
    MenuOptions(String title){

        this.title = title;
        this.message = message;
    }

    public String getMessage() { return message; }

    //public void setMessage(String message) { this.message = message; }

    public String getTitle(){
        return title;
    }
}
