package com.assignment.cabbookingapp.Service;

import com.assignment.cabbookingapp.Model.Driver;
import com.assignment.cabbookingapp.Model.Location;
import com.assignment.cabbookingapp.Model.Trip;
import com.assignment.cabbookingapp.Model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripService {

    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 5.0;
    public static final Double PER_KM_RATE = 10.0;

    private final Map<String, List<Trip>> trips = new HashMap<>();

    private final DriverService driverService;
    private final UserService userService;

    public TripService(
            DriverService driverService,
            UserService userService
    ) {
        this.driverService = driverService;
        this.userService = userService;
    }

    public List<String> find_ride(String userName, Double sourceX, Double sourceY,
                                  Double destX, Double destY) {
        User user = userService.getUser(userName);
        Location from = new Location(sourceX, sourceY);
        Location to = new Location(destX, destY);
        user.setTrip(new Trip(user, from, to));
        return driverService.getDrivers(new Location(sourceX, sourceY), MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
    }

    public Boolean choose_ride(String username, String driverName) {
        User user = userService.getUser(username);
        Driver driver = driverService.get_driver(driverName);
        if(!driver.getAvailable())
            return false;
        user.getTrip().setDriver(driver);
        return true;
    }

    public Long calculateBill(String username) {

        User user = userService.getUser(username);
        Trip trip = user.getTrip();
        Location from = trip.getFrom();
        Location to = trip.getTo();

        Long billAmount =  Math.round(from.distance(to) * PER_KM_RATE);

        Driver driver = trip.getDriver();
        userService.update_userLocation(username, to);
        driverService.update_driverLocation(driver.getName(), to);
        driver.setEarnings(driver.getEarnings() + billAmount);
        return billAmount;
    }

    public List<Trip> tripHistory(User user) {
        return trips.get(user.getName());
    }

}
