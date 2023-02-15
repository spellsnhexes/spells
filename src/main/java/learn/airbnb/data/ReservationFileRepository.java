package learn.airbnb.data;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import learn.airbnb.model.*;

public class ReservationFileRepository implements ReservationRepository {

    private static final String HEADER = "id,start_date,end_date,guest_first,guest_last,state";
    private final String directory;
    public ReservationFileRepository(String directory) { this.directory = directory; }

    public List<Reservation> findAll(LocalDate startDate, LocalDate endDate) throws DataException{
        ArrayList<Reservation> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(directory))){
            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()){
                String[] fields = line.split(",");
                if (fields.length == 6){
                   result.add(deserialize(fields));
                }
            }
        }  catch (FileNotFoundException ex){
        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }
        return result;
    }

         public List<Reservation> findByHost(Host hostId) {
             ArrayList<Reservation> result = new ArrayList<>();
             try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(hostId)))) {

                 reader.readLine(); // read header

                 for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                     String[] fields = line.split(",", -1);
                     if (fields.length == 6) {
                         result.add(deserialize(fields));
                     }
                 }
             } catch (IOException ex) {
                 // don't throw on read
             }
             return result;
         }


    public Reservation addReservation(Reservation reservation, LocalDate startDate, LocalDate endDate) throws DataException{
        List<Reservation> all = findAll(startDate, endDate);
        reservation.setResId(java.util.UUID.randomUUID().toString());
        all.add(reservation);
        writeAll(all);
        return reservation;
    }


    private String getFilePath(Host hostId){
        return Paths.get(directory, hostId + ".csv").toString();
    }


    public boolean modifyReservation(Reservation reservation, LocalDate startDate, LocalDate endDate) throws DataException{
        List<Reservation> all = findAll(startDate, endDate);
        for (int i = 0; i < all.size(); i++){
            if (all.get(i).getResId() == reservation.getResId()){
                all.set(i, reservation);
                writeAll(all);
                return true;
            }
        } return false;
    }

    public boolean cancelReservation(String resId, LocalDate startDate, LocalDate endDate) throws DataException{
        List<Reservation> all = findAll(startDate, endDate);
        for (int i = 0; i < all.size(); i++){
            if(all.get(i).getResId().equals(resId)){
                all.remove(i);
                writeAll(all);
                return true;
            }
        } return false;
    }


    private void writeAll(List<Reservation> reservations) throws DataException {
        try (PrintWriter writer = new PrintWriter(directory)) {
                      writer.println("id,start_date,end_date,guest_first,guest_last,state");
            for (Reservation r : reservations) {
                writer.println(serialize(r));
            }

        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);

        }
    }

    private String serialize(Reservation reservation) {
        return String.format("%s %s %s %s %s",
        reservation.getResId(),
        reservation.getStartDate(),
        reservation.getEndDate(),
        reservation.getGuest().getGuestId(),
        reservation.getCost());
    }

    private Reservation deserialize(String[] fields){
        Reservation result = new Reservation();
        result.setResId(fields[0]);
        result.setStartDate(LocalDate.parse(fields[2]));
        result.setEndDate(LocalDate.parse(fields[3]));
        result.setCost(Double.parseDouble(fields[5]));

        Guest guest = new Guest();
        guest.setGuestId(fields[4]);
        result.setGuest(guest);

        Host hostId = new Host();
        hostId.setHostId(fields[1]);
        result.setHost(hostId);
        return result;
    }
}
