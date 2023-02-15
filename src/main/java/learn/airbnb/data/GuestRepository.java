package learn.airbnb.data;
import java.util.List;
import learn.airbnb.model.Guest;

import java.util.Arrays;

public interface GuestRepository {
    List<Guest> findAll();
    List<Guest> findByState(String state);
    Guest findById (String guestId);
}
