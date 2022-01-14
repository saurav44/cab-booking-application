package com.assignment.cabbookingapp.Controller;

import com.assignment.cabbookingapp.Model.Location;
import com.assignment.cabbookingapp.Model.User;
import com.assignment.cabbookingapp.Service.TripService;
import com.assignment.cabbookingapp.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final TripService tripService;

    public UserController(UserService userService, TripService tripService) {
        this.userService = userService;
        this.tripService = tripService;
    }

    @PostMapping("/user/register")
    public User add_user(String name, Character gender, Integer age) {
        return userService.add_user(new User(name, gender, age));
    }

    @GetMapping("/user")
    public User get_user(String username) {
        return userService.getUser(username);
    }

    @PutMapping("/user/update/details")
    public User update_user(String username, String phone) {
        return userService.update_user(username, phone);
    }

    @PutMapping("/user/update/location")
    public User update_userLocation(String username, Double newX, Double newY) {
        return userService.update_userLocation(username, new Location(newX, newY));
    }

    @PostMapping("/find_ride")
    public List<String> find_ride(
            final String userName,
            final Double sourceX,
            final Double sourceY,
            final Double destX,
            final Double destY) {

        System.out.println("------ Finding rides for " + userName + " ---------");
        List<String> ridesFound = tripService.find_ride(userName, sourceX, sourceY, destX, destY);
        if(ridesFound.size() == 0) {
            System.out.println("No ride found [Since all the driver are more than 5 units away from user]\n");
            return null;
        }
        else
            System.out.println("Available Rides: " + ridesFound);
        return ridesFound;
    }

    @PostMapping("/choose_ride")
    public void choose_ride(String username, String drivername) {
        if(tripService.choose_ride(username, drivername))
            System.out.println("\nRide Started");
        else
            System.out.println("\n" + drivername + " is set to not available");
    }

    @GetMapping("/user/bill")
    public Long calculateBill(String username) {
        Long billAmount = tripService.calculateBill(username);
        System.out.println("ride Ended bill amount: $" + billAmount);
        return billAmount;
    }

}
