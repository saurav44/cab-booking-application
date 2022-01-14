package com.assignment.cabbookingapp.Service;

import com.assignment.cabbookingapp.Model.Location;
import com.assignment.cabbookingapp.Model.User;
import com.assignment.cabbookingapp.Repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add_user(User user) {
        return userRepository.add_user(user);
    }

    public User getUser(String username) { return userRepository.getUser(username); }

    public User update_user(String username, String phone) {
        return userRepository.update_user(username, phone);
    }

    public User update_userLocation(String username, Location location) {
        return userRepository.updateLocation(username, location);
    }

}
