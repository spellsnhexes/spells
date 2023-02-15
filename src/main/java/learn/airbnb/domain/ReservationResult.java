package learn.airbnb.domain;
import learn.airbnb.model.Reservation;
import java.util.ArrayList;

public class ReservationResult {
        public boolean isSuccess() { return false; }
        public ArrayList<String> getMessages() { return messages; }

        public void setMessages(ArrayList<String> messages) { this.messages = messages; }

        //error message
        private ArrayList<String> messages =  new ArrayList<>();

        public Reservation getReservation() { return reservation; }

        public void setReservation(Reservation reservation) { this.reservation = reservation; }

        private Reservation reservation;
        public void addErrorMessage(String message) { messages.add(message); }

}

