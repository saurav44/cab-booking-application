package com.assignment.cabbookingapp.Driver;

import com.assignment.cabbookingapp.Controller.DriverController;
import com.assignment.cabbookingapp.Controller.UserController;
import com.assignment.cabbookingapp.Repository.DriverRepository;
import com.assignment.cabbookingapp.Repository.UserRepository;
import com.assignment.cabbookingapp.Service.DriverService;
import com.assignment.cabbookingapp.Service.TripService;
import com.assignment.cabbookingapp.Service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DemoDriver {

    UserController userController;
    DriverController driverController;

    public DemoDriver() {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        DriverRepository driverRepository = new DriverRepository();
        DriverService driverService = new DriverService(driverRepository);
        TripService tripService = new TripService(driverService, userService);

        userController = new UserController(userService, tripService);
        driverController = new DriverController(driverService, tripService);
    }


    private void demo() {

        // User onboarding
        String r1 = "Abhishek";
        userController.add_user(r1, 'M', 23);
        String r2 = "Rahul";
        userController.add_user(r2, 'M', 29);
        String r3 = "Nandini";
        userController.add_user(r3, 'F', 22);

        // Updating user location
        userController.update_userLocation(r1, 0.0,0.0);
        userController.update_userLocation(r2, 10.0,0.0);
        userController.update_userLocation(r3, 15.0,6.0);

        // Onboarding Drivers
        driverController.add_driver("driver1", 'M', 18, "audi", "KA-1234", 10.0, 1.0);
        driverController.add_driver("driver2", 'F', 28, "audi", "KA-1234", 11.0, 10.0);
        driverController.add_driver("driver3", 'M', 19, "audi", "KA-1234", 5.0, 3.0);
        driverController.add_driver("driver4", 'F', 28, "audi", "KA-1234", 10.0, 2.0);

        // Find rides

        userController.find_ride(r1, 0.0, 0.0, 20.0, 1.0);

        userController.find_ride(r2, 10.0, 0.0, 15.0, 3.0);

        // Choose ride

        driverController.change_driverStatus("driver4", false);
        userController.choose_ride(r2, "driver4");

        userController.choose_ride(r2, "driver1");

        // Calculate the bill
        userController.calculateBill(r2);

        // Drivers total earning
        driverController.find_total_earning();

    }

    private void menuDrivenDemo() {
        System.out.println("Welcome to Cab booking application."
                + "\n\nChoose any of the option:"
                + "\ni. User onboarding"
                + "\n\t1. Register a User"
                + "\n\t2. Update user contact details"
                + "\n\t3. Update user location"
                + "\nii. Driver onboarding"
                + "\n\t4. Register a Driver"
                + "\n\t5. Update driver location"
                + "\n\t6. Update driver available status"
                + "\niii. Book a ride"
                + "\n\t7. Find a ride"
                + "\n\t8. Choose a ride"
                + "\n\t9. Calculate bill"
                + "\n\t10. Find total earning of drivers"
                + "\n999. Exit\n"
        );
        Scanner scn = new Scanner(System.in);

        while(true) {

            System.out.print("Enter the option number: ");
            int option = scn.nextInt();
            if(option < 1 || option > 10)
                break;

            switch (option) {
                case 1:
                    System.out.println("Enter user name, gender and age");
                    String name = scn.next();
                    char gender = scn.next().charAt(0);
                    int age = scn.nextInt();
                    userController.add_user(name, gender, age);
                    System.out.println("User registered successfully\n");
                    break;
                case 2:
                    System.out.println("Enter the user name, phone number");
                    String username = scn.next();
                    String phone = scn.next();
                    userController.update_user(username, phone);
                    System.out.println("User contact updated successfully\n");
                    break;
                case 3:
                    System.out.println("Enter user name, x-coordinate, y-coordinate");
                    username = scn.next();
                    double newX = scn.nextDouble();
                    double newY = scn.nextDouble();
                    userController.update_userLocation(username,newX, newY);
                    System.out.println("User location updated successfully\n");
                    break;
                case 4:
                    System.out.println("Enter driver name, gender and age");
                    name = scn.next();
                    gender = scn.next().charAt(0);
                    age = scn.nextInt();
                    System.out.println("Enter vehicle name, number");
                    String vehicleName = scn.next();
                    String vehicleNumber = scn.next();
                    System.out.println("Enter driver x-coordinate, y-coordinate");
                    newX = scn.nextDouble();
                    newY = scn.nextDouble();
                    driverController.add_driver(name, gender, age, vehicleName, vehicleNumber, newX, newY);
                    System.out.println("Driver registered successfully\n");
                    break;
                case 5:
                    System.out.println("Enter driver name, x-coordinate, y-coordinate");
                    name = scn.next();
                    newX = scn.nextDouble();
                    newY = scn.nextDouble();
                    driverController.update_driverLocation(name,newX, newY);
                    System.out.println("Driver location updated successfully\n");
                    break;
                case 6:
                    System.out.println("Enter driver name, available status");
                    name = scn.next();
                    Boolean status = scn.nextBoolean();
                    driverController.change_driverStatus(name,status);
                    System.out.println("Driver status updated successfully\n");
                    break;
                case 7:
                    System.out.println("Enter username");
                    name = scn.next();
                    System.out.println("Enter source location");
                    double srcX = scn.nextDouble();
                    double srcY = scn.nextDouble();
                    System.out.println("Enter destination location");
                    double destX = scn.nextDouble();
                    double destY = scn.nextDouble();
                    List<String> ridesFound = userController.find_ride(name, srcX, srcY, destX, destY);
                    System.out.println("Available Rides: " + ridesFound + "\n");
                    break;
                case 8:
                    System.out.println("Enter user name, driver name");
                    username = scn.next();
                    String drivername = scn.next();
                    userController.choose_ride(username, drivername);
                    break;
                case 9:
                    System.out.println("Enter user name");
                    username = scn.next();
                    Long billAmount = userController.calculateBill(username);
                    System.out.println("ride Ended bill amount: $" + billAmount + "\n");
                    break;
                case 10:
                    Map<String, Double> total_drivers_earning = driverController.find_total_earning();

                    for(String driver: total_drivers_earning.keySet()) {
                        System.out.println(driver + " earn $" + total_drivers_earning.get(driver));
                    }
                    System.out.println();
                    break;
                default:
                    break;

            }
        }
    }

    public static void  main(String[] args) {

        DemoDriver d = new DemoDriver();
        d.demo();
//        d.menuDrivenDemo();
    }

}
