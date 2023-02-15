package learn.airbnb.model;

public class Host {

    private String hostId;
    private String lastName;
    private String email;
    private String state;
    private Double standardRate;
    private Double weekendRate;

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public Double getStandardRate() { return standardRate; }

    public void setStandardRate(Double standardRate) { this.standardRate = standardRate; }

    public Double getWeekendRate() { return weekendRate; }

    public void setWeekendRate(Double weekendRate) { this.weekendRate = weekendRate; }
    public String getHostId() { return hostId; }

    public void setHostId(String hostId) { this.hostId = hostId; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}


