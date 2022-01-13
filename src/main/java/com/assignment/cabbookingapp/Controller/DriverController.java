package com.assignment.cabbookingapp.Controller;

import com.assignment.cabbookingapp.Model.Cab;
import com.assignment.cabbookingapp.Model.Location;
import com.assignment.cabbookingapp.Model.Driver;
import com.assignment.cabbookingapp.Service.TripService;
import com.assignment.cabbookingapp.Service.DriverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DriverController {

    private final DriverService driverService;
    private final TripService tripService;

    public DriverController(DriverService driverService, TripService tripService) {
        this.driverService = driverService;
        this.tripService = tripService;
    }

    @PostMapping("/driver/register")
    public Driver add_driver(String name, Character gender, Integer age, String cabName,
                                     String cabNumber, Double x, Double y) {
        return driverService.add_driver(new Driver(name, gender, age), new Cab(cabName, cabNumber), new Location(x, y));
    }
    

    @PutMapping("/driver/update/location")
    public Driver update_driverLocation(String driverName, Double x, Double y) {
        return driverService.update_driverLocation(driverName, new Location(x,y));
    }

    @PutMapping("/driver/update/status")
    public Driver change_driverStatus(String driverName, Boolean status) {
        return driverService.update_driverStatus(driverName, status);
    }

    @GetMapping("driver/earnings")
    public Map<String, Double> find_total_earning() {
        Map<String, Double> total_drivers_earning = driverService.find_total_earning();
        System.out.println();
        for(String driver: total_drivers_earning.keySet()) {
            System.out.println(driver + " earn $" + total_drivers_earning.get(driver));
        }

        return driverService.find_total_earning();
    }


}
