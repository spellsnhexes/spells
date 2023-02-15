package learn.airbnb.data;
import java.time.LocalDate;
import java.util.List;
import learn.airbnb.model.Reservation;
import java.util.Arrays;

public interface ReservationRepository {

    List<Reservation> findByHost(String hostId);

    Reservation addReservation(Reservation reservation) throws DataException;

    boolean modifyReservation(Reservation reservation) throws DataException;

}
