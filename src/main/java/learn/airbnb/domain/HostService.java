package learn.airbnb.domain;

import learn.airbnb.data.HostFileRepository;
import learn.airbnb.data.HostRepository;
import learn.airbnb.model.Host;

import java.util.List;
import java.util.stream.Collectors;

public class HostService {
    private final HostRepository repository;
    public HostService(HostRepository repository) { this.repository = repository; }

    public List<Host> findByEmail(String hostEmail) {
        return repository.findAll().stream()
                .filter(i -> i.getEmail().equals(hostEmail))
                .collect(Collectors.toList());
    }
}
