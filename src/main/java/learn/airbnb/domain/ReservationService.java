package learn.airbnb.domain;

import learn.airbnb.data.*;
import learn.airbnb.model.*;
import java.util.List;
import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;
    public ReservationService(ReservationRepository reservationRepository, GuestRepository guestRepository, HostRepository hostRepository) {
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
        this.hostRepository = hostRepository;
    }



    public List<Reservation> findByHost(Host host){

        Map<String, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(g -> g.getGuestId(), g -> g )); //indexes 'g' are the key for the map
        Map<String, Host> hostMap = hostRepository.findAll().stream()
                .collect(Collectors.toMap(h -> h.getHostId(), h -> h));

        List<Reservation> result = reservationRepository.findByHost(host.getHostId());
        for (Reservation reservation : result){
            reservation.setGuest(guestMap.get(reservation.getGuest().getGuestId()));
            reservation.setHost(hostMap.get(reservation.getHost().getHostId()));
        }

        return result;
    }
}
