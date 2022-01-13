package com.assignment.cabbookingapp.Repository;

import com.assignment.cabbookingapp.Model.Cab;
import com.assignment.cabbookingapp.Model.Location;
import com.assignment.cabbookingapp.Model.Driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRepository {
    Map<String, Driver> drivers = new HashMap<>();

    public Driver add_driver(Driver driver, Cab cab, Location location) {
        if(drivers.containsKey(driver.getName())) {
            return null;
        }
        driver.setCab(cab);
        driver.setLocation(location);
        drivers.put(driver.getName(), driver);
        return driver;
    }

    public Driver getDriver(String driverName) {
        if(!drivers.containsKey(driverName))
            return null;

        return drivers.get(driverName);
    }

    public Driver updateLocation(String driverName, Location location) {
        if(!drivers.containsKey(driverName)) {
            return null;
        }
        drivers.get(driverName).setLocation(location);
        return drivers.get(driverName);
    }

    public Driver change_driver_status(String driverName, Boolean status) {
        if(!drivers.containsKey(driverName)) {
            return null;
        }
        drivers.get(driverName).setAvailable(status);
        return drivers.get(driverName);

    }

    public List<String> getDrivers(Location from, Double maxDistance) {
        List<String> result = new ArrayList<>();
        for (Driver driver : drivers.values()) {

            if (driver.getLocation().distance(from) <= maxDistance) {
                result.add(driver.getName());
            }
        }
        return result;
    }

    public Map<String, Double> find_total_earning() {
        Map<String, Double> total_earnings = new HashMap<>();

        for(Driver driver: drivers.values()) {
            total_earnings.put(driver.getName(), driver.getEarnings());
        }
        return  total_earnings;
    }
}
