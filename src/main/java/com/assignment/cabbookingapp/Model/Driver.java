package com.assignment.cabbookingapp.Model;

import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
public class Driver extends Person {

    private Double earnings;
    private Cab cab;
    private Boolean isAvailable;
    private Location location;

    public Driver(String name, Character gender, Integer age) {
        super(name, gender, age);
        this.isAvailable = true;
        this.earnings = 0.0;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

}
