package com.assignment.cabbookingapp;

import com.assignment.cabbookingapp.Model.Cab;
import com.assignment.cabbookingapp.Model.Driver;
import com.assignment.cabbookingapp.Model.Location;
import com.assignment.cabbookingapp.Model.User;
import com.assignment.cabbookingapp.Repository.DriverRepository;
import com.assignment.cabbookingapp.Repository.UserRepository;
import com.assignment.cabbookingapp.Service.DriverService;
import com.assignment.cabbookingapp.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class CabBookingAppApplicationTests {

    UserService userService;
    DriverService driverService;

    @MockBean
    UserRepository userRepository;
    @MockBean
    DriverRepository driverRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        driverService = new DriverService(driverRepository);
    }

    @Test
    void add_userTest() {
        User user = new User("Harry", 'M', 22);
        when(userRepository.add_user(user)).thenReturn(user);
        Assertions.assertEquals(user, userService.add_user(user));
    }

    @Test
    void update_userTest() {
        User user = new User("Harry", 'M', 22);
        userRepository.add_user(user);
        when(userRepository.update_user("Harry", "911351566")).thenReturn(user);
        Assertions.assertEquals(user, userService.update_user("Harry", "911351566"));
    }

    @Test
    void update_userLocationTest() {
        User user = new User("Harry", 'M', 22);
        userRepository.add_user(user);
        Location location = new Location(2.0, 5.0);
        when(userRepository.updateLocation("Harry", location)).thenReturn(user);
        Assertions.assertEquals(user, userService.update_userLocation("Harry", location));
    }

    @Test
    void add_driverTest() {
        Driver driver = new Driver("driver1", 'M', 25);
        Cab cab = new Cab("audi", "KA-12342");
        Location location = new Location(10.0,1.0);
        when(driverRepository.add_driver(driver, cab, location)).thenReturn(driver);
        Assertions.assertEquals(driver, driverService.add_driver(driver, cab, location));
    }

    @Test
    void update_DriverLocationTest() {
        Driver driver = new Driver("driver1", 'M', 25);
        Cab cab = new Cab("audi", "KA-12342");
        Location location = new Location(10.0,1.0);
        driverRepository.add_driver(driver, cab, location);
        Location newLocation = new Location(5.0, 8.0);
        when(driverRepository.updateLocation("driver1", newLocation)).thenReturn(driver);
        Assertions.assertNotEquals(driver, driverService.update_driverLocation("driver1", new Location(10.0, 1.0)));
    }

    @Test
    void update_DriverStatusTest() {
        Driver driver = new Driver("driver1", 'M', 25);
        Cab cab = new Cab("audi", "KA-12342");
        Location location = new Location(10.0,1.0);
        driverRepository.add_driver(driver, cab, location);
        when(driverRepository.change_driver_status("driver1", false)).thenReturn(driver);
        Assertions.assertEquals(driver, driverService.update_driverStatus("driver1", false));
    }

    @Test
    void getDriversTest() {
        driverRepository.add_driver(new Driver("driver1", 'M', 25),
                new Cab("audi", "KA-12342"),
                new Location(10.0,1.0));
        driverRepository.add_driver(new Driver("driver2", 'M', 25),
                new Cab("audi", "KA-12342"),
                new Location(2.0,3.0));

        Location source = new Location(0.0, 0.0);
        when(driverRepository.getDrivers(source, 5.0)).
                thenReturn(Stream.of("driver2").collect(Collectors.toList()));
        Assertions.assertEquals(1, driverService.getDrivers(source, 5.0).size());
    }

}
