package com.assignment.cabbookingapp.Model;

import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
public class User extends Person {

    private String phone;
    private Location location;
    private Trip trip;

    public User(String name, Character gender, Integer age) {
        super(name, gender, age);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Trip getTrip() {
        return trip;
    }



    public void setTrip(Trip trip) {
        this.trip = trip;
    }


}
