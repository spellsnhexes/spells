package learn.airbnb;
import learn.airbnb.data.*;
import learn.airbnb.domain.*;
import learn.airbnb.model.*;
import learn.airbnb.ui.*;


import java.util.List;


public class App {
    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO();
        View view = new View(io);

        ReservationFileRepository reservationFileRepository = new ReservationFileRepository("./data/reservations");
        GuestFileRepository guestFileRepository = new GuestFileRepository("./data/guests.csv");
        HostFileRepository hostFileRepository = new HostFileRepository("./data/hosts.csv");

        GuestService guestService = new GuestService(guestFileRepository);
        HostService hostService = new HostService(hostFileRepository);
        ReservationService reservationService = new ReservationService(reservationFileRepository, guestFileRepository, hostFileRepository);


        final Controller controller = new Controller(reservationService, hostService, guestService, view);
        controller.run();
    }
}
