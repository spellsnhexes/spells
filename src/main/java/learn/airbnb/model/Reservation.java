package learn.airbnb.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Reservation {

    private String resId;
    private Guest guest;
    private Host host;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private double cost;
    private BigDecimal total;
    public BigDecimal getTotal() { return total; }
    public double getCost() { return cost; }
    public void setCost(double cost){ this.cost = cost; }

    public Guest getGuest() { return guest; }

    public void setGuest(Guest guest) { this.guest = guest; }

    public Host getHost() { return host; }

    public void setHost(Host host) { this.host = host; }

    public String getResId() { return resId; }

    public void setResId(String resId) { this.resId = resId; }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
