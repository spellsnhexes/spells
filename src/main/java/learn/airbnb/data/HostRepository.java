package learn.airbnb.data;
import learn.airbnb.model.Host;
import java.util.List;

public interface HostRepository {
    List<Host> findAll();
    Host findById(Host hostId);
    Host findByEmail(String email);
}
