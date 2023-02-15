package learn.airbnb.ui;
import learn.airbnb.data.DataException;
import learn.airbnb.domain.GuestService;
import learn.airbnb.domain.HostService;
import learn.airbnb.domain.ReservationService;
import learn.airbnb.model.*;
import java.util.List;
import java.time.LocalDate;

public class Controller {
    private final ReservationService reservationService;
    private final HostService hostService;
    private final GuestService guestService;
    private final View view;

    public Controller(ReservationService reservationService, HostService hostService, GuestService guestService, View view) {
        this.reservationService = reservationService;
        this.hostService = hostService;
        this.guestService = guestService;
        this.view = view;
    }

    public void run(){
        try {
            runMainMenu();
        } catch (DataException ex) {
            view.printHeader("Fatal error: " + ex);
        }
    }

    private void runMainMenu() throws DataException{
        MenuOptions option;
        do {
            option = view.displayMainMenu();
            System.out.println(option.getTitle());
            switch (option){
                case VIEW_RESERVATIONS -> viewReservations();
                case CREATE_RESERVATIONS -> createReservation();
                case MODIFY_RESERVATIONS -> modifyReservation();
                case CANCEL_RESERVATIONS -> cancelReservation();
                case EXIT -> view.printHeader("Goodbye!");
            }
        } while (option != MenuOptions.EXIT);
    }

    private void viewReservations() throws DataException{
        viewHosts();
        Host host = view.getHostEmail();
        List<Reservation> reservations = reservationService.findByHost(host);
        view.displayReservations(reservations);
        view.enterToContinue();

        }

    private void createReservation() throws DataException{

    }

    private void modifyReservation() throws DataException{

    }

    private void cancelReservation() throws DataException{

    }

    private void viewHosts() throws DataException{
        view.printHeader(MenuOptions.VIEW_RESERVATIONS.getMessage());
        Host hostEmail = view.getHostEmail();
        List<Host> hosts = hostService.findByEmail(String.valueOf(hostEmail));
        view.printHeader("Hosts");
        view.displayHosts(hosts);

    }
}
