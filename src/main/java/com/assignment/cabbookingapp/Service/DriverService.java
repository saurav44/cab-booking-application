package com.assignment.cabbookingapp.Service;

import com.assignment.cabbookingapp.Model.*;
import com.assignment.cabbookingapp.Repository.DriverRepository;
import com.assignment.cabbookingapp.Model.Driver;
import com.assignment.cabbookingapp.Model.Location;

import java.util.List;
import java.util.Map;

public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver add_driver(Driver driver, Cab cab, Location location) {
        return driverRepository.add_driver(driver, cab, location);
    }

    public Driver get_driver(String driverName) {
        return driverRepository.getDriver(driverName);
    }

    public Driver update_driverLocation(String driverName, Location location) {
        return driverRepository.updateLocation(driverName, location);
    }

    public Driver update_driverStatus(String driverName, Boolean status) {
        return driverRepository.change_driver_status(driverName, status);
    }

    public List<String> getDrivers(Location from, Double maxDistance) {
        return driverRepository.getDrivers(from, maxDistance);
    }


    public Map<String, Double> find_total_earning() {
        return driverRepository.find_total_earning();
    }
}
