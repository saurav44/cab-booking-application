package com.assignment.cabbookingapp;

import com.assignment.cabbookingapp.Model.Cab;
import com.assignment.cabbookingapp.Model.Driver;
import com.assignment.cabbookingapp.Model.Location;
import com.assignment.cabbookingapp.Model.User;
import com.assignment.cabbookingapp.Repository.DriverRepository;
import com.assignment.cabbookingapp.Repository.UserRepository;
import com.assignment.cabbookingapp.Service.DriverService;
import com.assignment.cabbookingapp.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

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
        assertEquals( user, userService.add_user(user));
    }

    @Test
    void update_userTest() {
        User user = new User("Harry", 'M', 22);
        when(userRepository.update_user("Harry", "911351566")).thenReturn(user);
        assertEquals(user, userService.update_user("Harry", "911351566"));
    }

    @Test
    void update_userLocationTest() {
        User user = new User("Harry", 'M', 22);
        Location location = new Location(2.0, 5.0);
        when(userRepository.updateLocation("Harry", location)).thenReturn(user);
        assertEquals(user, userService.update_userLocation("Harry", location));
    }

}
