package learn.airbnb.data;
import learn.airbnb.model.*;
import java.util.List;

public class HostFileRepository implements HostRepository {
    public HostFileRepository(String s) {}

    @Override
    public List<Host> findAll() {
        return null;
    }

    @Override
    public Host findById(Host hostId) { return null; }

    @Override
    public Host findByEmail(String email) {  return null; }
}
