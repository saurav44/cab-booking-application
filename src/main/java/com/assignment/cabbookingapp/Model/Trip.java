package com.assignment.cabbookingapp.Model;

import lombok.ToString;

@ToString
public class Trip {

    private final User user;
    private Driver driver;
    private final Location from;
    private final Location to;

    public Trip(User user, Location from, Location to) {
        this.user = user;
        this.from = from;
        this.to = to;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
